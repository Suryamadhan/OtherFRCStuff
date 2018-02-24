package team3647subsystems;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.*;
import team3647ConstantsAndFunctions.Constants;
import team3647ConstantsAndFunctions.NewFunctions;

public class Drivetrain 
{
	public static double aimedRatio, currentRatio, sum;
	public static boolean withinRange;
	
	public static WPI_TalonSRX leftSRX = new WPI_TalonSRX(Constants.leftMaster);
	public static WPI_TalonSRX rightSRX = new WPI_TalonSRX(Constants.rightMaster);
	
	public static VictorSPX leftSPX1 = new VictorSPX(Constants.leftSlave1);
	public static VictorSPX rightSPX1 = new VictorSPX(Constants.rightSlave1);
	public static VictorSPX leftSPX2 = new VictorSPX(Constants.leftSlave2);
	public static VictorSPX rightSPX2 = new VictorSPX(Constants.rightSlave2);
	
	public static DifferentialDrive drive = new DifferentialDrive(leftSRX, rightSRX);
	
	static double []adjustmentValues = new double[2];
	
	public static void drivetrainInitialization()
	{
		tankDrive(0,0);
		leftSPX1.follow(leftSRX);
		leftSPX2.follow(leftSRX);    
		rightSPX1.follow(rightSRX);
		rightSPX2.follow(rightSRX);
	}
	
	static double avg;

	
	public static void FRCarcadedrive(double yValue, double xValue)
	{
		drive.arcadeDrive(yValue, xValue);
	}
	
	public static void tankDrive(double lYValue, double rYValue)
	{
		drive.tankDrive(lYValue, rYValue, false);
	}
	
	public static void driveForw(double lValue, double rValue, double speed)
	{
		if(Math.abs(lValue - rValue) < 20)
		{
			tankDrive(speed, speed);
		}
		else if(rValue > lValue)
		{
			if(Math.abs(lValue - rValue) < 40)
			{
				tankDrive(speed, speed - .1);
			}
			else if(Math.abs(lValue - rValue) < 70)
			{
				tankDrive(speed, speed - .2);
			}
			else
			{
				tankDrive(speed, speed - .3);
			}
		}
		else
		{
			if(Math.abs(lValue - rValue) < 40)
			{
				tankDrive(speed - .1, speed);
			}
			else if(Math.abs(lValue - rValue) < 70)
			{
				tankDrive(speed - .2, speed);
			}
			else
			{
				tankDrive(speed - .3, speed);
			}
		}
		
	}

	static double drift;
	static String movingStatus, driftStatus;
	public static void arcadeDrive(double leftEnc, double rightEnc, double yValue, double xValue)
	{
		double lSpeed, rSpeed;
	 	if(yValue > 0 && xValue == 0)
	 	{
	 		movingStatus = "forward";
	 		if(driftStatus.equals("turn"))
	 		{
	 			drift++;
	 		}
	 		if(drift < 50 && driftStatus.equals("turn"))
	 		{
	 			Encoders.resetEncoders();
	 		}
	 		else
	 		{
	 			driftStatus = "noturn";
	 		}
	 	}
	 	else if(yValue < 0 && xValue == 0)
	 	{
	 		movingStatus = "backward";
	 	}
	 	else if(yValue == 0 && xValue == 0)
	 	{
	 		movingStatus = "stop";
	 		driftStatus = "turn";
	 	}
	 	else
	 	{
	 		movingStatus = "turning";
	 		driftStatus = "turn";
	 	}
	 		
	 	switch(movingStatus)
	 	{
	 		case "forward":
	 			if(yValue < .3)
	 			{
	 				lSpeed = yValue;
	 				rSpeed = yValue;
	 				Encoders.resetEncoders();
	 			}
	 			else
	 			{
	 				adjustmentValues = NewFunctions.adjustmentValues(leftEnc, rightEnc, false);
	 				lSpeed = yValue + adjustmentValues[0];
	 				rSpeed = yValue + adjustmentValues[1];
	 			}
	 			drive.tankDrive(lSpeed, -rSpeed, false);
	 			break;
	 		case "backward":
	 			if(yValue > -.3)
	 			{
		 			lSpeed =yValue;
		 			rSpeed =-yValue;
	 				Encoders.resetEncoders();
	 			}
	 			else
	 			{
	 				adjustmentValues = NewFunctions.adjustmentValues(leftEnc, rightEnc, false);
	 				lSpeed = yValue + adjustmentValues[0];
	 				rSpeed = yValue + adjustmentValues[1];
	 			}
	 			drive.tankDrive(lSpeed, -rSpeed, false);
	 			break;
	 		case "turning":
	 			drive.arcadeDrive(yValue, xValue,false);
	 			Encoders.resetEncoders();
	 			break;
	 		case "stop":
	 			lSpeed =(0);
	 			rSpeed =(0);
	 			drive.tankDrive(lSpeed, -rSpeed, false);
	 			break;
	 	}
	}
	
