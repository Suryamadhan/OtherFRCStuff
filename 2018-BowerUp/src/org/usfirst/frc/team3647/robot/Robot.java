package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
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
		enc = new Encoders();
		joy = new Joysticks();
		auto = new Autonomous();
	}
	
	@Override
	public void autonomousInit() 
	{
		Encoders.resetEncoders();
		auto.currentState = 1;
	}

	@Override
	public void autonomousPeriodic() 
	{
		enc.setEncoderValues();
	}

	@Override
	public void teleopPeriodic() 
	{
		enc.setEncoderValues();
		joy.setMainContollerValues();
		Drivetrain.arcadeDrive(enc.leftEncoderValue, enc.rightEncoderValue, joy.leftJoySticky, joy.rightJoyStickx);
	}

	@Override
	public void testPeriodic() 
	{
		
	}

}

