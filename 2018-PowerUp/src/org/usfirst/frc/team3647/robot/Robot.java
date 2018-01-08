package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import team3647subsystems.Encoders;

public class Robot extends IterativeRobot {

	Encoders enc;
	@Override
	public void robotInit() 
	{
		enc = new Encoders();
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
		
	}

	@Override
	public void testPeriodic() 
	{
		
	}
	
	public static void testy()
	{
		
	}
}

