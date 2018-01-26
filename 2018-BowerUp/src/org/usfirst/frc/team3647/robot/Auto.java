package org.usfirst.frc.team3647.robot;

import team3647ConstantsAndFunctions.Constants;
import team3647subsystems.Drivetrain;

public class Auto 
{
	static double aimedRatio, currentRatio;
	static double sum;
	static boolean withinRange;
	static double leftEncoder, rightEncoder;
	static double requiredLeftDist, requiredRightDist, requiredStraightDist = 0;
	static int currentState = 1;
	public static void test(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
				leftEncoder = lValue;
				rightEncoder = rValue;
				requiredLeftDist = (Constants.testSmall);
				requiredRightDist = (Constants.testBig);
				aimedRatio = ((requiredRightDist)/(requiredLeftDist));
				currentRatio = (((rightEncoder)/(leftEncoder))/aimedRatio);
				sum = (rightEncoder) + (leftEncoder);
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
					currentState = 2;
				}
				break;
			case 2:
				Drivetrain.drive.tankDrive(0, 0, false);
				break;
		}
	}
}
