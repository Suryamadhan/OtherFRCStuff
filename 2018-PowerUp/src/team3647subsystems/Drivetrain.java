package team3647subsystems;

import org.usfirst.frc.team3647.robot.Robot;

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
					
				}
				break;
		}
	}
	
}
