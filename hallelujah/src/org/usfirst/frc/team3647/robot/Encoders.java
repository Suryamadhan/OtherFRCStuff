package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Encoder;

public class Encoders 
{
public double leftEncoderValue, rightEncoderValue;
	
	public static Encoder leftEnc = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
	public static Encoder rightEnc = new Encoder(2, 3, false, Encoder.EncodingType.k4X);

	public void setEncoderValues()
	{
		leftEncoderValue = -leftEnc.get();
		rightEncoderValue = rightEnc.get();
	}
	
	public static void resetEncoders()
	{
		leftEnc.reset();
		rightEnc.reset();
	}
	
	public void testEncoders()
	{
		System.out.println("Left Encoder Value: " + leftEncoderValue);
		System.out.println("Right Encoder Value: " + rightEncoderValue);
	}

}
