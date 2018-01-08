package team3647subsystems;

import edu.wpi.first.wpilibj.Encoder;
import team3647ConstantsAndFunctions.Constants;

public class Encoders 
{
	Encoder leftEncoder = new Encoder(Constants.leftEncoderPinSourceA, Constants.leftEncoderPinSourceB, false, Encoder.EncodingType.k4X);
	Encoder rightEncoder = new Encoder(Constants.rightEncoderPinSourceA, Constants.rightEncoderPinSourceB, false, Encoder.EncodingType.k4X);
	
	
//	public Encoders()
//	{
//		leftEncoder = new Encoder(Constants.leftEncoderPinSourceA, Constants.leftEncoderPinSourceB, false, Encoder.EncodingType.k4X);
//		rightEncoder = new Encoder(Constants.rightEncoderPinSourceA, Constants.rightEncoderPinSourceB, false, Encoder.EncodingType.k4X);
//	}
	
	public double leftEncoderValue, rightEncoderValue;
	
	public void setEncoderValues()
	{
		leftEncoderValue = leftEncoder.get();
		rightEncoderValue = rightEncoder.get();
	}
	
	public void resetEncoders()
	{
		leftEncoder.reset();
		rightEncoder.reset();
	}

}
