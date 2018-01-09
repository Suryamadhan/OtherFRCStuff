package team3647subsystems;

import org.usfirst.frc.team3647.robot.Robot;

import edu.wpi.first.wpilibj.Spark;
import team3647ConstantsAndFunctions.Constants;

public class Drivetrain 
{
	public static Spark leftMotor = new Spark(Constants.leftMotorPin);
	public static Spark rightMotor =new Spark(Constants.righMotorPin);
	
	static Encoders enc = new Encoders();
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
		}
		else if(yValue < 0 && xValue == 0)
		{
			movingStatus = "backward";
		}
		else if(yValue == 0 && xValue == 0)
		{
			movingStatus = "stop";
		}
		else
		{
			movingStatus = "turning";
		}
		
		switch(movingStatus)
		{
			case "forward":
				if(yValue < .3)
				{
					setLeftMotorSpeed(yValue);
					setRightMotorSpeed(-yValue);
					//reset Encoders
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
					//reset Encoders
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
				//Reset Encoders
				break;
			case "stop":
				setLeftMotorSpeed(0);
				setRightMotorSpeed(0);
				break;
		}
	}
	
}
