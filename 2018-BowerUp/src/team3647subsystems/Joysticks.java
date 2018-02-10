package team3647subsystems;

import edu.wpi.first.wpilibj.Joystick;
import team3647ConstantsAndFunctions.Constants;

public class Joysticks 
{
	public Joystick mainController = new Joystick(Constants.mainControllerNumber);
	
	// Main contoller Variables
	public double leftTrigger, rightTrigger, leftJoySticky, leftJoyStickx, rightJoySticky, rightJoyStickx;
	public boolean rightBumper, leftBumper, buttonA, buttonB, buttonY;
	
	//Co-Driver Controller Variables
	public double leftTrigger1, rightTrigger1, leftJoySticky1, leftJoyStickx1, rightJoySticky1, rightJoyStickx1;
	public boolean rightBumper1, leftBumper1, buttonA1, buttonB1, buttonY1;
	
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
		rightJoySticky = -fixJoystickValue(mainController.getRawAxis(5));
	}
	
	public void setCoDriverContollerValues()
	{
		rightBumper1 =	mainController.getRawButton(6);
		leftBumper1 =	mainController.getRawButton(5);
		leftTrigger1 = fixJoystickValue(mainController.getRawAxis(2));
		buttonA1 =	mainController.getRawButton(1);
		buttonB1 = mainController.getRawButton(2);
		rightTrigger1 = fixJoystickValue(mainController.getRawAxis(3));
		buttonY1 = mainController.getRawButton(4);
		leftJoySticky1 = fixJoystickValue(-mainController.getRawAxis(1));
		leftJoyStickx1 = fixJoystickValue(mainController.getRawAxis(0));
		rightJoyStickx1 = fixJoystickValue(mainController.getRawAxis(4));
		rightJoySticky1 = -fixJoystickValue(mainController.getRawAxis(5));
	}
	
	public void updateControllers()
	{
		setMainContollerValues();
		setCoDriverContollerValues();
	}
	
	public static double fixJoystickValue(double jValue)
	{
		if(jValue < .15 && jValue > -.15)
		{
			return 0;
		}
		else
		{
			return 1 * jValue;
		}
	}
}
