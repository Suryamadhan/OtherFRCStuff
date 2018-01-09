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
		
	}

	@Override
	public void autonomousPeriodic() 
	{
		
	}

	@Override
	public void teleopPeriodic() 
	{
		joy.setMainContollerValues();
		Drivetrain.arcadeDrive(enc.leftEncoderValue, enc.rightEncoderValue, joy.leftJoySticky, joy.rightJoyStickx);
	}

	@Override
	public void testPeriodic() 
	{
		
	}
	
	public static void testy()
	{
		enc.resetEncoders();
	}
}

