
package team3647pistons;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import team3647elevator.Elevator;
import team3647elevator.ElevatorLevel;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;


public class Forks
{ 
	double pistonValue = piston.get();
	public static DoubleSolenoid piston = new DoubleSolenoid(4,5);
	
	public static void forks() 
  {
		piston.set(DoubleSolenoid.Value.kForward);
	}
	public static void notForks() 
  {
		piston.set(DoubleSolenoid.Value.kReverse);
	}
	public static void runPistons(boolean joyvalue) 
  {
		if(joyValue)
		{
			if(piston.get() == DoubleSolenoid.Value.kReverse)
			{
				forks();
				Timer.delay(.75);
			}
			else
			{
				notForks();
				Timer.delay(.75);
			}
		}
  }
}

