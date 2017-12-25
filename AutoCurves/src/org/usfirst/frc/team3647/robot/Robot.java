package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	final String defaultAuto = "Default";
	final String customAuto = "My Auto";
	String autoSelected;
	SendableChooser<String> chooser = new SendableChooser<>();
	double left = 1600;
	double right = 900;
	double ratio  = left/right;
	double actualRatio;
	int currentState = 1;
	double sum;
	boolean withinRange;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	Encoders enc;
	
	@Override
	public void robotInit() {
		enc = new Encoders();
	}


	@Override
	public void autonomousInit() {
		enc.resetEncoders();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		switch(currentState)
		{
			case 1:
				actualRatio =(enc.returnleftEncValue()/enc.returnrightEncValue()) / ratio;
				sum = enc.returnleftEncValue() + enc.returnrightEncValue();
				if( actualRatio >= .9 && actualRatio <= 1.1)
				{
					withinRange = true;
				}
				else
				{
					withinRange = false;
				}
				if(sum< left + right)
				{
					if(sum < 50 || withinRange)
					{
						Motors007.leftTalon.set(.6);
						Motors007.rightTalon.set(-.34);
					}
					else
					{
						if(actualRatio > 1.1 && actualRatio < 1.18)
						{
							Motors007.leftTalon.set(.52);
							Motors007.rightTalon.set(-.42);
						}
						else if(actualRatio > 1.18 && actualRatio < 1.25)
						{
							Motors007.leftTalon.set(.44);
							Motors007.rightTalon.set(-.5);
						}
						else if(actualRatio > 1.25)
						{
							Motors007.leftTalon.set(.36);
							Motors007.rightTalon.set(-.58);
						}
						else if(actualRatio < .9 && actualRatio > .82)
						{
							Motors007.leftTalon.set(.68);
							Motors007.rightTalon.set(-.26);
						}
						else if(actualRatio < .82 && actualRatio > .75)
						{
							Motors007.leftTalon.set(.76);
							Motors007.rightTalon.set(-.18);
						}
						else
						{
							Motors007.leftTalon.set(.84);
							Motors007.rightTalon.set(-.1);
						}
					}
				}
				else
				{
					Motors007.leftTalon.set(0);
					Motors007.rightTalon.set(0);
				}
				break;
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

