package org.usfirst.frc.team3647.robot;

import team3647ConstantsAndFunctions.Constants;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;

public class Auto 
{
	static double aimedRatio, currentRatio;
	static double sum;
	static boolean withinRange;
	static double requiredLeftDist, requiredRightDist, requiredStraightDist = 0;
	static int currentState = 1;
	
	
	public static void test(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
<<<<<<< HEAD
=======
				requiredStraightDist = Constants.testStright -1400;
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
					currentState = 3;
				}
				break;
			case 3:
				leftEncoder = lValue;
				rightEncoder = rValue;
>>>>>>> 40d0ac71d226e53f69e60a2582543a6f4141f760
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
					System.out.println(currentRatio);
					Drivetrain.goStraightLeft(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, .325, .585, .05);
				}
				else
				{
					currentState = 4;
				}
				break;
			case 4:
				Drivetrain.drive.tankDrive(0, 0, false);
				break;
		}
	}
}
