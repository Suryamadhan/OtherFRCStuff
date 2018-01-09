package team3647subsystems;

import edu.wpi.first.wpilibj.Spark;
import team3647ConstantsAndFunctions.Constants;

public class Drivetrain 
{
	public static Spark leftMotor = new Spark(Constants.leftMotorPin);
	public static Spark rightMotor =new Spark(Constants.righMotorPin);
	
	static double drift;
	static String movingStatus, driftStatus;
	
	public static void setLeftMotorSpeed(double speed)
	{
		Encoders.resetEncoders();
		leftMotor.set(speed);
	}
	
	public static void setRightMotorSpeed(double speed)
	{
		rightMotor.set(speed);
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
}
