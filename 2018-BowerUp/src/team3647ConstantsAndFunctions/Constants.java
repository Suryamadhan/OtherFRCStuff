package team3647ConstantsAndFunctions;

public class Constants 
{
	//Motor Pins
	public final static int leftMotorPin = 2;
	public final static int rightMotorPin = 1;
	
	//Encoder Pins
	public final static int leftEncoderPinSourceA = 0;
	public final static int leftEncoderPinSourceB = 1;
	public final static int rightEncoderPinSourceA = 4;
	public final static int rightEncoderPinSourceB = 5;
	
	//JoySticks
	public final static int mainControllerNumber = 0;
	
	//Turn Constant for the Drivetrain
	public static double turnConstant(double yValue)
	{
		double turnConstant;
		turnConstant = .75 -(.25*(Math.abs(yValue)));
		return turnConstant;
	}
	
	//Pins for Auto
	public final static int leftAutoPin = 6;
	public final static int middleAutoPin = 7;
	public final static int rightAutoPin = 8;
	
	//Auto numbers
	public final static double initialStraightLLSWSC = 720;
	public final static double smallTurnForSwitchSWSC = 250 * Math.PI;
	public final static double bigTurnForSwitchSWSC = 500 * Math.PI;
	public final static double distanceSwitchLLSWSC = 360;
	public final static double smallTurnFromSwitchSWSC = 1000 * Math.PI;
	public final static double bigTurnFromSwitchSWSC = 1800 * Math.PI;
	public final static double backUpToPickUpCubeSWSC = 3000;
}
