//package org.usfirst.frc.team3647.robot;
//
//import edu.wpi.first.wpilibj.Timer;
//import team3647ConstantsAndFunctions.NewFunctions;
//import team3647elevator.Elevator;
//import team3647elevator.ElevatorLevel;
//import team3647elevator.Intake;
//import team3647elevator.oof;
//import team3647subsystems.Drivetrain;
//import team3647subsystems.Encoders;
//
//public class NewAutos 
//{
//	static int currentState;
//	static double lSSpeed, rSSpeed;
//	static double []adjustmentValues = new double[2];
//	static double prevLeftEncoder, prevRightEncoder;
//	
//	public static void MSRSWF(double lValue, double rValue)
//	{
//		switch(currentState)
//		{
//			case 0:
//				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedStop())
//				{
//					Elevator.stopEleVader();
//					ElevatorLevel.resetElevatorEncoders();
//					currentState = 1;
//				}
//				else
//				{
//					Encoders.resetEncoders();
//					Elevator.moveEleVader(-.2);
//				}
//				break;
//			case 1: 
//				lSSpeed = NewFunctions.msrswfLeftSpeed(lValue, rValue, 1);
//				rSSpeed = NewFunctions.msrswfRightSpeed(lValue, rValue, 1);
//				if(lSSpeed == 0 && rSSpeed == 0)
//				{
//					prevLeftEncoder = lValue;
//					prevRightEncoder = rValue;
//					currentState = 2;
//				}
//				else
//				{
//					adjustmentValues = NewFunctions.msrswfadjustment(lValue, rValue, 1);
//					Drivetrain.drive.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1], false);
//				}
//				break;
//			case 2:
//				lValue-=prevLeftEncoder;
//				rValue-=prevRightEncoder;
//				lSSpeed = NewFunctions.msrswfLeftSpeed(lValue, rValue, 2);
//				rSSpeed = NewFunctions.msrswfRightSpeed(lValue, rValue, 2);
//				if(lSSpeed == 0 && rSSpeed == 0)
//				{
//					currentState = 3;
//				}
//				else
//				{
//					adjustmentValues = NewFunctions.msrswfadjustment(lValue, rValue, 2);
//					Drivetrain.drive.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1], false);
//				}
//				break;
//			case 3:
//				Drivetrain.stop();
//				if(lValue == 0 && rValue == 0)
//				{
//					currentState = 4;
//				}
//				else
//				{
//					Encoders.resetEncoders();
//				}
//				break;
//			case 4:
//				if(ElevatorLevel.reachedSwitch())
//				{
//					Elevator.stopEleVader();
//					currentState = 5;
//				}
//				else
//				{
//					Elevator.moveEleVader(.4);
//				}
//				break;
//			case 5:
//				Intake.shootCube();
//				Timer.delay(1);
//				currentState = 6;
////				if(Intake.bannerSensor.get())
////				{
////					Intake.shootCube();
////				}
////				else
////				{
////					Timer.delay(.4);
////					oof.stopIntake();
////					currentState = 6;
////				}
//				break;
//			case 6:
//				Intake.stopIntake();
//				Elevator.stopEleVader();
//				Drivetrain.stop();
//				break;
//		}
//	}
//	
//	public static void MSLSWF(double lValue, double rValue)
//	{
//		switch(currentState)
//		{
//			case 0:
//				if(lValue == 0 && rValue == 0 && ElevatorLevel.reachedStop())
//				{
//					Elevator.stopEleVader();
//					ElevatorLevel.resetElevatorEncoders();
//					currentState = 1;
//				}
//				else
//				{
//					Encoders.resetEncoders();
//					Elevator.moveEleVader(-.2);
//				}
//				break;
//			case 1: 
//				lSSpeed = NewFunctions.mslsswfLeftSpeed(lValue, rValue, 1);
//				rSSpeed = NewFunctions.mslsswfRightSpeed(lValue, rValue, 1);
//				if(lSSpeed == 0 && rSSpeed == 0)
//				{
//					prevLeftEncoder = lValue;
//					prevRightEncoder = rValue;
//					currentState = 2;
//				}
//				else
//				{
//					adjustmentValues = NewFunctions.mslsswfadjustment(lValue, rValue, 1);
//					Drivetrain.drive.tankDrive(lSSpeed + adjustmentValues[0], rSSpeed + adjustmentValues[1], false);
//				}
//				break;
//			case 2:
//				lValue-=prevLeftEncoder;
//				rValue-=prevRightEncoder;
//				lSSpeed = NewFunctions.mslsswfLeftSpeed(lValue, rValue, 2);
//				rSSpeed = NewFunctions.mslsswfRightSpeed(lValue, rValue, 2);
//				if(lSSpeed == 0 && rSSpeed == 0)
//				{
//					prevLeftEncoder = lValue;
//					prevRightEncoder = rValue;
//					currentState = 3;
//				}
//				else
//				{
//					
//				}
//				break;
//		}
//	}
//	
//	public static void initialize()
//	{
//		Encoders.resetEncoders();
//		Elevator.elevatorState = 0;
//		currentState = 0;
//	}
//
//}
