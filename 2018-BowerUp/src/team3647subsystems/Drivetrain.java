package team3647subsystems;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.*;
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
	
	public static DifferentialDrive _drive = new DifferentialDrive(leftSRX, rightSRX);
	
	public static void drivetrainInitialization()
	{
		setLeftMotorSpeed(0);
		setRightMotorSpeed(0);
		leftSPX1.follow(leftSRX);
		leftSPX2.follow(leftSRX);    
		rightSPX1.follow(rightSRX);
		rightSPX2.follow(rightSRX);
	}
	
	public static void configPID()
	{
		leftSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		leftSRX.setSensorPhase(true);
		leftSRX.configNominalOutputForward(0, 10);
		leftSRX.configNominalOutputReverse(0, 10);
		leftSRX.configPeakOutputForward(1, 10);
		leftSRX.configPeakOutputReverse(-1, 10);
		leftSRX.config_kF(0, 0.1097, 10);
		leftSRX.config_kP(0, 0.113333, 10);
		leftSRX.config_kI(0, 0, 10);
		leftSRX.config_kD(0, 0, 10);
		
		rightSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		rightSRX.setSensorPhase(true);
		rightSRX.configNominalOutputForward(0, 10);
		rightSRX.configNominalOutputReverse(0, 10);
		rightSRX.configPeakOutputForward(1, 10);
		rightSRX.configPeakOutputReverse(-1, 10);
		rightSRX.config_kF(0, 0.1097, 10);
		rightSRX.config_kP(0, 0.113333, 10);
		rightSRX.config_kI(0, 0, 10);
		rightSRX.config_kD(0, 0, 10);
		
	}
	
	static double drift, avg;
	static String movingStatus, driftStatus;
	
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
	
	public static void testPID(double yValue, double xValue)
	{
		_drive.arcadeDrive(yValue, xValue);
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
	
	public static void driveForward(double leftEnc, double rightEnc, double speed)
	{
//		if(Math.abs(leftEnc - rightEnc) < 6)
//		{
//			setLeftMotorSpeed(speed);
//			setRightMotorSpeed(-speed);
//		}
//		else if(Math.abs(leftEnc - rightEnc) < 20)
//		{
//			if(rightEnc > leftEnc)
//			{
//				 setLeftMotorSpeed(speed);
//				 setRightMotorSpeed(-speed + .125);
//			}
//			else
//			{
//				 setLeftMotorSpeed(speed - .125);
//				 setRightMotorSpeed(-speed);
//			}
//		}
//		else if(Math.abs(leftEnc - rightEnc) < 34)
//		{
//			if(rightEnc > leftEnc)
//			{
//				 setLeftMotorSpeed(speed);
//				 setRightMotorSpeed(-speed + .2);
//			}
//			else
//			{
//				 setLeftMotorSpeed(speed - .2);
//				 setRightMotorSpeed(-speed);
//			}
//		}
//		else if(Math.abs(leftEnc - rightEnc) < 48)
//		{
//			if(rightEnc > leftEnc)
//			{
//				 setLeftMotorSpeed(speed);
//				 setRightMotorSpeed(-speed + .275);
//			}
//			else
//			{
//				 setLeftMotorSpeed(speed - .275);
//				 setRightMotorSpeed(-speed);
//			}
//		}
//		else
//		{
//			if(rightEnc > leftEnc)
//			{
//				 setLeftMotorSpeed(speed);
//				 setRightMotorSpeed(-speed + .34);
//			}
//			else
//			{
//				 setLeftMotorSpeed(speed - .34);
//				 setRightMotorSpeed(-speed);
//			}
//		}
		
		setLeftMotorSpeed(speed);
		setRightMotorSpeed(-speed);
	}
	
	public static void driveBackward(double leftEnc, double rightEnc, double speed)
	{
		if(Math.abs(leftEnc - rightEnc) < 6)
		{
			setLeftMotorSpeed(speed);
			setRightMotorSpeed(-speed);
		}
		else if(Math.abs(leftEnc - rightEnc) < 20)
		{
			if(rightEnc > leftEnc)
			{
				 setLeftMotorSpeed(speed + .125);
				 setRightMotorSpeed(-speed);
			}
			else
			{
				 setLeftMotorSpeed(speed);
				 setRightMotorSpeed(-speed - .125);
			}
		}
		else if(Math.abs(leftEnc - rightEnc) < 34)
		{
			if(rightEnc > leftEnc)
			{
				 setLeftMotorSpeed(speed + .2);
				 setRightMotorSpeed(-speed);
			}
			else
			{
				 setLeftMotorSpeed(speed);
				 setRightMotorSpeed(-speed - .2);
			}
		}
		else if(Math.abs(leftEnc - rightEnc) < 48)
		{
			if(rightEnc > leftEnc)
			{
				 setLeftMotorSpeed(speed + .275);
				 setRightMotorSpeed(-speed);
			}
			else
			{
				 setLeftMotorSpeed(speed);
				 setRightMotorSpeed(-speed - .275);
			}
		}
		else
		{
			if(rightEnc > leftEnc)
			{
				 setLeftMotorSpeed(speed + .34);
				 setRightMotorSpeed(-speed);
			}
			else
			{
				 setLeftMotorSpeed(speed);
				 setRightMotorSpeed(-speed - .34);
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
		if(withinRange || sum < 50)
		{
			setLeftMotorSpeed(leftSpeed);
			setRightMotorSpeed(-rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				setLeftMotorSpeed(leftSpeed + adjustment);
				setRightMotorSpeed(-(rightSpeed - adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				setLeftMotorSpeed(leftSpeed + (2*adjustment));
				setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
				setLeftMotorSpeed(leftSpeed + (3*adjustment));
				setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				setLeftMotorSpeed(leftSpeed - adjustment);
				setRightMotorSpeed(-(rightSpeed + adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				setLeftMotorSpeed(leftSpeed - (2*adjustment));
				setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
			}
			else
			{
				setLeftMotorSpeed(leftSpeed - (3*adjustment));
				setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
			}
		}
	}
	
	public static void goStraightRight(double currentRatio, boolean withinRange, double sum, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		if(withinRange || sum < 50)
		{
			setLeftMotorSpeed(leftSpeed);
			setRightMotorSpeed(-rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				setLeftMotorSpeed(leftSpeed + adjustment);
				setRightMotorSpeed(-(rightSpeed - adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{			
				setLeftMotorSpeed(leftSpeed + (2*adjustment));
				setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{		
				setLeftMotorSpeed(leftSpeed + (3*adjustment));
				setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				setLeftMotorSpeed(leftSpeed - adjustment);
				setRightMotorSpeed(-(rightSpeed + adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				setLeftMotorSpeed(leftSpeed - (2*adjustment));
				setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
			}
			else
			{
				setLeftMotorSpeed(leftSpeed - (3*adjustment));
				setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
			}
		}
	}
	
	public static void goBackLeft(double currentRatio, boolean withinRange, double sum, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		if(withinRange || sum < 50)
		{
			setLeftMotorSpeed(leftSpeed);
			setRightMotorSpeed(-rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				setLeftMotorSpeed(leftSpeed - adjustment);
				setRightMotorSpeed(-(rightSpeed + adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				setLeftMotorSpeed(leftSpeed - (2*adjustment));
				setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
				setLeftMotorSpeed(leftSpeed - (3*adjustment));
				setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				setLeftMotorSpeed(leftSpeed + adjustment);
				setRightMotorSpeed(-(rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				setLeftMotorSpeed(leftSpeed + (2*adjustment));
				setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
			}
			else
			{
				setLeftMotorSpeed(leftSpeed + (3*adjustment));
				setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
			}
		}
	}
	
	public static void goBackRight(double currentRatio, boolean withinRange, double sum, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		if(withinRange || sum < 50)
		{
			setLeftMotorSpeed(leftSpeed);
			setRightMotorSpeed(-rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				setLeftMotorSpeed(leftSpeed - adjustment);
				setRightMotorSpeed(-(rightSpeed + adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				setLeftMotorSpeed(leftSpeed - (2*adjustment));
				setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
				setLeftMotorSpeed(leftSpeed - (3*adjustment));
				setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				setLeftMotorSpeed(leftSpeed + adjustment);
				setRightMotorSpeed(-(rightSpeed - adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				setLeftMotorSpeed(leftSpeed + (2*adjustment));
				setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
			}
			else
			{
				setLeftMotorSpeed(leftSpeed + (3*adjustment));
				setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
			}
		}
	}
}
