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
	static double lSSpeed, rSSpeed, adjustmentConstant, speed;
	static double []adjustmentValues = new double[2];
	static double prevLeftEncoder, prevRightEncoder, avg;
	static double requiredLeftDist, requiredRightDist;
	
	public static void lr(double lValue, double rValue)
	{
		switch(currentState)
		{
			case 0:
				prevLeftEncoder = 0;
				prevRightEncoder = 0;
				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedStop())
				{
					Elevator.stopEleVader();
					ElevatorLevel.resetElevatorEncoders();
					currentState = 1;
				}
				else
				{
					Encoders.resetEncoders();
					Elevator.moveEleVader(-.25);
				}
				break;
			case 1:
				if(ElevatorLevel.reachedPickUp())
				{
					Elevator.stopEleVader();
					currentState = 11;
				}
				else
				{
					Elevator.moveEleVader(.5);
				}
				break;
			case 2:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.lrStraightToScale - 1600))
				{
					avg = (lValue + rValue)/2.0;
					speed = Functions.lrStarightToScale(avg);
					Drivetrain.driveForw(lValue, rValue, speed);
				}
				else
				{
					currentState = 3;
				}
				break;
			case 3:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.lrStraightToScale))
				{
					speed = .2;
					Drivetrain.driveForw(lValue, rValue, speed);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 4;
				}
				break;
			case 4:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				lSSpeed = 0;
				if(rValue < AutoConstants.lrScaleTurn && !ElevatorLevel.reachedScale())
				{
					rSSpeed = .35;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
					Elevator.moveEleVader(Functions.pickUpToScale(ElevatorLevel.elevatorEncoderValue));
				}
				else if(rValue >= AutoConstants.lrScaleTurn && !ElevatorLevel.reachedScale())
				{
					Drivetrain.stop();
					if(ElevatorLevel.elevatorEncoderValue < Constants.scale)
					{
						Elevator.moveEleVader(Functions.pickUpToScale(ElevatorLevel.elevatorEncoderValue));
					}
					else
					{
						Elevator.moveEleVader(-.2);
					}
						
				}
				else if(rValue < AutoConstants.scaleJankTurnToScaleRightSide && ElevatorLevel.reachedScale())
				{
					rSSpeed = .7;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
					ElevatorLevel.maintainScalePosition();
				}
				else
				{
					Drivetrain.stop();
					ElevatorLevel.maintainScalePosition();
					Timer.delay(.2);
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
//					culrentState = 6;
//				}
				ElevatorLevel.maintainScalePosition();
				Intake.shootCube();
				Encoders.resetEncoders();
				Timer.delay(1);
				currentState = 6;
				break;
			case 6:
				Intake.stopIntake();
				lSSpeed = 0;
				if(Math.abs(rValue) < AutoConstants.lrBackTurnAfterSwitch && !ElevatorLevel.reachedPickUp())
				{
					rSSpeed = -.55;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
					Elevator.moveEleVader(Functions.scaleToPickUp(ElevatorLevel.elevatorEncoderValue));
				}
				else if(Math.abs(rValue) >= AutoConstants.lrBackTurnAfterSwitch && !ElevatorLevel.reachedPickUp())
				{
					Drivetrain.stop();
					Elevator.moveEleVader(Functions.scaleToPickUp(ElevatorLevel.elevatorEncoderValue));
				}
				else if(Math.abs(rValue) < AutoConstants.lrBackTurnAfterSwitch && ElevatorLevel.reachedPickUp())
				{
					rSSpeed = -.55;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
					Elevator.stopEleVader();
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 14;
				}
				break;
			case 7:
				ElevatorLevel.maintainPickUpPosition();
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.lrBackTurnAfterSwitch - 1500))
				{
					Drivetrain.driveBack(lValue, rValue, -.8);
				}
				else
				{
					currentState = 8;
				}
				break;
			case 8:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.lrBackTurnAfterSwitch))
				{
					Drivetrain.driveBack(lValue, rValue, -.2);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 9;
				}
				break;
			case 9:
				ElevatorLevel.maintainPickUpPosition();
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				rSSpeed = 0;
				lValue = Math.abs(lValue);
				if(lValue < AutoConstants.lrbackUpToWallTurnDist)
				{
					lSSpeed = -.6;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
				}
				else
				{
					currentState = 14;
				}
				break;
			case 10:
				ElevatorLevel.maintainPickUpPosition();
				Drivetrain.stop();
				Timer.delay(.1);
				currentState = 11;
				break;
			case 11:
				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedPickUp())
				{
					Elevator.stopEleVader();
					ElevatorLevel.resetElevatorEncoders();
					currentState = 12;
				}
				else
				{
					Encoders.resetEncoders();
					ElevatorLevel.maintainPickUpPosition();
				}
				break;
			case 12:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.lrStraightToCube - 1600))
				{
					Drivetrain.driveForw(lValue, rValue, .8);
				}
				else
				{
					currentState = 12;
				}
				break;
			case 13:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.lrStraightToCube))
				{
					speed = .2;
					Drivetrain.driveForw(lValue, rValue, speed);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 14;
				}
				break;
			case 14:
				ElevatorLevel.maintainPickUpPosition();
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				if(Functions.lrPickUpCube(rValue) != 0)
				{
					rSSpeed = Functions.lrPickUpCube(rValue);
					lSSpeed = rSSpeed/AutoConstants.lrCubeTurnRatio;
					Drivetrain.goStraightLeft(lValue, rValue, AutoConstants.lrCubeTurnRatio, lSSpeed, rSSpeed, .06);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 15;
				}
				break;
			case 15:
				Drivetrain.stop();
				break;
		}
	}
	
	public static void rr(double lValue, double rValue)
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
					Elevator.moveEleVader(-.25);
				}
				break;
			case 1:
				if(ElevatorLevel.reachedPickUp())
				{
					Elevator.stopEleVader();
					currentState = 2;
				}
				else
				{
					Elevator.moveEleVader(.5);
				}
				break;
			case 2:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.rrStraightToScale - 1800))
				{
					avg = (lValue + rValue)/2.0;
					speed = Functions.rrStarightToScale(avg);
					Drivetrain.driveForw(lValue, rValue, speed);
				}
				else
				{
					currentState = 3;
				}
				break;
			case 3:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.rrStraightToScale))
				{
					speed = .2;
					Drivetrain.driveForw(lValue, rValue, speed);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 4;
				}
				break;
			case 4:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				lSSpeed = 0;
				if(rValue < AutoConstants.rrScaleTurn && !ElevatorLevel.reachedScale())
				{
					rSSpeed = .7;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
					Elevator.moveEleVader(Functions.pickUpToScale(ElevatorLevel.elevatorEncoderValue));
				}
				else if(rValue >= AutoConstants.rrScaleTurn && !ElevatorLevel.reachedScale())
				{
					Drivetrain.stop();
					if(ElevatorLevel.elevatorEncoderValue < Constants.scale)
					{
						Elevator.moveEleVader(Functions.pickUpToScale(ElevatorLevel.elevatorEncoderValue));
					}
					else
					{
						Elevator.moveEleVader(-.2);
					}
						
				}
				else if(rValue < AutoConstants.scaleJankTurnToScaleRightSide && ElevatorLevel.reachedScale())
				{
					rSSpeed = .7;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
					ElevatorLevel.maintainScalePosition();
				}
				else
				{
					Drivetrain.stop();
					ElevatorLevel.maintainScalePosition();
					Timer.delay(.2);
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
				ElevatorLevel.maintainScalePosition();
				Intake.shootCube();
				Encoders.resetEncoders();
				Timer.delay(1);
				currentState = 6;
				break;
			case 6:
				Intake.stopIntake();
				lSSpeed = 0;
				if(Math.abs(rValue) < AutoConstants.rrBackTurnAfterSwitch && !ElevatorLevel.reachedPickUp())
				{
					rSSpeed = -.55;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
					Elevator.moveEleVader(Functions.scaleToPickUp(ElevatorLevel.elevatorEncoderValue));
				}
				else if(Math.abs(rValue) >= AutoConstants.rrBackTurnAfterSwitch && !ElevatorLevel.reachedPickUp())
				{
					Drivetrain.stop();
					Elevator.moveEleVader(Functions.scaleToPickUp(ElevatorLevel.elevatorEncoderValue));
				}
				else if(Math.abs(rValue) < AutoConstants.rrBackTurnAfterSwitch && ElevatorLevel.reachedPickUp())
				{
					rSSpeed = -.55;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
					Elevator.stopEleVader();
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 7;
				}
				break;
			case 7:
				ElevatorLevel.maintainPickUpPosition();
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.rrBackTurnAfterSwitch - 1500))
				{
					Drivetrain.driveBack(lValue, rValue, -.8);
				}
				else
				{
					currentState = 8;
				}
				break;
			case 8:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.rrBackTurnAfterSwitch))
				{
					Drivetrain.driveBack(lValue, rValue, -.2);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 9;
				}
				break;
			case 9:
				ElevatorLevel.maintainPickUpPosition();
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				rSSpeed = 0;
				lValue = Math.abs(lValue);
				if(lValue < AutoConstants.rrbackUpToWallTurnDist)
				{
					lSSpeed = -.6;
					Drivetrain.tankDrive(lSSpeed, rSSpeed);
				}
				else
				{
					currentState = 19;
				}
				break;
			case 10:
				ElevatorLevel.maintainPickUpPosition();
				Drivetrain.stop();
				Timer.delay(.35);
				currentState = 11;
				break;
			case 11:
				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedPickUp())
				{
					Elevator.stopEleVader();
					ElevatorLevel.resetElevatorEncoders();
					currentState = 12;
				}
				else
				{
					Encoders.resetEncoders();
					ElevatorLevel.maintainPickUpPosition();
				}
				break;
			case 12:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.rrStraightToCube - 1600))
				{
					Drivetrain.driveForw(lValue, rValue, .8);
				}
				else
				{
					currentState = 13;
				}
				break;
			case 13:
				ElevatorLevel.maintainPickUpPosition();
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.rrStraightToCube))
				{
					speed = .2;
					Drivetrain.driveForw(lValue, rValue, speed);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 14;
				}
				break;
			case 14:
				ElevatorLevel.maintainPickUpPosition();
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				if(Functions.rrPickUpCube(rValue) != 0)
				{
					rSSpeed = Functions.rrPickUpCube(rValue);
					lSSpeed = rSSpeed/AutoConstants.rrCubeTurnRatio;
					Drivetrain.goStraightLeft(lValue, rValue, AutoConstants.rrCubeTurnRatio, lSSpeed, rSSpeed, .06);
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 15;
				}
				break;
			case 15:
				ElevatorLevel.maintainPickUpPosition();
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				if(!Drivetrain.reachedDistance(lValue, rValue, 2000))
				{
					Intake.pickUpCube();
					speed = .7;
					Drivetrain.driveForw(lValue, rValue, speed);
				}
				else
				{
					Drivetrain.stop();
					currentState = 16;
				}
				break;
			case 16:
				Timer.delay(.2);
				Encoders.resetEncoders();
				Timer.delay(.1);
				currentState = 17;
				break;
			case 17:
				avg = (lValue + rValue)/2.0;
				if(avg < 2000 && !ElevatorLevel.reachedSwitch())
				{
					Elevator.moveEleVader(Functions.stopToSwitch(ElevatorLevel.elevatorEncoderValue));
					Drivetrain.driveForw(lValue, rValue, .5);
				}
				else if(avg < 2000 && ElevatorLevel.reachedSwitch())
				{
					Drivetrain.driveForw(lValue, rValue, .5);
					Elevator.stopEleVader();
				}
				else if(avg > 2000 && !ElevatorLevel.reachedSwitch())
				{
					Drivetrain.stop();
					Elevator.moveEleVader(Functions.stopToSwitch(ElevatorLevel.elevatorEncoderValue));
				}
				else
				{
					Drivetrain.stop();
					Elevator.stopEleVader();
					currentState = 18;
				}
				break;
			case 18:
				Intake.shootCube();
				Timer.delay(1);
				currentState = 19;
				break;
			case 19:
				Intake.stopIntake();
				Drivetrain.stop();
				Elevator.stopEleVader();
				break;
		}
	}
	
	public static void middleSideLeftAuto(double lValue, double rValue)//switch
	{
		//Before Straight: Make sure X is 72 inches to the left
		//After Straight: Make sure Y is 140 inches at end.
		
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
				if(Functions.firstTurnSpeedForMSlSW(rValue) == 0)
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 2;
				}
				else
				{
					rSSpeed = Functions.firstTurnSpeedForMSlSW(rValue);
					lSSpeed = rSSpeed/AutoConstants.ratioForFirstTurnMSLSW;
					Drivetrain.goStraightLeft(lValue, rValue, AutoConstants.ratioForFirstTurnMSLSW, lSSpeed, rSSpeed, .06);
				}
				break;
			case 2:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				System.out.println(lValue);
				if(Functions.secondTurnSpeedForMSlSW(lValue) == 0)
				{
					currentState = 3;
				}
				else
				{
					lSSpeed = Functions.secondTurnSpeedForMSlSW(rValue);
					rSSpeed = lSSpeed/AutoConstants.ratioForSecondTurnMSLSW;
					Drivetrain.goStraightRight(lValue, rValue, AutoConstants.ratioForSecondTurnMSLSW, lSSpeed, rSSpeed, .06);
				}
				break;
			case 3:
				Drivetrain.stop();
				if(ElevatorLevel.reachedSwitch())
				{
					Elevator.stopEleVader();
					Encoders.resetEncoders();
					Timer.delay(.2);
					currentState = 4;
				}
				else
				{
					Elevator.moveEleVader(Functions.stopToSwitch(ElevatorLevel.elevatorEncoderValue));
				}
				break;
			case 4:
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.straightForSwitchMSLSW))
				{
					Drivetrain.driveForw(lValue, rValue, .5);
				}
				else
				{
					currentState = 5;
				}
				break;
			case 5:
				Drivetrain.stop();
				Timer.delay(.2);
				Intake.shootCube();
				Timer.delay(1);
				currentState = 6;
				break;
			case 6:
				Drivetrain.stop();
				Intake.stopIntake();
				break;
		}
	}
	
	public static void middleSideRightAuto(double lValue, double rValue)//switch
	{
		switch(currentState)
		{
			//Check after curves: 36 in at the end of all the turns
			//At end around 140 inches
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
				if(Functions.firstTurnSpeedForMSRSW(lValue) == 0)
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 2;
				}
				else
				{
					lSSpeed = Functions.firstTurnSpeedForMSRSW(lValue);
					rSSpeed = lSSpeed/AutoConstants.ratioForFirstTurnMSRSW;
					Drivetrain.goStraightRight(lValue, rValue, AutoConstants.ratioForFirstTurnMSRSW, lSSpeed, rSSpeed, .06);
				}
				break;
			case 2:
				lValue-=prevLeftEncoder;
				rValue-=prevRightEncoder;
				if(rValue < AutoConstants.secondBigOneWheelCurveMSRSW)
				{
					Drivetrain.tankDrive(0, Functions.secondTurnSpeedForMSRSW(rValue));
				}
				else
				{
					prevLeftEncoder = lValue;
					prevRightEncoder = rValue;
					currentState = 3;
				}
				break;
			case 3:
				Drivetrain.stop();
				if(ElevatorLevel.reachedSwitch())
				{
					Elevator.stopEleVader();
					Encoders.resetEncoders();
					Timer.delay(.2);
					currentState = 4;
				}
				else
				{
					Elevator.moveEleVader(Functions.stopToSwitch(ElevatorLevel.elevatorEncoderValue));
				}
				break;
			case 4:
				if(!Drivetrain.reachedDistance(lValue, rValue, AutoConstants.straightForSwitchMSRSW))
				{
					Drivetrain.driveForw(lValue, rValue, .6);
				}
				else
				{
					currentState = 5;
				}
				break;
			case 5:
				Drivetrain.stop();
				Timer.delay(.2);
				Intake.shootCube();
				Timer.delay(1);
				currentState = 5;
				break;
			case 6:
				Drivetrain.stop();
				Intake.stopIntake();
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
		prevLeftEncoder = 0;
		prevRightEncoder = 0;
		currentState = 0;
	}

}
