package team3647subsystems;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import team3647ConstantsAndFunctions.Constants;

public class Drivetrain 
{
	public static CANTalon leftMotor;
	public static CANTalon rightMotor;
	
	public static void  stuff()
	{
		leftMotor = new CANTalon(0);
		rightMotor =new CANTalon(3);
	}
	
	static double drift, avg;
	static String movingStatus, driftStatus;
	
	public static void setLeftMotorSpeed(double speed)
	{
		leftMotor.set(speed);
		//leftMotor.set(ControlMode.MotionProfile, speed);
	}
	
	public static void setRightMotorSpeed(double speed)
	{
		rightMotor.set(speed);
	//	rightMotor.set(ControlMode.MotionProfile, speed);
	}
	
	public static void test(double yValue)
	{
		setRightMotorSpeed(yValue);
//		setLeftMotorSpeed(yValue);
	}
	
	public static void testDrive(double yValue, double xValue)
	{
		setLeftMotorSpeed(yValue + xValue);
		setRightMotorSpeed(-(yValue - xValue));
	}
	
	public static void arcadeDrive(double leftEnc, double rightEnc, double yValue, double xValue)
	{
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
					setLeftMotorSpeed(yValue);
					setRightMotorSpeed(-yValue);
					Encoders.resetEncoders();
				}
				else
				{
					if(Math.abs(leftEnc - rightEnc) < 6)
					{
						setLeftMotorSpeed(yValue);
						setRightMotorSpeed(-yValue);
					}
					else if(Math.abs(leftEnc - rightEnc) < 20)
					{
						if(rightEnc > leftEnc)
						{
							 setLeftMotorSpeed(yValue);
							 setRightMotorSpeed(-yValue + .125);
						}
						else
						{
							 setLeftMotorSpeed(yValue - .125);
							 setRightMotorSpeed(-yValue);
						}
					}
					else if(Math.abs(leftEnc - rightEnc) < 34)
					{
						if(rightEnc > leftEnc)
						{
							 setLeftMotorSpeed(yValue);
							 setRightMotorSpeed(-yValue + .2);
						}
						else
						{
							 setLeftMotorSpeed(yValue - .2);
							 setRightMotorSpeed(-yValue);
						}
					}
					else if(Math.abs(leftEnc - rightEnc) < 48)
					{
						if(rightEnc > leftEnc)
						{
							 setLeftMotorSpeed(yValue);
							 setRightMotorSpeed(-yValue + .275);
						}
						else
						{
							 setLeftMotorSpeed(yValue - .275);
							 setRightMotorSpeed(-yValue);
						}
					}
					else
					{
						if(rightEnc > leftEnc)
						{
							 setLeftMotorSpeed(yValue);
							 setRightMotorSpeed(-yValue + .34);
						}
						else
						{
							 setLeftMotorSpeed(yValue - .34);
							 setRightMotorSpeed(-yValue);
						}
					}
				}
				break;
			case "backward":
				if(yValue > -.3)
				{
					setLeftMotorSpeed(yValue);
					setRightMotorSpeed(-yValue);
					Encoders.resetEncoders();
				}
				else
				{
					if(Math.abs(leftEnc - rightEnc) < 6)
					{
						setLeftMotorSpeed(yValue);
						setRightMotorSpeed(-yValue);
					}
					else if(Math.abs(leftEnc - rightEnc) < 20)
					{
						if(rightEnc > leftEnc)
						{
							 setLeftMotorSpeed(yValue + .125);
							 setRightMotorSpeed(-yValue);
						}
						else
						{
							 setLeftMotorSpeed(yValue);
							 setRightMotorSpeed(-yValue - .125);
						}
					}
					else if(Math.abs(leftEnc - rightEnc) < 34)
					{
						if(rightEnc > leftEnc)
						{
							 setLeftMotorSpeed(yValue + .2);
							 setRightMotorSpeed(-yValue);
						}
						else
						{
							 setLeftMotorSpeed(yValue);
							 setRightMotorSpeed(-yValue - .2);
						}
					}
					else if(Math.abs(leftEnc - rightEnc) < 48)
					{
						if(rightEnc > leftEnc)
						{
							 setLeftMotorSpeed(yValue + .275);
							 setRightMotorSpeed(-yValue);
						}
						else
						{
							 setLeftMotorSpeed(yValue);
							 setRightMotorSpeed(-yValue - .275);
						}
					}
					else
					{
						if(rightEnc > leftEnc)
						{
							 setLeftMotorSpeed(yValue + .34);
							 setRightMotorSpeed(-yValue);
						}
						else
						{
							 setLeftMotorSpeed(yValue);
							 setRightMotorSpeed(-yValue - .34);
						}
					}
				}
				break;
			case "turning":
				double speedY, speedX;
				speedY = Math.abs(yValue);
				speedY *= yValue;
				speedX = xValue * Constants.turnConstant(yValue);
				setLeftMotorSpeed(speedY + speedX);
				setRightMotorSpeed(-speedY + speedX);
				Encoders.resetEncoders();
				break;
			case "stop":
				setLeftMotorSpeed(0);
				setRightMotorSpeed(0);
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
	
