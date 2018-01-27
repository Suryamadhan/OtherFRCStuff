package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;
import team3647subsystems.Joysticks;

public class Robot extends IterativeRobot {

	Encoders enc;
	Joysticks joy;
	
	int yes = 1;
	
	@Override
	public void robotInit() 
	{
		try
		{
			CrashChecker.logRobotInit();
			enc = new Encoders();
			joy = new Joysticks();
			Encoders.resetEncoders();
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
			Encoders.resetEncoders();
			Auto.currentState = 1;
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
//			System.out.println(Auto.currentState);
			Auto.test(enc.leftEncoderValue, enc.rightEncoderValue);
			//enc.testEncoders();
			//Autonomous.runAuto(enc.leftEncoderValue, enc.rightEncoderValue);
//			switch(yes)
//			{
//				case 1:
//					Autonomous.currentState = 1;
//					yes = 2;
//					break;
//				case 2:
//					enc.setEncoderValues();
//					auto.yeet();
//					System.out.println(Autonomous.currentState);
//					break;
//			}
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
			Drivetrain.testSpeed();
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
		if(joy.buttonA)
		{
			Encoders.resetEncoders();
		}
		//Drivetrain.testSpeed();
	}

}

