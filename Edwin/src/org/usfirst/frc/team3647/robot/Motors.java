package org.usfirst.frc.team3647.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Motors 
{
	public static WPI_TalonSRX leftSRX = new WPI_TalonSRX(57);
	public static WPI_TalonSRX rightSRX = new WPI_TalonSRX(54);
	
	public static VictorSPX leftSPX1 = new VictorSPX(52;
	public static VictorSPX rightSPX1 = new VictorSPX(58);
	public static VictorSPX leftSPX2 = new VictorSPX(Constants.leftSlave2);
	public static VictorSPX rightSPX2 = new VictorSPX(53);
	
	public static DifferentialDrive drive = new DifferentialDrive(leftSRX, rightSRX);
	
	public static void drivetrainInitialization()
	{
		tankDrive(0,0);
		leftSPX1.follow(leftSRX);
		leftSPX2.follow(leftSRX);    
		rightSPX1.follow(rightSRX);
		rightSPX2.follow(rightSRX);
	}
	
	public static void setSpeed(double lSpeed, double rSpeed)
	{
		drive.tankDrive(lSpeed, rSpeed, false);
	}

}
