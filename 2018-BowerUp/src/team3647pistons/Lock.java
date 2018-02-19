package team3647pistons;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Lock 
{
	//Double Pistons
	public static Solenoid piston = new Solenoid(5);
		
	public static void lock()
	{
		piston.set(true);
	}
		
	public static void unLock()
	{
		piston.set(false);
	}
		
	public static void runPiston(boolean joyValue)
	{
		if(joyValue)
		{
			if(!piston.get())
			{
				lock();
				Timer.delay(.75);
			}
			else
			{
				unLock();
				Timer.delay(.75);
			}
		}
	}
}

