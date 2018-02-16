package team3647pistons;
import edu.wpi.first.wpilibj.Solenoid;

public class Clamps 
{
	//Single Piston
	
	public static Solenoid clampPiston = new Solenoid(6); //input number may be diff
	
	public static void clampThePiston()
	{
		clampPiston.set(true);
	}
	
	public static void unClampThePiston()
	{
		clampPiston.set(false);
	}
	
	//The function we will be calling in Robot.java and Auto
	public static void runPiston(boolean joyValue)
	{
		if(joyValue)
		{
			clampThePiston();
		}
		else
		{
			unClampThePiston();
		}
	}
}
