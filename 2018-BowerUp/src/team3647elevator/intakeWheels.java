package team3647elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class intakeWheels 
{
	//public static VictorSPX leftIntakeMotor = new VictorSPX(55);
//	public static VictorSPX rightIntakeMotor = new VictorSPX(56);
	
	public static DigitalInput bannerSensor = new DigitalInput(0); 
	
	public static boolean hasCube()
	{
		if(bannerSensor.get())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void shootCube()
	{
		run(-1, -1);
	}
	
	public static void stopIntake()
	{
		run(0, 0);
	}
	
	public static void pickUpCube()
	{
		run(1, 1);
	}
	
	public static void run(double lValue, double rValue)
	{
		//leftIntakeMotor.set(ControlMode.PercentOutput, -lValue);
		//rightIntakeMotor.set(ControlMode.PercentOutput, -rValue);
	}
	
	public static void B()
	{
		
	}

}
