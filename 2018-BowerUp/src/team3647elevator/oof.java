package team3647elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class oof 
{
	public static VictorSPX rightIntakeMotor = new VictorSPX(56);
	public static VictorSPX leftIntakeMotor = new VictorSPX(55);
	
	public static void b(double yes, double no)
	{
		rightIntakeMotor.set(ControlMode.PercentOutput, no);
		leftIntakeMotor.set(ControlMode.PercentOutput, yes );
	}
	
	public static void shootCube()
	{
		b(1, 1);
	}
	
	public static void stopIntake()
	{
		b(0, 0);
	}
	
	public static void pickUpCube()
	{
		b(-1, -1);
	}
	
}
