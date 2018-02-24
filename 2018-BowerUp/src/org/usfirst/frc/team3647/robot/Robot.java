package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.MotorSafety;
import edu.wpi.first.wpilibj.MotorSafetyHelper;
import team3647elevator.Elevator;
import team3647elevator.ElevatorLevel;
import team3647elevator.Intake;
import team3647pistons.intakeMechanism;

import team3647pistons.IntakeTilt;
import team3647pistons.Shifter;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;
import team3647subsystems.Joysticks;

public class Robot extends IterativeRobot {

	Encoders enc;
	Joysticks joy;
	ElevatorLevel eleVader;
	MotorSafety safety;
	MotorSafetyHelper safetyChecker;

	@Override
	public void robotInit() 
	{
		try
		{
			
			CrashChecker.logRobotInit();
			enc = new Encoders();
			safetyChecker = new MotorSafetyHelper(safety);
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
			Autonomous.initialize();
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
			Autonomous.rightSideBigJank(Encoders.leftEncoderValue, Encoders.rightEncoderValue);
			Encoders.testEncoders();
			Drivetrain.testSpeed();
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
			updateJoysticks();
			runMotorSafety();
			runPistons();
			runDrivetrain();
			runElevator();
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
		updateJoysticks();
		Shifter.runPiston(joy.buttonY);
		if(joy.buttonA)
		{
			Elevator.moveEleVader(.13);
		}
		else
		{
			Elevator.moveEleVader(joy.rightJoySticky * .4);
		}
	}
	
	public void updateJoysticks()
	{
		joy.setMainContollerValues();
		joy.setCoDriverContollerValues();
	}
	
	public void runElevator()
	{
		eleVader.setElevatorEncoder();
		if(Shifter.piston.get() == DoubleSolenoid.Value.kForward)
		{
			Elevator.moveEleVader(joy.rightJoySticky * .4);
		}
		else
		{
			Elevator.setElevatorButtons(joy.buttonA1, joy.buttonB1, joy.buttonX1,  joy.buttonY1);
			Elevator.setManualOverride(joy.rightJoySticky1 * .6);
			Elevator.runDarthVader();
			Intake.runIntake(joy.rightTrigger1, joy.leftTrigger1);
		}
	}
	
	public void runPistons()
	{
		//Clamps.runPiston(joy.buttonA);
		intakeMechanism.runIntake(joy.rightBumper1);
		IntakeTilt.runPiston(joy.buttonX);
		Shifter.runPiston(joy.buttonY);
	}
	
	public void runDrivetrain()
	{
		enc.setEncoderValues();
		Drivetrain.arcadeDrive(Encoders.leftEncoderValue, Encoders.rightEncoderValue, joy.leftJoySticky, joy.rightJoyStickx);
	}
	
	public void runMotorSafety()
	{
		safetyChecker.setSafetyEnabled(false);
	}
}