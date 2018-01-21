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
	// The speed of the robot while running the program

		double leftJoystickValueY;
		double rightJoystickValueY;
		double rightJoystickValueX;
		double rightJoystickX;
		double leftEncoderValue;
		double rightEncoderValue;
		double rightSpeed;
		double leftSpeed;
		double prevError = 0;
		double sumError = 0;
		double kp = 0.2;
		double ki = 0.01;
		double kd = .1;
		
		// This function is run whenever the robot starts. This function is used for any
		// initialization of code

		Encoders encoderObject;
		Joysticks joystickObject;

		public void robotInit() {
			encoderObject = new Encoders();
			joystickObject = new Joysticks();
			Motors.drivetrainInitialization();
		}

		// This function runs once, right before autonomous period starts.

		public void autonomousInit() {
			encoderObject.resetEncoders();
		}

		// This is the function that is called during the autonomous period
		// This function runs periodically, meaning it acts as an infinite loop

		public void autonomousPeriodic() {

		}

		// This is the function that is called during the Tele-operated period
		// This function runs periodically, meaning it acts as an infinite loop

		public void teleopPeriodic() {
			joystickObject.updateMainController();
			leftJoystickValueY = Joysticks.leftJoySticky;
			rightJoystickValueX = Joysticks.rightJoyStickx;
			leftEncoderValue = encoderObject.getLeftEncoder();
			rightEncoderValue = encoderObject.getRightEncoder();

			leftSpeed = leftJoystickValueY;
			rightSpeed = leftJoystickValueY;
		
			if (leftJoystickValueY ==0)
			{
				encoderObject.resetEncoders();
				
				sumError = 0;
				prevError = 0;
				if(rightJoystickValueX != 0) {
					Motors.setLeftSpeed(.5*rightJoystickValueX);
					Motors.setRightSpeed(-(.5*rightJoystickValueX));
				}
				
				else {
					Motors.setLeftSpeed(0);
					Motors.setRightSpeed(0);
				}
				System.out.println(rightJoystickValueX);
			} 
			else 
			{
				if(leftJoystickValueY > 0) {
					runPIDforward();
				}
				else if(leftJoystickValueY < 0) {
					runPIDbackward();
				}
			}
			

		}

		
		public void runPIDforward() {

			double error = (leftEncoderValue - rightEncoderValue)/1000;
			double diffError = error - prevError;
			sumError = sumError + error;
			double inputValue = kp * error + ki * sumError + kd * diffError;

			leftSpeed = .8*leftSpeed - inputValue/2;
			rightSpeed = .8*rightSpeed + inputValue/2; 
			if (leftSpeed<0) {
				leftSpeed = 0;
			}
			if (rightSpeed<0) {
				rightSpeed = 0;
			}
			Motors.setLeftSpeed(leftSpeed);
			Motors.setRightSpeed(rightSpeed);
			prevError = error;

		}
		
		public void runPIDbackward() {

			double error = (leftEncoderValue - rightEncoderValue)/1000;
			double diffError = error - prevError;
			sumError = sumError + error;
			double inputValue = kp * error + ki * sumError + kd * diffError;

			leftSpeed = .8*leftSpeed - inputValue/2;
			rightSpeed = .8*rightSpeed + inputValue/2; 

			if (leftSpeed>0) {
				leftSpeed = 0;
			}
			if (rightSpeed>0) {
				rightSpeed = 0;
			}
			Motors.setLeftSpeed(leftSpeed);
			Motors.setRightSpeed(rightSpeed);
			prevError = error;
		}
		
		public void testPeriodic() {

		}

}

