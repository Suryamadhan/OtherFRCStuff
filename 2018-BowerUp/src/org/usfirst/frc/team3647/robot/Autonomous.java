package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Timer;
import team3647ConstantsAndFunctions.AutoConstants;
import team3647ConstantsAndFunctions.Constants;
import team3647ConstantsAndFunctions.Functions;
import team3647ConstantsAndFunctions.NewFunctions;
import team3647elevator.Elevator;
import team3647elevator.ElevatorLevel;
import team3647elevator.Intake;
import team3647pistons.intakeMechanism;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;

public class Autonomous 
{
	//Switch: Y direction: 168 inches, center of the robot
	//Switch: X direction: 55.5 inches, start far edge to end robot
	//Scale: Y direction: 324 inches, center of the robot
	//Scale: X direction: 42 inches, start far edge to end robot
	
	static int currentState;
	static double lSSpeed, rSSpeed, adjustmentConstant;
	static double []adjustmentValues = new double[2];
	static double prevLeftEncoder, prevRightEncoder, avg;
	static double requiredLeftDist, requiredRightDist;
	
	public static void rightSideBigJank(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 0 :
				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedStop())
				{
					Elevator.stopEleVader();
					ElevatorLevel.resetElevatorEncoders();
					currentState = 1;
				}
				else
				{
					Encoders.resetEncoders();
					Elevator.moveEleVader(-.2);
				}
				break;
			case 1:
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.scaleJankStraightRightSide - 1500))
				{
					avg = (lValue + rValue)/2.0;
					lSSpeed = Functions.straightInitialRightSideJank(avg);
					rSSpeed = Functions.straightInitialRightSideJank(avg);
					adjustmentValues = NewFunctions.adjustmentValues(lValue, rValue, false);
					Drivetrain.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1]);
				}
				else
				{
					currentState = 2;
				}
				break;
			case 2:
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.scaleJankStraightRightSide))
				{
					avg = (lValue + rValue)/2.0;
					lSSpeed = .2;
					rSSpeed = .2;
					adjustmentValues = NewFunctions.adjustmentValues(lValue, rValue, false);
					Drivetrain.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1]);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 20;
				}
				break;
			case 3:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				lSSpeed = 0;
				if(rValue < AutoConstants.scaleJankTurnToScaleRightSide)
				{
					rSSpeed = .35;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
				}
				else
				{
					Drivetrain.stop();
					Timer.delay(.2);
					currentState = 4;
				}
				break;
			case 4:
				if(ElevatorLevel.reachedScale())
				{
					Elevator.moveEleVader(Functions.stopToScale(ElevatorLevel.elevatorEncoderValue));
				}
				else
				{
					Elevator.stopEleVader();
					currentState = 5;
				}
				break;
			case 5:
