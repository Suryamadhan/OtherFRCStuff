package team3647pistons;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;

public class IntakeTilt 
{
	//Double Pistons
	
	public static Solenoid piston = new Solenoid(5);
	
	public static void intakeTitled()
	{
		piston.set(true);
	}
	
	public static void intakeNotTitled()
	{
		piston.set(false);
	}
	
	public static void runPiston(boolean joyValue)
	{
		if(joyValue)
		{
			intakeTitled();
		}
		else
		{
			intakeNotTitled();
		}
	}
}
