package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Timer;
import team3647ConstantsAndFunctions.Constants;
import team3647ConstantsAndFunctions.Functions;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;

public class Auto 
{
	static double aimedRatio, currentRatio;
	static double sum;
	static boolean withinRange;
	static double requiredLeftDist, requiredRightDist, requiredStraightDist = 0;
	static int currentState;
	
	static double prevLeftEncoder, prevRightEncoder;
	static String gameData, auto;
	
	static double lSSpeed, rSSpeed, lAdjustment, rAdjustment, error;
	static boolean lError, rError;
	static double []adjustmentValues = new double[2];
	
	public static void MSRRSW1(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
				sum = lValue + rValue;
				if(sum < (Constants.MSRRSWfirstbigTurn + Constants.MSRRSWfirstsmallTurn + Constants.MSRRSWsecondbigTurn + Constants.MSRRSWsecondsmallTurn))
				{
					lSSpeed = Functions.MSRRSWsupposedLeftSpeed1(lValue);
					rSSpeed = Functions.MSRRSWsupposedRightSpeed1(rValue);
					adjustmentValues = Functions.MSRRSWcorrection1(lValue, rValue);
					lAdjustment = adjustmentValues[0];
					rAdjustment = adjustmentValues[1];
					Drivetrain.tankDrive(lSSpeed + lAdjustment, rSSpeed +rAdjustment);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 4;
				}
				break;
			case 2:
				error = Math.abs(prevLeftEncoder - prevRightEncoder);
				if(prevRightEncoder > prevLeftEncoder)
				{
					rError = true;
					lError = false;
					currentState = 3;
				}
				else if(prevRightEncoder < prevLeftEncoder)
				{
					rError = false;
					lError = true;
					currentState = 3;
				}
				else
				{
					rError = false;
					lError = false;
					currentState = 3;
				}
				break;
			case 3:
				if(lError)
				{
					lValue = lValue - prevLeftEncoder + error;
					rValue = rValue - prevRightEncoder;
					if(!Drivetrain.reachedDistance(lValue, rValue, Constants.MSRRSWStraight))
					{
						Drivetrain.driveForward(lValue, rValue, .6, .06);
					}
					else
					{
						currentState = 4;
					}
				}
				else if(rError)
				{
					lValue = lValue - prevLeftEncoder;
					rValue = rValue - prevRightEncoder + error;
					if(!Drivetrain.reachedDistance(lValue, rValue, Constants.MSRRSWStraight))
					{
						Drivetrain.driveForward(lValue, rValue, .6, .06);
					}
					else
					{
						currentState = 4;
					}
				}
				else
				{
					lValue = lValue - prevLeftEncoder;
					rValue = rValue - prevRightEncoder;
					if(!Drivetrain.reachedDistance(lValue, rValue, Constants.MSRRSWStraight))
					{
						Drivetrain.driveForward(lValue, rValue, .6, .06);
					}
					else
					{
						currentState = 4;
					}
				}
				break;
			case 4:
				Drivetrain.stop();
				break;
		}
		Encoders.testEncoders();	
	}
	
	public static void testTurn(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
				requiredRightDist = (Constants.testSmall - 1440);
				requiredLeftDist = (Constants.testBig - 2592);
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
					Drivetrain.goStraightRight(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, .36, .2, .03);
				}
				else
				{
					requiredRightDist = 0;
					requiredLeftDist = 0;
					currentState = 3;
				}
				break;
			case 3:
				Drivetrain.stop();
				Timer.delay(.3);
				Encoders.resetEncoders();
				Timer.delay(.3);
				currentState = 4;
				break;
			case 4:
				requiredRightDist = (Constants.testSmall - 1440);
				requiredLeftDist = (Constants.testBig - 2592);
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
					currentState = 5;
				}
				break;
			case 5:
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
					Drivetrain.goBackRight(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, -.36, -.2, .04);
				}
				else
				{
					requiredRightDist = 0;
					requiredLeftDist = 0;
					currentState = 6;
				}
				break;
			case 6:
				Drivetrain.stop();
				break;
		}
		
	}
	
	public static void testRight(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
				requiredStraightDist = Constants.testStright -1440;
				if(!Drivetrain.reachedDistance(lValue, rValue, requiredStraightDist))
				{
					Drivetrain.driveForward(lValue, rValue, .6, .06);
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
					Drivetrain.driveForward(lValue, rValue, .1, .02);
				}
				else
				{
					requiredStraightDist = 0;
					currentState = 3;
				}
				break;
			case 3:
				requiredRightDist = (Constants.testSmall - 1440);
				requiredLeftDist = (Constants.testBig - 2592);
				aimedRatio = ((requiredLeftDist)/(requiredRightDist));
				lValue = lValue - Constants.testStright;
				rValue = rValue - Constants.testStright;
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
					currentState = 4;
				}
				break;
			case 4:
				requiredRightDist = (Constants.testSmall);
				requiredLeftDist = (Constants.testBig);
				aimedRatio = ((requiredLeftDist)/(requiredRightDist));
				lValue = lValue - Constants.testStright;
				rValue = rValue - Constants.testStright;
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
					Drivetrain.goStraightRight(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, .36, .2, .03);
				}
				else
				{
					requiredRightDist = 0;
					requiredLeftDist = 0;
					currentState = 5;
				}
				break;
			case 5:
				Drivetrain.stop();
				Encoders.resetEncoders();
				Timer.delay(.3);
				currentState = 6;
				break;
			case 6:
				requiredRightDist = (Constants.testSmall - 1440);
				requiredLeftDist = (Constants.testBig - 2592);
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
					currentState = 7;
				}
				break;
			case 7:
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
					Drivetrain.goBackRight(currentRatio, withinRange, sum, requiredLeftDist, requiredRightDist, -.36, -.2, .04);
				}
				else
				{
					requiredRightDist = 0;
					requiredLeftDist = 0;
					currentState = 8;
				}
				break;
			case 8:
				requiredStraightDist = 1440;
				lValue = lValue + Constants.testBig;
				rValue = rValue + Constants.testSmall;
				if(!Drivetrain.reachedDistance(lValue, rValue, requiredStraightDist))
				{
					Drivetrain.driveBackward(lValue, rValue, -.4, .03);
				}
				else
				{
					currentState =9;
				}
				break;
			case 9:
				requiredStraightDist = Constants.testStright - 1440;
				lValue = lValue + Constants.testBig;
				rValue = rValue + Constants.testSmall;
				if(!Drivetrain.reachedDistance(lValue, rValue, requiredStraightDist))
				{
					Drivetrain.driveBackward(lValue, rValue, -.6, .06);
				}
				else
				{
					currentState =10;
				}
				break;
			case 10:
				requiredStraightDist = Constants.testStright;
				lValue = lValue + Constants.testBig;
				rValue = rValue + Constants.testSmall;
				if(!Drivetrain.reachedDistance(lValue, rValue, requiredStraightDist))
				{
					Drivetrain.driveBackward(lValue, rValue, -.2, 04);
				}
				else
				{
					currentState =11;
				}
				break;
			case 11:
				Drivetrain.stop();
				break;
		}
	}
	
	public static void test(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 1:
				requiredStraightDist = Constants.testStright -1440;
				if(!Drivetrain.reachedDistance(lValue, rValue, requiredStraightDist))
				{
					Drivetrain.driveForward(lValue, rValue, .6, .06);
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
					Drivetrain.driveForward(lValue, rValue, .1, .02);
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
	
	public static void runAuto(double lValue, double rValue)
	{
		gameData = "RRR";//
		//gameData = DriverStation.getInstance().getGameSpecificMessage();
		auto += gameData.charAt(0);//
		auto += gameData.charAt(1);//
		switch(auto)
		{
			case "LL":
				MSRRSW1(lValue, rValue);//
				break;
			case "RR":
				MSRRSW1(lValue, rValue);
				break;
			case "RL":
				MSRRSW1(lValue, rValue);//
				break;
			case "LR":
				MSRRSW1(lValue, rValue);//
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