//				if(Intake.bannerSensor.get())
//				{
//					Intake.shootCube();
//				}
//				else
//				{
//					Timer.delay(.3);
//					currentState = 6;
//				}
				Intake.shootCube();
				Encoders.resetEncoders();
				Timer.delay(1);
				currentState = 14;
				break;
			case 14:
				if(ElevatorLevel.reachedStop())
				{
					Elevator.stopEleVader();
					currentState = 6;
				}
				else
				{
					Intake.stopIntake();
					Elevator.moveEleVader(-.3);
				}
				break;
			case 6:
				lSSpeed = 0;
				if(Math.abs(rValue) < AutoConstants.scaleJankTurnToScaleRightSide)
				{
					rSSpeed = -.3;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 7;
				}
				break;
			case 7:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.backUpAfterFirstScaleRightSide - 1500))
				{
					avg = (Math.abs(rValue) + Math.abs(lValue))/2.0;
					lSSpeed = Functions.backUpAfterFirstCubeRightSideJank(avg);
					rSSpeed = Functions.backUpAfterFirstCubeRightSideJank(avg);
					adjustmentValues = NewFunctions.adjustmentValues(lValue, rValue, false);
					Drivetrain.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1]);
				}
				else
				{
					currentState = 8;
				}
				break;
			case 8:
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.backUpAfterFirstScaleRightSide))
				{
					avg = (Math.abs(rValue) + Math.abs(lValue))/2.0;
					lSSpeed = -.2;
					rSSpeed = -.2;
					adjustmentValues = NewFunctions.adjustmentValues(lValue, rValue, false);
					Drivetrain.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1]);
				}
				else
				{
					Drivetrain.stop();
					Timer.delay(.2);
					currentState = 9;
				}
				break;
			case 9:
				if(lValue == 0 && rValue == 0)
				{
					currentState = 10;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 10:
				if(Functions.uTurnForFirstCubeRightSideBigJank(rValue) != 0)
				{
					rSSpeed = Functions.uTurnForFirstCubeRightSideBigJank(rValue);
					lSSpeed = rSSpeed/AutoConstants.UTurntoFirstCubeJankRightSideRatio;
					Drivetrain.goStraightLeft(lValue, rValue, AutoConstants.bigUTurntoFirstCubeJankRightSide/AutoConstants.UTurntoFirstCubeJankRightSideRatio, AutoConstants.bigUTurntoFirstCubeJankRightSide, lSSpeed, rSSpeed, .06);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 11;
				}
				break;
			case 11:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				intakeMechanism.openIntake();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.StraightToFirstCubeJankRightSide))
				{
					lSSpeed = .4;
					rSSpeed = .4;
					adjustmentValues = NewFunctions.adjustmentValues(lValue, rValue, true);
					Drivetrain.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1]);
					Intake.pickUpCube();
				}
				else
				{
					currentState = 12;
				}
				break;
			case 12:
				Intake.stopIntake();
				intakeMechanism.closeIntake();
				Drivetrain.stop();
				Timer.delay(.2);
				currentState = 13;
				break;
			case 13:
				if(ElevatorLevel.reachedSwitch())
				{
					Elevator.stopEleVader();
					currentState = 15;
				}
				else
				{
					Elevator.moveEleVader(Functions.stopToSwitch(ElevatorLevel.elevatorEncoderValue));
				}
				break;
				//no case 14
			case 15:
				Timer.delay(.2);
				currentState = 16;
				break;
			case 16:
