package team3647subsystems;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import team3647ConstantsAndFunctions.Constants;

public class Drivetrain 
{
	public static WPI_TalonSRX leftSRX = new WPI_TalonSRX(Constants.leftMaster);
	public static WPI_TalonSRX rightSRX = new WPI_TalonSRX(Constants.rightMaster);
	
	public static VictorSPX leftSPX1 = new VictorSPX(Constants.leftSlave1);
	public static VictorSPX rightSPX1 = new VictorSPX(Constants.rightSlave1);
	public static VictorSPX leftSPX2 = new VictorSPX(Constants.leftSlave2);
	public static VictorSPX rightSPX2 = new VictorSPX(Constants.rightSlave2);
	
	public static DifferentialDrive drive = new DifferentialDrive(leftSRX, rightSRX);
	
	public static void drivetrainInitialization()
	{
		setLeftMotorSpeed(0);
		setRightMotorSpeed(0);
		leftSPX1.follow(leftSRX);
		leftSPX2.follow(leftSRX);    
		rightSPX1.follow(rightSRX);
		rightSPX2.follow(rightSRX);
	}
	
	static double avg;

	public static void setLeftMotorSpeed(double speed, ControlMode cm)
	{
		leftSRX.set(cm, speed);
	}
	
	public static void setLeftMotorSpeed(double speed)
	{
		leftSRX.set(speed);
	}
	
	public static void setRightMotorSpeed(double speed, ControlMode cm)
	{
		rightSRX.set(cm, speed);
	}
	
	public static void setRightMotorSpeed(double speed)
	{
		rightSRX.set(speed);
	}
	
	
	public static void testDrive(double fYValue, double sYValue)
	{
		setLeftMotorSpeed(fYValue);
		setRightMotorSpeed(-sYValue);
	}
	
	public static void FRCarcadedrive(double yValue, double xValue)
	{
		drive.arcadeDrive(yValue, xValue);
	}
	
	public static void tankDrive(double lYValue, double rYValue)
	{
		drive.tankDrive(lYValue, rYValue, false);
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
	 					rSpeed = -yValue;
	 					Encoders.resetEncoders();
	 				}
	 				else
	 				{
	 					if(Math.abs(leftEnc - rightEnc) < 100)
	 					{
	 						lSpeed = yValue;
	 						rSpeed = -yValue;
	 					}
	 					else if(Math.abs(leftEnc - rightEnc) < 200)
						{
	 						if(rightEnc > leftEnc)
	 						{
	 							 lSpeed = yValue;
	 							 rSpeed = -yValue + .05;
	 						}
	 						
	 						else
	 						{
	 							 lSpeed = yValue - .05;
	 							 rSpeed = -yValue;
	 						}
	 					}
	 					else if(Math.abs(leftEnc - rightEnc) < 300)
	 					{
	 						if(rightEnc > leftEnc)
	 						{
	 							 lSpeed = yValue;
	 							 rSpeed = -yValue + .1;
	 						}
	 						else
	 						{
	 							 lSpeed = yValue - .1;
	 							 rSpeed = -yValue;
	 						}
	 					}
	 					else if(Math.abs(leftEnc - rightEnc) < 500)
	 					{
	 						if(rightEnc > leftEnc)
	 						{
	 							 lSpeed = yValue;
	 							 rSpeed = -yValue + .18;
	 						}
	 						else
	 						{
	 							 lSpeed = yValue - .18;
	 							 rSpeed = -yValue;
	 						}
	 					}
		 				else
		 				{
		 					if(rightEnc > leftEnc)
		 					{
		 						 lSpeed = yValue;
		 						 rSpeed = -yValue + .27;
		 					}
		 					else
		 					{
		 						 lSpeed = yValue - .27;
		 						 rSpeed = -yValue;
		 					}
		 				}
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
	 					if(Math.abs(leftEnc - rightEnc) < 100)
	 					{
	 						lSpeed = yValue;
	 						rSpeed = -yValue;
	 					}
	 					else if(Math.abs(leftEnc - rightEnc) < 200)
	 					{
	 						if(rightEnc > leftEnc)
	 						{
	 							 lSpeed = yValue + .05;
	 							 rSpeed = -yValue;
	 						}
	 						else
	 						{
	 							 lSpeed = yValue;
	 							 rSpeed = -yValue - .05;
	 						}
	 					}
	 					else if(Math.abs(leftEnc - rightEnc) < 300)
	 					{
	 						if(rightEnc > leftEnc)
	 						{
	 							 lSpeed = yValue + .1;
	 							 rSpeed = -yValue;
	 						}
	 						else
							{
								 lSpeed = yValue;
	 							 rSpeed = -yValue - .1;
	 						}
	 					}
	 					else if(Math.abs(leftEnc - rightEnc) < 500)
	 					{
	 						if(rightEnc > leftEnc)
	 						{
	 							 lSpeed = yValue + .18;
	 							 rSpeed = -yValue;
	 						}
	 						else
	 						{
	 							 lSpeed = yValue;
	 							 rSpeed = -yValue - .18;
	 						}
	 					}
	 					else
	 					{
	 						if(rightEnc > leftEnc)
	 						{
	 							 lSpeed = yValue + .27;
	 							 rSpeed = -yValue;
	 						}
	 						else
	 						{
	 							 lSpeed = yValue;
	 							 rSpeed = -yValue - .27;
							}
	 					}
	 				}
	 				drive.tankDrive(lSpeed, -rSpeed, false);
	 				break;
	 			case "turning":
//	 				double speedY, speedX;
//	 				speedY = Math.abs(yValue);
//	 				speedY *= yValue;
//	 				speedX = xValue * Constants.turnConstant(yValue);
//	 				lSpeed = speedY + speedX ;
//	 				rSpeed = -speedY + speedX ;
//	 				drive.tankDrive(lSpeed, -rSpeed, false);
	 				drive.arcadeDrive(yValue, xValue);
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
	
