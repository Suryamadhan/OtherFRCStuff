package team3647subsystems;

import edu.wpi.first.wpilibj.Encoder;
import team3647ConstantsAndFunctions.Constants;

public class Encoders 
{
	static Encoder leftEncoder = new Encoder(Constants.leftEncoderPinSourceA, Constants.leftEncoderPinSourceB, false, Encoder.EncodingType.k4X);
	static Encoder rightEncoder = new Encoder(Constants.rightEncoderPinSourceA, Constants.rightEncoderPinSourceB, false, Encoder.EncodingType.k4X);
	
	public double leftEncoderValue, rightEncoderValue;
	
	public void setEncoderValues()
	{
		leftEncoderValue = Drivetrain._frontRightMotor.getSensorCollection().getQuadraturePosition();
		rightEncoderValue = -Drivetrain._frontLeftMotor.getSensorCollection().getQuadraturePosition();
	}
	
	public static void resetEncoders()
	{
		Drivetrain._frontLeftMotor.getSensorCollection().setQuadraturePosition(0, 10);
		Drivetrain._frontRightMotor.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	public void testEncoders()
	{
		System.out.println("Left Encoder Value: " + -leftEncoderValue);
		System.out.println("Right Encoder Value: " + rightEncoderValue);
	}

}