//				if(Intake.bannerSensor.get())
//				{
//					Intake.shootCube();
//				}
//				else
//				{
//					Timer.delay(.3);
//					currentState = 6;
//				}
				Intake.shootCube();
				//shoot towards the right side of the robot
				Encoders.resetEncoders();
				Timer.delay(.7);
				currentState = 17;
				break;
			case 17:
				Intake.stopIntake();
				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedStop())
				{
					Elevator.stopEleVader();
					ElevatorLevel.resetElevatorEncoders();
					currentState = 18;
				}
				else
				{
					Encoders.resetEncoders();
					Elevator.moveEleVader(Functions.switchToStop(ElevatorLevel.elevatorEncoderValue));
				}
				break;
			case 18:
				if(Functions.backUpAfterPickUpFirstCubeRightSideBigJank(rValue, false) !=0)
				{
					rSSpeed = Functions.backUpAfterPickUpFirstCubeRightSideBigJank(rValue, false);
					lSSpeed = rSSpeed/AutoConstants.BackUpAfterFirstCubeRatioJankRightSide;
					Drivetrain.goBackLeft(lValue, rValue, AutoConstants.halfCircleTurnForCubesRightSideJank, AutoConstants.halfCircleTurnForCubesRightSideJank/AutoConstants.BackUpAfterFirstCubeRatioJankRightSide, lSSpeed, rSSpeed, .05);
				}
				else
				{
					currentState = 19;
				}
				break;
			case 19:
				Drivetrain.stop();
				Timer.delay(.2);
				currentState = 20;
				break;
			case 20:
				Drivetrain.stop();
				break;
				
		}
	}
	
	public static void testDrift(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 0:
				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedStop())
				{
					Elevator.stopEleVader();
					ElevatorLevel.resetElevatorEncoders();
					currentState = 1;
				}
				else
				{
					Encoders.resetEncoders();
					Elevator.moveEleVader(-.2);
				}
				break;
			case 1:
				Drivetrain.tankDrive(.9, .9);
				Timer.delay(2.5);
				currentState = 2;
				break;
			case 2:
				Drivetrain.stop();
				prevLeftEncoder = lValue;
				prevRightEncoder = rValue;
				Timer.delay(1.2);
				currentState = 3;
				break;
			case 3:
				System.out.println("lValue" + (lValue-prevLeftEncoder));
				System.out.println("rValue" + (rValue-prevRightEncoder));
				currentState = 4;
				break;
			case 4:
				Drivetrain.stop();
				Elevator.stopEleVader();
				break;
		}
	}
	
	public static void MSRSWF(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 0:
				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedStop())
				{
					Elevator.stopEleVader();
					ElevatorLevel.resetElevatorEncoders();
					currentState = 1;
				}
				else
				{
					Encoders.resetEncoders();
					Elevator.moveEleVader(-.2);
				}
				break;
			case 1: 
				lSSpeed = NewFunctions.msrswfLeftSpeed(lValue, rValue, 1);
				rSSpeed = NewFunctions.msrswfRightSpeed(lValue, rValue, 1);
				if(lSSpeed == 0 && rSSpeed == 0)
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 2;
				}
				else
				{
					adjustmentValues = NewFunctions.msrswfadjustment(lValue, rValue, 1);
					Drivetrain.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1]);
				}
				break;
			case 2:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				lSSpeed = NewFunctions.msrswfLeftSpeed(lValue, rValue, 2);
				rSSpeed = NewFunctions.msrswfRightSpeed(lValue, rValue, 2);
				if(lSSpeed == 0 && rSSpeed == 0)
				{
					currentState = 3;
				}
				else
				{
					adjustmentValues = NewFunctions.msrswfadjustment(lValue, rValue, 2);
					Drivetrain.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1]);
				}
				break;
			case 3:
				Drivetrain.stop();
				if(lValue == 0 && rValue == 0)
				{
					currentState = 4;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 4:
				if(ElevatorLevel.reachedSwitch())
				{
					Elevator.stopEleVader();
					currentState = 5;
				}
				else
				{
					Elevator.moveEleVader(.4);
				}
				break;
			case 5:
				Intake.shootCube();
				Timer.delay(1);
				currentState = 6;
//				if(Intake.bannerSensor.get())
//				{
//					Intake.shootCube();
//				}
//				else
//				{
//					Timer.delay(.4);
//					oof.stopIntake();
//					currentState = 6;
//				}
				break;
			case 6:
				Intake.stopIntake();
				Elevator.stopEleVader();
				Drivetrain.stop();
				break;
		}
	}
	
	public static void MSLSWF(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 0:
				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedStop())
				{
					Elevator.stopEleVader();
					ElevatorLevel.resetElevatorEncoders();
					currentState = 1;
				}
				else
				{
					Encoders.resetEncoders();
					Elevator.moveEleVader(-.2);
				}
				break;
			case 1:
				lSSpeed = NewFunctions.mslsswfLeftSpeed(lValue, rValue, 2);
				rSSpeed = NewFunctions.mslsswfRightSpeed(lValue, rValue, 2);
				if(lSSpeed == 0 && rSSpeed == 0)
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 2;
				}
				else
				{
					adjustmentValues = NewFunctions.mslsswfadjustment(lValue, rValue, 2);
					Drivetrain.drive.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1], false);
				}
				break;
			case 2:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				lSSpeed = NewFunctions.mslsswfLeftSpeed(lValue, rValue, 3);
				rSSpeed = NewFunctions.mslsswfRightSpeed(lValue, rValue, 3);
				if(lSSpeed == 0 && rSSpeed == 0)
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 3;
				}
				else
				{
					adjustmentValues = NewFunctions.mslsswfadjustment(lValue, rValue, 3);
					Drivetrain.drive.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1], false);
				}
				break;
			case 3:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				lSSpeed = NewFunctions.mslsswfLeftSpeed(lValue, rValue, 4);
				rSSpeed = NewFunctions.mslsswfRightSpeed(lValue, rValue, 4);
				if(lSSpeed == 0 && rSSpeed == 0)
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 4;
				}
				else
				{
					adjustmentValues = NewFunctions.mslsswfadjustment(lValue, rValue, 4);
					Drivetrain.drive.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1], false);
				}
				break;
			case 4:
				Drivetrain.stop();
				if(lValue == 0 && rValue == 0)
				{
					currentState = 5;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 5:
				if(ElevatorLevel.reachedSwitch())
				{
					Elevator.stopEleVader();
					currentState = 6;
				}
				else
				{
					Elevator.moveEleVader(.4);
				}
				break;
			case 6:
				Intake.shootCube();
				Timer.delay(1);
				currentState = 7;
//				if(Intake.bannerSensor.get())
//				{
//					Intake.shootCube();
//				}
//				else
//				{
//					Timer.delay(.4);
//					oof.stopIntake();
//					currentState = 6;
//				}
				break;
			case 7:
				Intake.stopIntake();
				Elevator.stopEleVader();
				Drivetrain.stop();
				break;
		}
	}
	
	public static void LSLSCF(double lValue, double rValue, double step)
	{
		switch(currentState)
		{
			case 0:
				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedStop())
				{
					Elevator.stopEleVader();
					ElevatorLevel.resetElevatorEncoders();
					currentState = 1;
				}
				else
				{
					Encoders.resetEncoders();
					Elevator.moveEleVader(-.2);
				}
				break;
			case 1:
				if(!Drivetrain.reachedDistance(lValue, rValue, NewFunctions.lslscfstraight - 8))
				{
					lSSpeed = rSSpeed = NewFunctions.lslscfStraightSpeed(lValue, rValue);
					adjustmentValues = NewFunctions.goStraight(lValue, rValue);
					Drivetrain.drive.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1], false);
				}
				else if(!Drivetrain.reachedDistance(lValue, rValue, NewFunctions.lslscfstraight))
				{
					lSSpeed = rSSpeed = .2;
					adjustmentValues = NewFunctions.goStraight(lValue, rValue);
					Drivetrain.drive.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1], false);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 2;
				}
				break;
			case 2:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				if(!Drivetrain.reachedTurnDistance(lValue+rValue, NewFunctions.lslscfbigTurn, NewFunctions.lslscfsmallTurn))
				{
					lSSpeed = NewFunctions.lslscfBigTurnSpeed(lValue);
					rSSpeed = lSSpeed/NewFunctions.lslscffirstTurnRatio;
					adjustmentConstant = Constants.adjustmentConstant(lSSpeed);
					Drivetrain.goStraightRight(lValue, rValue, NewFunctions.lslscfbigTurn, NewFunctions.lslscfsmallTurn, lSSpeed, rSSpeed, adjustmentConstant);
				}
				else
				{
					currentState = 3;
				}
				break;
			case 3:
				Drivetrain.stop();
				if(lValue == 0 && rValue == 0)
				{
					currentState = 4;
				}
				else
				{
					Encoders.resetEncoders();
				}
				break;
			case 4:
				if(ElevatorLevel.reachedSwitch())
				{
					Elevator.stopEleVader();
					currentState = 5;
				}
				else
				{
					Elevator.moveEleVader(.4);
				}
				break;
			case 5:
				Intake.shootCube();
				Timer.delay(1);
				currentState = 6;
//				if(Intake.bannerSensor.get())
//				{
//					Intake.shootCube();
//				}
//				else
//				{
//					Timer.delay(.4);
//					oof.stopIntake();
//					currentState = 6;
//				}
				break;
			case 6:
				Intake.stopIntake();
				Elevator.stopEleVader();
				Drivetrain.stop();
				break;
		}
	}
	
	public static void initialize()
	{
		Encoders.resetEncoders();
		Intake.stopIntake();
		Elevator.stopEleVader();
		intakeMechanism.closeIntake();
		Elevator.elevatorState = 0;
		currentState = 0;
	}

}
