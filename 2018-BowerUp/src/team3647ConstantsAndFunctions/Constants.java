package team3647ConstantsAndFunctions;

public class Constants 
{
	//Motor Pins
	public final static int leftMaster = 57;
	public final static int rightMaster = 54;
	public final static int rightSlave1 = 58;
	public final static int rightSlave2 = 59;
	public final static int leftSlave1 = 52;
	public final static int leftSlave2 = 53;
	
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
	public final static double initialStraightLLSWSC = 772;
	public final static double smallTurnForSwitchSWSC = 386 * Math.PI;
	public final static double bigTurnForSwitchSWSC = 772 * Math.PI;
	public final static double distanceSwitchLLSWSC = 386;
	public final static double smallTurnFromSwitchSWSC = 964 * Math.PI;
	public final static double bigTurnFromSwitchSWSC = 1735.2 * Math.PI;
	public final static double backUpToPickUpCubeSWSC = 3214;
	
	public final static double moveOnce = 600;
	
	public final static double testStright = 50 * 100;
	public final static double testBig = 10 * moveOnce;
	public final static double testSmall = 5.56 * moveOnce;

}
