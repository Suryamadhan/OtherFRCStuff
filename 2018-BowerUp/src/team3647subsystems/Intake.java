package team3647subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Intake 
{
	public static WPI_TalonSRX leftIntakeMotor = new WPI_TalonSRX(62);
	public static WPI_TalonSRX rightIntakeMotor = new WPI_TalonSRX(52);
	static DifferentialDrive intake = new DifferentialDrive(leftIntakeMotor, rightIntakeMotor);
	
	public static void run(double lValue, double rValue)
	{
		intake.tankDrive(lValue, -rValue, false);
	}

}
