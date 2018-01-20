package org.usfirst.frc.team3647.robot;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Motors {
	public static WPI_TalonSRX _frontLeftMotor = new WPI_TalonSRX(0);
	public static WPI_TalonSRX _frontRightMotor = new WPI_TalonSRX(3);
	
	public static VictorSPX _leftSlave1 = new VictorSPX(1);
	public static VictorSPX _rightSlave1 = new VictorSPX(0);
	public static VictorSPX _leftSlave2 = new VictorSPX(2);
	public static VictorSPX _rightSlave2 = new VictorSPX(3);

	public static void setLeftSpeed(double leftSpeed) {
		_frontLeftMotor.set(leftSpeed);
		}
	public static void setRightSpeed(double rightSpeed) {
		_frontRightMotor.set(-rightSpeed);
	}
	
	public static void drivetrainInitialization()
	{
		setLeftSpeed(0);
		setRightSpeed(0);
		_leftSlave1.follow(_frontLeftMotor);
		_leftSlave2.follow(_frontLeftMotor);    
		_rightSlave1.follow(_frontRightMotor);
		_rightSlave2.follow(_frontRightMotor);
	}
	
	
}
