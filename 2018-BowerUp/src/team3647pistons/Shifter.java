package team3647pistons;

import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Shifter 
{
	//Double Pistons
	
		public static DoubleSolenoid piston = new DoubleSolenoid(1,0);
		
		public static void intakeTitled()
		{
			piston.set(DoubleSolenoid.Value.kForward);
		}
		
		public static void intakeNotTitled()
		{
			piston.set(DoubleSolenoid.Value.kReverse);
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