	public static boolean reachedDistance(double leftEnc, double rightEnc, double distance)
	{
		
		avg = Math.abs(leftEnc) + Math.abs(rightEnc);
		avg/=2;
		if(avg<distance)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static void testSpeed()
	{
		System.out.println("Left speed: " + leftSRX.get());
		System.out.println("Right speed:" + rightSRX.get());
	}
	
	public static boolean reachedTurnDistance(double sum, double requiredLeftDist, double requiredRightDist)
	{
		if(sum < requiredLeftDist + requiredRightDist)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static void goStraightLeft(double lValue, double rValue, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		aimedRatio = ((requiredRightDist)/(requiredLeftDist));
		currentRatio = (((rValue)/(lValue))/aimedRatio);
		sum = (rValue) + (lValue);
		if(currentRatio >= .9 && currentRatio <= 1.1)
		{
			withinRange = true;
		}
		else
		{
			withinRange = false;
		}
		if(withinRange || sum < 360)
		{
			drive.tankDrive(leftSpeed,rightSpeed, false);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				drive.tankDrive(leftSpeed + adjustment, (rightSpeed - adjustment), false);
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				drive.tankDrive(leftSpeed + (2*adjustment),(rightSpeed - (2*adjustment)), false);
			}
			else if(currentRatio > 1.25)
			{
				drive.tankDrive(leftSpeed + (3*adjustment), (rightSpeed - (3*adjustment)), false);
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				drive.tankDrive(leftSpeed - adjustment, (rightSpeed + adjustment), false);
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				drive.tankDrive(leftSpeed - (2*adjustment), (rightSpeed + (2*adjustment)), false);
			}
			else
			{
				drive.tankDrive(leftSpeed - (3*adjustment), (rightSpeed + (3*adjustment)), false);
			}
		}
	}
	
	public static void goStraightRight(double lValue, double rValue, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		aimedRatio = ((requiredLeftDist)/(requiredRightDist));
		currentRatio = (((lValue)/(rValue))/aimedRatio);
		sum = (rValue) + (lValue);
		if(currentRatio >= .9 && currentRatio <= 1.1)
		{
			withinRange = true;
		}
		else
		{
			withinRange = false;
		}
		if(withinRange || sum < 360)
		{
			drive.tankDrive(leftSpeed,rightSpeed, false);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				drive.tankDrive(leftSpeed - adjustment,(rightSpeed + adjustment), false);
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				drive.tankDrive(leftSpeed - (2*adjustment),(rightSpeed + (2*adjustment)), false);
			}
			else if(currentRatio > 1.25)
			{
				drive.tankDrive(leftSpeed - (3*adjustment),(rightSpeed + (3*adjustment)), false);
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				drive.tankDrive(leftSpeed + adjustment,(rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				drive.tankDrive(leftSpeed + (2*adjustment),(rightSpeed - (2*adjustment)));
			}
			else
			{
				drive.tankDrive(leftSpeed + (3*adjustment),(rightSpeed - (3*adjustment)));
			}
		}
	}
	
	public static void goBackLeft(double lValue, double rValue, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		aimedRatio = ((requiredRightDist)/(requiredLeftDist));
		rValue = Math.abs(rValue);
		lValue = Math.abs(lValue);
		currentRatio = (((rValue)/(lValue))/aimedRatio);
		sum = (rValue) + (lValue);
		if(withinRange || sum < 50)
		{
			drive.tankDrive(leftSpeed,rightSpeed, false);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				drive.tankDrive(leftSpeed - adjustment, (rightSpeed + adjustment), false);
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				drive.tankDrive(leftSpeed - (2*adjustment), (rightSpeed + (2*adjustment)), false);
			}
			else if(currentRatio > 1.25)
			{
				drive.tankDrive(leftSpeed - (3*adjustment), (rightSpeed + (3*adjustment)), false);
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				drive.tankDrive(leftSpeed + adjustment, (rightSpeed - adjustment), false);
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				drive.tankDrive(leftSpeed + (2*adjustment), (rightSpeed - (2*adjustment)), false);
			}
			else
			{
				drive.tankDrive(leftSpeed + (3*adjustment), (rightSpeed - (3*adjustment)), false);
			}
		}
	}
	
	public static void goBackRight(double lValue, double rValue, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		aimedRatio = ((requiredLeftDist)/(requiredRightDist));
		rValue = Math.abs(rValue);
		lValue = Math.abs(lValue);
		currentRatio = (((lValue)/(rValue))/aimedRatio);
		sum = (rValue) + (lValue);
		if(withinRange || sum < 50)
		{
			drive.tankDrive(leftSpeed,rightSpeed, false);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				drive.tankDrive(leftSpeed + adjustment,(rightSpeed - adjustment), false);
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				drive.tankDrive(leftSpeed + (2*adjustment), (rightSpeed - (2*adjustment)), false);
			}
			else if(currentRatio > 1.25)
			{	
				drive.tankDrive(leftSpeed + (3*adjustment), (rightSpeed - (3*adjustment)), false);
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				drive.tankDrive(leftSpeed - adjustment, (rightSpeed + adjustment), false);
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				drive.tankDrive(leftSpeed - (2*adjustment), (rightSpeed + (2*adjustment)), false);
			}
			else
			{
				drive.tankDrive(leftSpeed - (3*adjustment), (rightSpeed + (3*adjustment)), false);
			}
		}
	}
	
	public static void stop()
	{
		drive.tankDrive(0,0);
	}
}
