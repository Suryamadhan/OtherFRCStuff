package team3647elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;

public class Intake 
{
	public static VictorSPX rightIntakeMotor = new VictorSPX(56);
	public static VictorSPX leftIntakeMotor = new VictorSPX(55);
	
//	public static DigitalInput bannerSensor = new DigitalInput(99); 
	
	public static void b(double lTrigger, double rTrigger)
	{
		if(lTrigger > 0)
		{	
			rightIntakeMotor.set(ControlMode.PercentOutput, -lTrigger);
			leftIntakeMotor.set(ControlMode.PercentOutput, -lTrigger);
		}
		else if(rTrigger > 0)
		{
			rightIntakeMotor.set(ControlMode.PercentOutput, rTrigger);
			leftIntakeMotor.set(ControlMode.PercentOutput, rTrigger);
		}
		else
		{
			rightIntakeMotor.set(ControlMode.PercentOutput, 0);
			leftIntakeMotor.set(ControlMode.PercentOutput, 0);
		}
			
		
	}
	
	public static void shootCube()
	{
		b(0, 1);
	}
	
	public static void stopIntake()
	{
		b(0, 0);
	}
	
	public static void pickUpCube()
	{
		b(1, 0);
	}
}
