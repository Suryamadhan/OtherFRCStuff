package team3647pistons;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class DoublePistonExample 
{
	//Here is an Example of a single piston
	
	public static DoubleSolenoid piston = new DoubleSolenoid(1,2);
	
	public static void openPiston()
	{
		piston.set(true);
	}
	
	public static void closePiston()
	{
		piston.set(false);
	}
	
	public static void runPiston(boolean joyValue)
	{
		if(joyValue)
		{
			openPiston();
		}
		else
		{
			closePiston();
		}
	}
}
