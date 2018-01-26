package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;
import team3647subsystems.Joysticks;

public class Robot extends IterativeRobot {

	Encoders enc;
	Joysticks joy;
	Autonomous auto;
	
	int yes = 1;
	
	@Override
	public void robotInit() 
	{
		try
		{
			CrashChecker.logRobotInit();
			enc = new Encoders();
			joy = new Joysticks();
			auto = new Autonomous();
			Encoders.resetEncoders();
			Drivetrain.drivetrainInitialization();
			//Drivetrain.configPID();
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
			//Encoders.resetEncoders();
			yes = 1;
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
			switch(yes)
			{
				case 1:
					Autonomous.currentState = 1;
					yes = 2;
					break;
				case 2:
					enc.setEncoderValues();
					auto.yeet();
					System.out.println(Autonomous.currentState);
					break;
			}
		}
		
	}

	@Override
	public void teleopPeriodic() 
	{
		try 
		{
			CrashChecker.logTeleopPeriodic();
			enc.setEncoderValues();
			joy.setMainContollerValues();
			Drivetrain.arcadeDrive(enc.leftEncoderValue, enc.rightEncoderValue, joy.leftJoySticky, joy.rightJoyStickx);
			System.out.println("Left " + Drivetrain.leftSRX.get());
			System.out.println("Right " + Drivetrain.rightSRX.get());
			//Drivetrain.testDrive(joy.leftJoySticky, joy.rightJoyStickx);
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
		enc.testEncoders();
		joy.setMainContollerValues();
		Drivetrain.testDrive(joy.leftJoySticky, joy.rightJoySticky);
	}

}

