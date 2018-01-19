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
	WPI_TalonSRX _frontLeftMotor = new WPI_TalonSRX(11); 		/* device IDs here (1 of 2) */
	WPI_TalonSRX _frontRightMotor = new WPI_TalonSRX(14);

	/* extra talons for six motor drives */
	WPI_VictorSPX _leftSlave1 = new WPI_VictorSPX(13);
	WPI_VictorSPX _rightSlave1 = new WPI_VictorSPX(15);
	WPI_VictorSPX _leftSlave2 = new WPI_VictorSPX(16);
	WPI_VictorSPX _rightSlave2 = new WPI_VictorSPX(17);
	
	
	DifferentialDrive _drive = new DifferentialDrive(_frontLeftMotor, _frontRightMotor);
	
	Joystick _joy = new Joystick(0);

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		_leftSlave1.follow(_frontLeftMotor);
	    	_leftSlave2.follow(_frontLeftMotor);
	    	_rightSlave1.follow(_frontRightMotor);
	    	_rightSlave2.follow(_frontRightMotor);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{
		double forward = _joy.getY(); // logitech gampad left X, positive is forward
		double turn = _joy.getZ(); //logitech gampad right X, positive means turn right
		_drive.arcadeDrive(forward, turn);
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
	}
}

