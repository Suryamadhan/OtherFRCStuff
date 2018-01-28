/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
//	private static final String kDefaultAuto = "Default";
//	private static final String kCustomAuto = "My Auto";
//	private String m_autoSelected;
//	private SendableChooser<String> m_chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	int currentState = 1;
	Encoders enc;
	@Override
	public void robotInit() {
		enc = new Encoders();
//		m_chooser.addDefault("Default Auto", kDefaultAuto);
//		m_chooser.addObject("My Auto", kCustomAuto);
//		SmartDashboard.putData("Auto choices", m_chooser);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
		Encoders.resetEncoders();
		currentState = 1;
//		m_autoSelected = m_chooser.getSelected();
//		// autoSelected = SmartDashboard.getString("Auto Selector",
//		// defaultAuto);
//		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		enc.setEncoderValues();
		switch(currentState)
		{
			case 1:
				if(!Motors.reachedDistance(enc.leftEncoderValue, enc.rightEncoderValue, 1600))
				{
					Motors.driveForward(enc.leftEncoderValue, enc.rightEncoderValue, .7);
				}
				else
				{
					currentState = 2;
				}
				break;
			case 2:
				if(!Motors.reachedDistance(enc.leftEncoderValue, enc.rightEncoderValue, 2000))
				{
					Motors.driveForward(enc.leftEncoderValue, enc.rightEncoderValue, .2);
				}
				else
				{
					currentState = 3;
				}
				break;
			case 3:
				Motors.leftMotor.set(0);
				Motors.rightMotor.set(0);
				Timer.delay(.3);
				Encoders.resetEncoders();
				currentState = 4;
				break;
			case 4: 
				enc.leftEncoderValue = Math.abs(enc.leftEncoderValue);
				enc.rightEncoderValue = Math.abs(enc.rightEncoderValue);
				if(!Motors.reachedDistance(enc.leftEncoderValue, enc.rightEncoderValue, 1400))
				{
					Motors.driveBackward(enc.leftEncoderValue, enc.rightEncoderValue, -.7);
				}
				else
				{
					currentState = 5;
				}
				break;
			case 5:
				enc.leftEncoderValue = Math.abs(enc.leftEncoderValue);
				enc.rightEncoderValue = Math.abs(enc.rightEncoderValue);
				if(!Motors.reachedDistance(enc.leftEncoderValue, enc.rightEncoderValue, 1600))
				{
					Motors.driveBackward(enc.leftEncoderValue, enc.rightEncoderValue, -.2);
				}
				else
				{
					currentState = 5;
				}
				break;
			case 6: 
				Motors.leftMotor.set(0);
				Motors.rightMotor.set(0);
				break;
		}
//		switch (m_autoSelected) {
//			case kCustomAuto:
//				// Put custom auto code here
//				break;
//			case kDefaultAuto:
//			default:
//				// Put default auto code here
//				break;
//		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
