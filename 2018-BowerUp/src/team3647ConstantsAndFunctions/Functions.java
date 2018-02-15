package team3647ConstantsAndFunctions;

public class Functions 
{
	public static double sum, speed;
	
	public static double sinx(double x)
	{
		return Math.sin(x);
	}
	
	public static double switchInitialStraight(double lValue, double rValue)
	{
		//This function would change if length of the straight distance changes
		
		sum = lValue + rValue;
		sum *= 0.000143112244898;
		sum+=.574;
		speed = sinx(sum);
		return speed;
	}
	
	public static double scaleInitialStraight(double lValue, double rValue)
	{
		//This function would change if length of the straight distance changes
		
		sum = lValue + rValue;
		sum *= 0.0000500892857143;
		sum +=.574;
		speed = sinx(sum);
		return speed;
	}
	
	public static double switchFirstCurveBigSpeed(double lValue)
	{
		//This function would change if length of the straight distance changes
		
		lValue*=0.000143112244898;
		lValue+=.574;
		speed = sinx(lValue);
		return speed;
	}
	
	public static double scaleFirstCurveBigSpeed(double lValue)
	{
		//This function would change if length of the straight distance changes
		
		lValue*=0.000383612988505;
		lValue+=.574;
		speed = sinx(lValue);
		return speed;
	}
	
	public static double middleRightBigCurveSpeed(double lValue)
	{
		//This function would change if length of the straight distance changes
		
		lValue*=0.0001737405713;
		lValue+=1.969;
		speed = sinx(lValue);
		return speed;
	}
	
	public static double stopToPickUp(double eValue)
	{
		eValue*=0.000163448275862;
		eValue+=1.899;
		speed = sinx(eValue);
		speed/=2;
		return speed;
	}
	
	public static double stopToSwitch(double eValue)
	{
		eValue*=0.0000449333333333;
		eValue+=2.17;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double stopToScale(double eValue)
	{
		eValue*=0.0000321559633028;
		eValue+=1.552;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double pickUpToStop(double eValue)
	{
		return -.2;
	}
	
	public static double pickUpToSwitch(double eValue)
	{
		eValue*=0.0000449333333333;
		eValue+=2.17;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double pickUpToScale(double eValue)
	{
		eValue*=0.0000321559633028;
		eValue+=1.552;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double switchToStop(double eValue)
	{
		return -.2;
	}
	
	public static double switchToPickUp(double eValue)
	{
		return -.2;
	}
	
	public static double switchToScale(double eValue)
	{
		eValue*=0.0000321559633028;
		eValue+=1.552;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double scaleToStop(double eValue)
	{
		return -.2;
	}
	
	public static double scaleToPickUp(double eValue)
	{
		return -.2;
	}
	
	public static double scaleToSwitch(double eValue)
	{
		return -.2;
	}
	
	// new auto functions
	public static double middleRBigTurn(double eValue)//
	{
		eValue*=0.000232226080487;
		eValue+=4.429;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double middleRStraighttoSwitch(double eValue)//
	{
		eValue*=0.000799102322763;
		eValue +=1.0471975512;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double middleLBigTurn(double eValue)//
	{
		eValue*=0.000167455541031;
		eValue+=4.429;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double middleLStraighttoSwitch(double eValue)//
	{
		eValue*=0.000226;
		eValue +=4.996;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double straightForSwitch(double eValue)//
	{
		eValue*=0.000487444444444;
		eValue += 1.287;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double BigTurnSwitch(double eValue)//
	{
		eValue*=0.00050651060639;
		eValue += 1.855;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double straightForScale(double eValue)
	{
		eValue*=0.000522261904762;
		eValue += 1.287;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double BigTurnScale(double eValue)
	{
		eValue*=0.000542689935418;
		eValue += 1.855;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	
}