	public static void driveForward(double leftEnc, double rightEnc, double speed)
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
				 setLeftMotorSpeed(speed);
				 setRightMotorSpeed(-speed + .125);
			}
			else
			{
				 setLeftMotorSpeed(speed - .125);
				 setRightMotorSpeed(-speed);
			}
		}
		else if(Math.abs(leftEnc - rightEnc) < 34)
		{
			if(rightEnc > leftEnc)
			{
				 setLeftMotorSpeed(speed);
				 setRightMotorSpeed(-speed + .2);
			}
			else
			{
				 setLeftMotorSpeed(speed - .2);
				 setRightMotorSpeed(-speed);
			}
		}
		else if(Math.abs(leftEnc - rightEnc) < 48)
		{
			if(rightEnc > leftEnc)
			{
				 setLeftMotorSpeed(speed);
				 setRightMotorSpeed(-speed + .275);
			}
			else
			{
				 setLeftMotorSpeed(speed - .275);
				 setRightMotorSpeed(-speed);
			}
		}
		else
		{
			if(rightEnc > leftEnc)
			{
				 setLeftMotorSpeed(speed);
				 setRightMotorSpeed(-speed + .34);
			}
			else
			{
				 setLeftMotorSpeed(speed - .34);
				 setRightMotorSpeed(-speed);
			}
		}
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
			Drivetrain.setLeftMotorSpeed(leftSpeed);
			Drivetrain.setRightMotorSpeed(-rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed + adjustment);
				Drivetrain.setRightMotorSpeed(-(rightSpeed - adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed + (2*adjustment));
				Drivetrain.setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed + (3*adjustment));
				Drivetrain.setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed - adjustment);
				Drivetrain.setRightMotorSpeed(-(rightSpeed + adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed - (2*adjustment));
				Drivetrain.setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
			}
			else
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed - (3*adjustment));
				Drivetrain.setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
			}
		}
	}
	
	public static void goStraightRight(double currentRatio, boolean withinRange, double sum, double requiredLeftDist, double requiredRightDist, double leftSpeed, double rightSpeed, double adjustment)
	{
		if(withinRange || sum < 50)
		{
			Drivetrain.setLeftMotorSpeed(leftSpeed);
			Drivetrain.setRightMotorSpeed(-rightSpeed);
		}
		else
		{
			if(currentRatio > 1.1 && currentRatio < 1.18)
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed + adjustment);
				Drivetrain.setRightMotorSpeed(-(rightSpeed - adjustment));
			}
			else if(currentRatio > 1.18 && currentRatio < 1.25)
			{			
				Drivetrain.setLeftMotorSpeed(leftSpeed + (2*adjustment));
				Drivetrain.setRightMotorSpeed(-(rightSpeed - (2*adjustment)));
			}
			else if(currentRatio > 1.25)
			{		
				Drivetrain.setLeftMotorSpeed(leftSpeed + (3*adjustment));
				Drivetrain.setRightMotorSpeed(-(rightSpeed - (3*adjustment)));
			}
			else if(currentRatio < .9 && currentRatio > .82)
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed - adjustment);
				Drivetrain.setRightMotorSpeed(-(rightSpeed + adjustment));
			}
			else if(currentRatio < .82 && currentRatio > .75)
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed - (2*adjustment));
				Drivetrain.setRightMotorSpeed(-(rightSpeed + (2*adjustment)));
			}
			else
			{
				Drivetrain.setLeftMotorSpeed(leftSpeed - (3*adjustment));
				Drivetrain.setRightMotorSpeed(-(rightSpeed + (3*adjustment)));
			}
		}
	}
	
	public static void goBackLeft()
	{
		
	}
	
	public static void goBackRight()
	{
		
	}
}
