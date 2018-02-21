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
//		eValue*=0.000163448275862;
//		eValue+=1.899;
//		speed = sinx(eValue);
//		speed/=2;
//		return speed;
//		eValue*=0.000438;
//		eValue+=5.236;
//		eValue/=2;
//		speed = sinx(eValue);
//		return speed;
		return .25;
	}
	
	public static double stopToSwitch(double eValue)
	{
//		eValue*=0.0000449333333333;
//		eValue+=2.17;
//		speed = sinx(eValue);
//		return speed;
//		eValue*=0.000286;
//		eValue*=1.855;
//		eValue/=2;
//		speed = sinx(eValue);
//		return speed;
//		eValue*=0.000161222222222;
//		eValue+=4.429;
//		eValue/=2;
//		speed = sinx(eValue);
//		return speed;
		eValue*=(-0.00006);
		eValue+=.8;
		return eValue;
		//.8 to .2
		
	}
	
	public static double stopToScale(double eValue)
	{
//		eValue*=0.000156090909091;
//		eValue+=2.24;
//		eValue/=2;
//		speed = sinx(eValue);
//		return speed;
//		if(eValue < Constants.sWitch)
//		{
//			return 1;
//		}
//		else
//		{
//			return switchToScale(eValue);
//		}
//		if(eValue < Constants.sWitch)
//		{
//			return 1;
//		}
//		else
//		{
//			eValue*=(-0.0000333333333333);
//			eValue+=1.2;
//			return eValue;
//			//.9 to .25
//		}
//		return .4;
		
		if(eValue < 25000)
		{
			return 1;
		}
		else
		{
			eValue*=(-0.0000333333333333);
			eValue+=1.683;
			return eValue;
			//.85 to .25
		}
	}
	
	public static double pickUpToStop(double eValue)
	{
		return -.2;
	}
	
	public static double pickUpToSwitch(double eValue)
	{
//		eValue*=0.0001435;
//		eValue+=4.589;
//		eValue/=2;
//		speed = sinx(eValue);
//		return speed;
//		eValue*=(-0.0000625);
//		eValue+=.763;
//		return eValue;
		return .3;
		//.7 to .2
	}
	
	public static double pickUpToScale(double eValue)
	{
//		eValue*=0.00012059082602;
//		eValue+=3.02059265359;
//		eValue/=2;
//		speed = sinx(eValue);
//		return speed;
//		eValue*=(-0.0000224137931034);
//		eValue+=.87;
//		return eValue;
		return .4;
		//.85 to 2
	}
	
	public static double switchToStop(double eValue)
	{
		return -.2;
	}
	
	public static double switchToPickUp(double eValue)
	{
		return -.2;
	}
	
	public static double switchToScale(double eValue)//
	{
//		eValue*=(-0.0000285714285714);
//		eValue+=1.057;
//		return eValue;
		return .4;
		//.8 to .2
	}
	
	public static double scaleToStop(double eValue)//
	{
//		if(eValue > Constants.sWitch)
//		{
//			return -.7;
//		}
//		else
//		{
//			return switchToStop(eValue);
//		}
		return -.2;
	}
	
	public static double scaleToPickUp(double eValue)//
	{
//		if(eValue > Constants.sWitch)
//		{
//			return -.7;
//		}
//		else
//		{
//			return switchToPickUp(eValue);
//		}
		return -.2;
	}
	
	public static double scaleToSwitch(double eValue)
	{
//		eValue*=-0.000142629639014;
//		eValue+=7.56718530718;
//		eValue/=2;
//		speed = sinx(eValue);
//		return -speed;
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
		speed*=1.42;
		return speed;
	}
	
	public static double straightForScale(double eValue)//
	{
		eValue*=0.000143541666667;
		eValue += 1.551;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	public static double BigTurnScale(double eValue)
	{
		eValue*=0.000150299705657;
		eValue += 4.732;
		eValue/=2;
		speed = sinx(eValue);
		return speed;
	}
	
	
}
