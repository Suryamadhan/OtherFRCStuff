package team3647pistons;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Lock 
{
	//Double Pistons
	boolean IQ2 = false;
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
			if(!IQ2)
			{
				lock();
				IQ2 = false;
			}
			else
			{
				unLock();
				IQ2 = true;
			}
		}
}