	public static void driveForward(double leftEnc, double rightEnc, double speed)
	{
		if(Math.abs(leftEnc - rightEnc) < 100)
		{
			drive.tankDrive(speed, speed, false);
		}
		else if(Math.abs(leftEnc - rightEnc) < 200)
		{
			if(leftEnc > rightEnc)
		 	{
				drive.tankDrive(speed - .05, speed, false);
		 	}
			else
		 	{
		 		drive.tankDrive(speed, speed - .05, false);
		 	}
		 }
		 else if(Math.abs(leftEnc - rightEnc) < 300)
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed - .1, speed, false);
			 }
			 else
			 {
				 drive.tankDrive(speed, speed - .1, false);
			 }
		 }
		 else if(Math.abs(leftEnc - rightEnc) < 500)
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed - .18, speed, false);
			 }
			 else
			 {
				 drive.tankDrive(speed, speed - .18, false);
			 }
		 }
		 else
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed - .27, speed, false);
			 }
			 else
			 {
			 	drive.tankDrive(speed, speed - .27, false);
			 }
		 }
	}
	
	public static void driveBackward(double leftEnc, double rightEnc, double speed)
	{
		if(Math.abs(leftEnc - rightEnc) < 100)
		{
			drive.tankDrive(speed, speed, false);
		}
		else if(Math.abs(leftEnc - rightEnc) < 200)
		{
			if(leftEnc > rightEnc)
		 	{
				drive.tankDrive(speed + .05, speed, false);
		 	}
			else
		 	{
		 		drive.tankDrive(speed, speed + .05, false);
		 	}
		 }
		else if(Math.abs(leftEnc - rightEnc) < 300)
		{
			if(leftEnc > rightEnc)
			{
				drive.tankDrive(speed + .1, speed, false);
			}
			else
			{
				drive.tankDrive(speed, speed + .1, false);
			}
		 }
		else if(Math.abs(leftEnc - rightEnc) < 500)
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed +.18, speed, false);
			 }
			 else
			 {
				 drive.tankDrive(speed, speed + .18, false);
			 }
		 }
		 else
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed + .27, speed, false);
			 }
			 else
			 {
			 	drive.tankDrive(speed, speed + .27, false);
			 }
		 }
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
	
	public static void goStraightLeft(double currentRatio, boolean withinRange, double sum, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		if(withinRange || sum < 360)
		{
			drive.tankDrive(leftSpeed,rightSpeed, false);
		}
		else
		{
			System.out.println("RIP");
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
//				setLeftMotorSpeed(leftSpeed + adjustment);
//				setRightMotorSpeed(-(rightSpeed - adjustment));
				
				drive.tankDrive(leftSpeed + adjustment, (rightSpeed - adjustment), false);
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
//				setLeftMotorSpeed(leftSpeed + (2*adjustment));
//				setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
				
				drive.tankDrive(leftSpeed + (2*adjustment),(rightSpeed - (2*adjustment)), false);
			}
			else if(currentRatio > 1.25)
			{
//				setLeftMotorSpeed(leftSpeed + (3*adjustment));
//				setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
				
				drive.tankDrive(leftSpeed + (3*adjustment), (rightSpeed - (3*adjustment)), false);
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
//				setLeftMotorSpeed(leftSpeed - adjustment);
//				setRightMotorSpeed(-(rightSpeed + adjustment));
				
				drive.tankDrive(leftSpeed - adjustment, (rightSpeed + adjustment), false);
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
//				setLeftMotorSpeed(leftSpeed - (2*adjustment));
//				setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
				
				drive.tankDrive(leftSpeed - (2*adjustment), (rightSpeed + (2*adjustment)), false);
			}
			else
			{
//				setLeftMotorSpeed(leftSpeed - (3*adjustment));
//				setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
				
				drive.tankDrive(leftSpeed - (3*adjustment), (rightSpeed + (3*adjustment)), false);
			}
		}
	}
	
	public static void goStraightRight(double currentRatio, boolean withinRange, double sum, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		if(withinRange || sum < 360)
		{
//			setLeftMotorSpeed(leftSpeed);
//			setRightMotorSpeed(-rightSpeed);
			
			drive.tankDrive(leftSpeed,rightSpeed, false);
			System.out.println("no rip");
		}
		else
		{
			System.out.println("RIP");
			System.out.println("left:" + leftSRX.get());
			System.out.println("right:" + rightSRX.get());
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
//				setLeftMotorSpeed(leftSpeed + adjustment);
//				setRightMotorSpeed(-(rightSpeed - adjustment));
				
				drive.tankDrive(leftSpeed - adjustment,(rightSpeed + adjustment), false);
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
//				setLeftMotorSpeed(leftSpeed + (2*adjustment));
//				setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
				
				drive.tankDrive(leftSpeed - (2*adjustment),(rightSpeed + (2*adjustment)), false);
			}
			else if(currentRatio > 1.25)
			{
//				setLeftMotorSpeed(leftSpeed + (3*adjustment));
//				setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
				
				drive.tankDrive(leftSpeed - (3*adjustment),(rightSpeed + (3*adjustment)), false);
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
//				setLeftMotorSpeed(leftSpeed - adjustment);
//				setRightMotorSpeed(-(rightSpeed + adjustment));
				
				drive.tankDrive(leftSpeed + adjustment,(rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
//				setLeftMotorSpeed(leftSpeed - (2*adjustment));
//				setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
				
				drive.tankDrive(leftSpeed + (2*adjustment),(rightSpeed - (2*adjustment)));
			}
			else
			{
//				setLeftMotorSpeed(leftSpeed - (3*adjustment));
//				setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
				
				drive.tankDrive(leftSpeed + (3*adjustment),(rightSpeed - (3*adjustment)));
			}
		}
	}
	
	public static void goBackLeft(double currentRatio, boolean withinRange, double sum, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		if(withinRange || sum < 50)
		{
//			setLeftMotorSpeed(leftSpeed);
//			setRightMotorSpeed(-rightSpeed);
			
			drive.tankDrive(leftSpeed,rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
//				setLeftMotorSpeed(leftSpeed + adjustment);
//				setRightMotorSpeed(-(rightSpeed - adjustment));
				
				drive.tankDrive(leftSpeed - adjustment,-(rightSpeed + adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
//				setLeftMotorSpeed(leftSpeed + (2*adjustment));
//				setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
				
				drive.tankDrive(leftSpeed - (2*adjustment),-(rightSpeed + (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
//				setLeftMotorSpeed(leftSpeed + (3*adjustment));
//				setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
				
				drive.tankDrive(leftSpeed - (3*adjustment),-(rightSpeed + (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
//				setLeftMotorSpeed(leftSpeed - adjustment);
//				setRightMotorSpeed(-(rightSpeed + adjustment));
				
				drive.tankDrive(leftSpeed + adjustment,-(rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
//				setLeftMotorSpeed(leftSpeed - (2*adjustment));
//				setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
				
				drive.tankDrive(leftSpeed + (2*adjustment),-(rightSpeed - (2*adjustment)));
			}
			else
			{
//				setLeftMotorSpeed(leftSpeed - (3*adjustment));
//				setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
				
				drive.tankDrive(leftSpeed + (3*adjustment),-(rightSpeed - (3*adjustment)));
			}
		}
	}
	
	public static void goBackRight(double currentRatio, boolean withinRange, double sum, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		if(withinRange || sum < 50)
		{
//			setLeftMotorSpeed(leftSpeed);
//			setRightMotorSpeed(-rightSpeed);
			
			drive.tankDrive(leftSpeed,rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
//				setLeftMotorSpeed(leftSpeed + adjustment);
//				setRightMotorSpeed(-(rightSpeed - adjustment));
				
				drive.tankDrive(leftSpeed - adjustment,-(rightSpeed + adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
//				setLeftMotorSpeed(leftSpeed + (2*adjustment));
//				setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
				
				drive.tankDrive(leftSpeed - (2*adjustment),-(rightSpeed + (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
//				setLeftMotorSpeed(leftSpeed + (3*adjustment));
//				setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
				
				drive.tankDrive(leftSpeed - (3*adjustment),-(rightSpeed + (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
//				setLeftMotorSpeed(leftSpeed - adjustment);
//				setRightMotorSpeed(-(rightSpeed + adjustment));
				
				drive.tankDrive(leftSpeed + adjustment,-(rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
//				setLeftMotorSpeed(leftSpeed - (2*adjustment));
//				setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
				
				drive.tankDrive(leftSpeed + (2*adjustment),-(rightSpeed - (2*adjustment)));
			}
			else
			{
//				setLeftMotorSpeed(leftSpeed - (3*adjustment));
//				setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
				
				drive.tankDrive(leftSpeed + (3*adjustment),-(rightSpeed - (3*adjustment)));
			}
		}
	}
}
