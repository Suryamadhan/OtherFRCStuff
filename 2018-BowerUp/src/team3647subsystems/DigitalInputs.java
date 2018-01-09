package team3647subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import team3647ConstantsAndFunctions.Constants;

public class DigitalInputs 
{
	public static boolean left, middle, right;
	
	static DigitalInput leftAuto = new DigitalInput(Constants.leftAutoPin);
	static DigitalInput middleAuto = new DigitalInput(Constants.middleAutoPin);
	static DigitalInput rightAuto = new DigitalInput(Constants.rightAutoPin);
	
	public static void setPinValues()
	{
		left = leftAuto.get();
		middle = middleAuto.get();
		right = rightAuto.get();
	}
}
