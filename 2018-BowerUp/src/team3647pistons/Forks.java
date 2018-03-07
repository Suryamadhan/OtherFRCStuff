
package team3647pistons;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;

public class Forks{ 
	double pistonValue = piston.get();
	public static DoubleSolenoid piston = new DoubleSolenoid(1, 2);
	
	public static void openPiston() {
		piston.set(DoubleSolenoid.Value.kForward);
	}
	public static void closePiston() {
		piston.set(DoubleSolenoid.Value.kReverse);
	}
	public static void runPistons(boolean joyvalue) {
		if(joyvalue) {
			if(pistonValue = DoubleSolenoid.Value.kReverse)
			openPiston();
		}
		else {
			closePiston();
		}
	}
}



