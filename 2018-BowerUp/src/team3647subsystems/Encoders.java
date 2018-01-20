package team3647subsystems;

import edu.wpi.first.wpilibj.Encoder;
import team3647ConstantsAndFunctions.Constants;

public class Encoders 
{
	public double leftEncoderValue, rightEncoderValue;
	
	public void setEncoderValues()
	{
		leftEncoderValue = Drivetrain.rightSRX.getSensorCollection().getQuadraturePosition();
		rightEncoderValue = -Drivetrain.leftSRX.getSensorCollection().getQuadraturePosition();
	}
	
	public static void resetEncoders()
	{
		Drivetrain.leftSRX.getSensorCollection().setQuadraturePosition(0, 10);
		Drivetrain.rightSRX.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	public void testEncoders()
	{
		System.out.println("Left Encoder Value: " + leftEncoderValue);
		System.out.println("Right Encoder Value: " + rightEncoderValue);
	}

}
