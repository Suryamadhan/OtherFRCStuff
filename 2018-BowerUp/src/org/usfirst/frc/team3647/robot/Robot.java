package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import team3647elevator.Elevator;
import team3647elevator.ElevatorLevel;
import team3647elevator.intakeWheels;
import team3647elevator.oof;
import team3647pistons.Intake;
import team3647pistons.Shifter;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;
import team3647subsystems.Joysticks;

public class Robot extends IterativeRobot {

	Encoders enc;
	Joysticks joy;
	ElevatorLevel eleVader;
	MotorSafety yes;
	MotorSafetyHelper yayt;

	@Override
	public void robotInit() 
	{
		try
		{
			
			CrashChecker.logRobotInit();
			enc = new Encoders();
			yayt = new MotorSafetyHelper(yes);
			joy = new Joysticks();
			eleVader = new ElevatorLevel();
			Encoders.resetEncoders();
			ElevatorLevel.resetElevatorEncoders();
			Drivetrain.drivetrainInitialization();
		}
		catch(Throwable t)
		{
			CrashChecker.logThrowableCrash(t);
			throw t;
		}
	}
	
	@Override
	public void autonomousInit() 
	{
		try 
		{
			CrashChecker.logAutoInit();
			NewAutos.initialize();
		}
		catch(Throwable t)
		{
			CrashChecker.logThrowableCrash(t);
			throw t;
		}	
	}

	@Override
	public void autonomousPeriodic() 
	{
		while(DriverStation.getInstance().isAutonomous() && !DriverStation.getInstance().isDisabled())
		{
			enc.setEncoderValues();
			eleVader.setElevatorEncoder();
			Auto.testB(Encoders.leftEncoderValue, Encoders.rightEncoderValue);
		}
	}
	
	@Override
	public void teleopInit()
	{
		Elevator.elevatorState = 0;
	}
	
	@Override
	public void teleopPeriodic() 
	{
		try 
		{
			CrashChecker.logTeleopPeriodic();
			enc.setEncoderValues();
			joy.updateControllers();
			eleVader.setElevatorEncoder();
			Drivetrain.arcadeDrive(Encoders.leftEncoderValue, Encoders.rightEncoderValue, joy.leftJoySticky, joy.rightJoyStickx);
			//oof.b(joy.rightTrigger1, joy.leftTrigger1);
			Shifter.runPiston(joy.leftBumper1);
			Intake.runIntake(joy.rightBumper1);
			//System.out.println(joy.rightBumper1);
			//Elevator.setElevatorButtons(joy.buttonA1, joy.buttonB1, joy.buttonY1,  joy.buttonX1);
			//Elevator.setManualOverride(joy.rightJoySticky1 * .4);
			//Elevator.runElevator();
			//intakeWheels.run(joy.leftTrigger, joy.rightTrigger);
//			Shifter.runPiston(joy.buttonA);
//			Intake.runIntake(joy.buttonB);
//			eleVader.setElevatorEncoder();
//			Elevator.setElevatorButtons(joy.buttonA, joy.buttonB, joy.leftBumper,  joy.rightBumper);
//			Elevator.setManualOverride(joy.rightJoySticky * .4);
//			Elevator.runElevator();
		}
		catch(Throwable t)
		{
			CrashChecker.logThrowableCrash(t);
			throw t;
		}
	}

	@Override
	public void testPeriodic() 
	{
		yayt.setSafetyEnabled(false);
		enc.setEncoderValues();
		//Encoders.testEncoders();
		joy.updateControllers();
		Drivetrain.drive.arcadeDrive(joy.leftJoySticky, joy.rightJoyStickx, false);
		//Drivetrain.tankDrive(joy.leftJoySticky, joy.rightJoySticky);
		eleVader.setElevatorEncoder();
		//Elevator.moveEleVader(joy.rightJoySticky * .4);
		//ElevatorLevel.testElevatorEncoders();
		Encoders.testEncoders();
		//System.out.println(ElevatorLevel.reachedStop());
		//oof.b(joy.rightJoySticky);
		if(joy.buttonA)
		{
			Encoders.resetEncoders();
		}
//		oof.b(joy.leftJoySticky, joy.rightJoySticky);
//		Intake.runIntake(joy.buttonA);
//	}
	}
}