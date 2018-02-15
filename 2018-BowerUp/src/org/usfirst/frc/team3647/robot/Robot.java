package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Solenoid;
import team3647elevator.Elevator;
import team3647elevator.ElevatorLevel;
import team3647elevator.intakeWheels;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;
import team3647subsystems.Joysticks;

public class Robot extends IterativeRobot {

	Encoders enc;
	Joysticks joy;
	ElevatorLevel eleVader;
	Solenoid yes;

	@Override
	public void robotInit() 
	{
		try
		{
			CrashChecker.logRobotInit();
			enc = new Encoders();
			joy = new Joysticks();
			eleVader = new ElevatorLevel();
			Encoders.resetEncoders();
			yes = new Solenoid(7);
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
			Auto.initialize();
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
			Elevator.runElevator();
			Auto.MSLSW(Encoders.leftEncoderValue, Encoders.rightEncoderValue);
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
			Drivetrain.arcadeDrive(Encoders.leftEncoderValue, Encoders.rightEncoderValue, joy.leftJoySticky, joy.rightJoyStickx);
			System.out.println("oof");
			//intakeWheels.run(joy.leftTrigger, joy.rightTrigger);
			
			eleVader.setElevatorEncoder();
			Elevator.setElevatorButtons(joy.buttonA, joy.buttonB, joy.leftBumper,  joy.rightBumper);
			Elevator.setManualOverride(joy.rightJoySticky);
			Elevator.runElevator();
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
		enc.setEncoderValues();
		Encoders.testEncoders();
		joy.updateControllers();
		//Drivetrain.tankDrive(joy.leftJoySticky, joy.rightJoySticky);
		eleVader.setElevatorEncoder();
		Elevator.moveEleVader(joy.rightJoySticky * .4);
		//System.out.println(ElevatorLevel.reachedStop());
		//Intake.run(joy.leftJoySticky, joy.rightJoySticky);
		if(joy.buttonA)
		{
			Encoders.resetEncoders();
		}
	}
}
