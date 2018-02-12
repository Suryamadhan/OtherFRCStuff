package team3647pistons;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Intake 
{
	//Double Pistons
	public static DoubleSolenoid piston = new DoubleSolenoid(1,2);
	
	public static void openIntake()
	{
		piston.set(DoubleSolenoid.Value.kForward);
	}
	
	public static void closeIntake()
	{
		piston.set(DoubleSolenoid.Value.kReverse);
	}
	
	public static void runIntake(boolean joyValue)
	{
		if(joyValue)
		{
			openIntake();
		}
		else
		{
			closeIntake();
		}
	}
}
