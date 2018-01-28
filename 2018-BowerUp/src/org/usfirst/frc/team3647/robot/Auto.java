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
				requiredStraightDist = Constants.testStright -2400;
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
				requiredStraightDist = Constants.testStright-1000;
				if(!Drivetrain.reachedDistance(lValue, rValue, requiredStraightDist))
				{
					Drivetrain.driveForward(lValue, rValue, .1);
				}
				else
				{
					Encoders.resetEncoders();
					Timer.delay(.2);
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
					currentState = 4;
				}
				break;
			case 4:
				Drivetrain.drive.tankDrive(0, 0, false);
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
				Drivetrain.drive.tankDrive(0, 0, false);
				break;
		}
	}
}
