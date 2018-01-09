package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;
import team3647subsystems.Joysticks;

public class Robot extends IterativeRobot {

	static Encoders enc;
	Joysticks joy;
	@Override
	public void robotInit() 
	{
		enc = new Encoders();
		joy = new Joysticks();
	}
	
	@Override
	public void autonomousInit() 
	{
		Encoders.resetEncoders();
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

