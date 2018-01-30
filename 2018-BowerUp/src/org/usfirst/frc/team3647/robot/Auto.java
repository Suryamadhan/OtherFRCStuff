package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Timer;
import team3647ConstantsAndFunctions.Constants;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;

public class Auto 
{
	static double aimedRatio, currentRatio;
	static double sum;
	static boolean withinRange;
	static double requiredLeftDist, requiredRightDist, requiredStraightDist = 0;
	static int currentState;
	
	
	public static void test(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
				requiredStraightDist = Constants.testStright -1440;
				if(!Drivetrain.reachedDistance(lValue, rValue, requiredStraightDist))
				{
					Drivetrain.driveForward(lValue, rValue, .6);
				}
				else
				{
					currentState =2;
				}
				break;
			case 2:
				requiredStraightDist = Constants.testStright;
				if(!Drivetrain.reachedDistance(lValue, rValue, requiredStraightDist))
				{
					Drivetrain.driveForward(lValue, rValue, .1);
				}
				else
				{
					Encoders.resetEncoders();
					//Timer.delay(.2);
					System.out.println("lValue: " + lValue);
					System.out.println("rValue: " + rValue);
					requiredStraightDist = 0;
					currentState = 3;
				}
				break;
			case 3:
				requiredLeftDist = (Constants.testSmall);
				requiredRightDist = (Constants.testBig);
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
				if(!Drivetrain.reachedTurnDistance(sum, requiredLeftDist, requiredRightDist))
				{
					Drivetrain.goStraightLeft(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, .325, .585, .05);
				}
				else
				{
					currentState = 6;
				}
				break;
			case 4:
				Drivetrain.stop();
				Encoders.resetEncoders();
				Timer.delay(.2);
				currentState = 5;
				break;
			case 5:
				requiredLeftDist = (Constants.testSmall);
				requiredRightDist = (Constants.testBig);
				aimedRatio = ((requiredRightDist)/(requiredLeftDist));
				rValue = Math.abs(rValue);
				lValue = Math.abs(lValue);
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
				if(!Drivetrain.reachedTurnDistance(sum, requiredLeftDist, requiredRightDist))
				{
					Drivetrain.goBackLeft(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, -.2, -.36, .03);
				}
				else
				{
					currentState = 6;
				}
				break;
			case 6:
				Drivetrain.stop();
				break;
		}
	}
	
	public static void FR(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
				requiredRightDist = (Constants.testSmall);
				requiredLeftDist = (Constants.testBig);
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
				if(!Drivetrain.reachedTurnDistance(sum, requiredLeftDist, requiredRightDist))
				{
					Drivetrain.goStraightRight(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, .585, .325, .04);
				}
				else
				{
					currentState = 2;
				}
				break;
			case 2:
				Drivetrain.stop();
				break;
		}
	}
	
	public static void BR(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
				requiredRightDist = (Constants.testSmall);
				requiredLeftDist = (Constants.testBig);
				aimedRatio = ((requiredLeftDist)/(requiredRightDist));
				lValue = Math.abs(lValue);
				rValue = Math.abs(rValue);
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
				if(!Drivetrain.reachedTurnDistance(sum, requiredLeftDist, requiredRightDist))
				{
					Drivetrain.goBackRight(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, -.585, -.325, .04);
				}
				else
				{
					currentState = 2;
				}
				break;
			case 2:
				Drivetrain.stop();
				break;
		}
	}
	
	public static void BL(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
				requiredRightDist = (Constants.testBig);
				requiredLeftDist = (Constants.testSmall);
				aimedRatio = ((requiredRightDist)/(requiredLeftDist));
				lValue = Math.abs(lValue);
				rValue = Math.abs(rValue);
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
				if(!Drivetrain.reachedTurnDistance(sum, requiredLeftDist, requiredRightDist))
				{
					Drivetrain.goBackLeft(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, -.325, -.585, .04);
				}
				else
				{
					currentState = 2;
				}
				break;
			case 2:
				Drivetrain.stop();
				break;
		}
	}
	
	
	
	public static void initialize()
	{
		Encoders.resetEncoders();
		currentState = 1;
		requiredLeftDist = 0;
		requiredRightDist = 0;
		requiredStraightDist = 0;
	}
}