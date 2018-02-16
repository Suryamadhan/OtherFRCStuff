package team3647pistons;
import edu.wpi.first.wpilibj.Solenoid;

public class Ramps 
{
	//Single Piston
	public static Solenoid rampPiston = new Solenoid(7);

	public static void turnOnRamps()
	{
		 rampPiston.set(true);
	}
	
	public static void turnOffRamps()
	{
		rampPiston.set(false);
	}
	public static void runPiston(boolean joyValue) {
		if(joyValue) {
			turnOnRamps();
		}
		else {
			turnOffRamps();
		}
	}
}
