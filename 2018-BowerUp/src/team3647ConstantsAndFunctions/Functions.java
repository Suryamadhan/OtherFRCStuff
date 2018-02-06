package team3647ConstantsAndFunctions;

public class Functions 
{
	public static double sum, speed;
	
	public static double sinx(double x)
	{
		return Math.sin(x);
	}
	
	public static double LSLRSWinitialStraight(double lValue, double rValue)
	{
		//This function would change if length of the straight distance changes
		
		sum = lValue + rValue;
		sum *= 0.000143112244898;
		sum+=.574;
		speed = sinx(sum);
		return speed;
	}
	
	public static double LSLRSWfirstCurveLeftSpeed(double lValue)
	{
		//This function would change if length of the straight distance changes
		
		lValue*=0.000333928571429;
		lValue+=.574;
		speed = sinx(lValue);
		return speed;
	}
	
	public static final double LSLRSWfirstCurveRightSpeedConstant = 1.88235294118;
	
	
}