package team3647subsystems;

import edu.wpi.first.wpilibj.Joystick;
import team3647ConstantsAndFunctions.Constants;

public class Joysticks 
{
	public Joystick mainController = new Joystick(Constants.mainControllerNumber);
	
//	public Joysticks()
//	{
//		 mainController = new Joystick(Constants.mainController);
//	}
	
	// Main contoller Variables
	public double leftTrigger, rightTrigger, leftJoySticky, leftJoyStickx, rightJoySticky, rightJoyStickx;
	public boolean rightBumper, leftBumper, buttonA, buttonB, buttonY;
	
	public void setMainContollerValues()
	{
		rightBumper =	mainController.getRawButton(6);
		leftBumper =	mainController.getRawButton(5);
		leftTrigger = fixJoystickValue(mainController.getRawAxis(2));
		buttonA =	mainController.getRawButton(1);
		buttonB = mainController.getRawButton(2);
		rightTrigger = fixJoystickValue(mainController.getRawAxis(3));
		buttonY = mainController.getRawButton(4);
		leftJoySticky = fixJoystickValue(-mainController.getRawAxis(1));
		leftJoyStickx = fixJoystickValue(mainController.getRawAxis(0));
		rightJoyStickx = fixJoystickValue(mainController.getRawAxis(4));
		rightJoySticky = fixJoystickValue(mainController.getRawAxis(5));
		
	}
	
	public static double fixJoystickValue(double jValue)
	{
		if(jValue < .15 && jValue > -.15)
		{
			return 0;
		}
		else
		{
			return jValue;
		}
	}
}
