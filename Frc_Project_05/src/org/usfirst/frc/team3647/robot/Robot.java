package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot 
{	
	Autonomous auto;
	Encoders enc;
	Joysticks joy;
	double leftEncoderValue;
	double rightEncoderValue;
	double rightSpeed;
	double leftSpeed;
	double speed;
	double prevError = 0;
	double sumError = 0;
	
	//This function is run whenever the robot starts. This function is used for any initialization of code
	@Override
	public void robotInit() 
	{
		auto = new Autonomous();
		enc = new Encoders();
		joy = new Joysticks();
	}

	 //This function runs once, right before autonomous period starts. 
	@Override
	public void autonomousInit() 
	{
		auto.AutoVarInit();
		enc.resetEncoders();
	}
		

	//This is the function that is called during the autonomous period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void autonomousPeriodic() 
	{
		enc.setEncoderValues();
		auto.runAuto(enc.leftEncoderValue, enc.rightEncoderValue);
		if (auto.resetEncoder){
			enc.resetEncoders();
			auto.resetEncoder = false;
		}
	}
	
	//This is the function that is called during the Tele-operated period
	//This function runs periodically, meaning it acts as an infinite loop
	@Override
	public void teleopPeriodic() 
	{
		joy.setMainControllerValues();
		enc.setEncoderValues();
		Motors.leftSpark.set(joy.leftJoySticky/2);
		Motors.rightSpark.set(-joy.rightJoySticky/2);
		System.out.print("left"+enc.leftEncoderValue);
		System.out.println(" right"+enc.rightEncoderValue);

	}

	//This is the function that is called during the test
	//Test is an option available in the driver station and can be used to test specific pieces of code.
	//This function runs periodically, meaning it acts like an infinite loop
	@Override
	public void testPeriodic() 
	{
		
	}
}