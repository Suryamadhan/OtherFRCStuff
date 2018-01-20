package org.usfirst.frc.team3647.robot;

import com.ctre.CANTalon;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import team3647ConstantsAndFunctions.Constants;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;
import team3647subsystems.Joysticks;

public class Robot extends IterativeRobot {

	Encoders enc;
	Joysticks joy;
	Autonomous auto;
	
	@Override
	public void robotInit() 
	{
		try
		{
			CrashChecker.logRobotInit();
			enc = new Encoders();
			joy = new Joysticks();
			auto = new Autonomous();
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
			auto.currentState = 1;
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
//			Encoders.testEncoders();
			enc.setEncoderValues();
			Drivetrain.setLeftMotorSpeed(.2);
			Drivetrain.setRightMotorSpeed(-.2);
			enc.testEncoders();
//			auto.runAuto(enc.leftEncoderValue, enc.rightEncoderValue);
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
			Drivetrain.test(joy.leftJoySticky, joy.rightJoySticky);
//			System.out.println(1);
			//Drivetrain.arcadeDrive(enc.leftEncoderValue, enc.rightEncoderValue, joy.leftJoySticky, joy.rightJoyStickx);
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
	}

}

