package team3647pistons;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class IntakeTilt 
{
	//Double Pistons
	
	public static DoubleSolenoid piston = new DoubleSolenoid(1,2);
	
	public static void intakeTilted()
	{
		piston.set(DoubleSolenoid.Value.kForward);
	}
	
	public static void intakeNotTilted()
	{
		piston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public static void runPiston(boolean joyValue)
	{
		if(joyValue)
		{
			intakeTilted();
		}
		else
		{
			intakeNotTilted();
		}
	}
}
