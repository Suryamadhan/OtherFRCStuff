package team3647ConstantsAndFunctions;

public class NewFunctions 
{
	public static double msrswfStraighToSwitch = 102;
	public static double msrswtotalTurnLength = 56.5486677646;
	public static double msrswsmallTurnLength = 15.7079632679;
	
	public static double mslswInitialStraight = 8;
	public static double mslswMediumTurn = 56.5486677646;
	public static double mslswBigTurn = 97.3893722613;
	public static double mslswSmallTurn = 15.7079632679;
	
	
	public static double msrswfLeftSpeed(double lValue, double rValue, int step)
	{
		if(step == 1)
		{
			if(lValue < msrswtotalTurnLength)
			{
				lValue*=(-0.012378717796);
				lValue+=.9;
				return lValue;
			}
			else
			{
				return 0;
			}
		}
		else if(step == 2)
		{
			if(lValue < msrswfStraighToSwitch)
			{
				lValue*=(-0.005);
				lValue+=.9;
				return lValue;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return 0;
		}
	}
	
	public static double msrswfRightSpeed(double lValue, double rValue, int step)
	{
		if(step == 1)
		{
			if(rValue < msrswsmallTurnLength && lValue < msrswtotalTurnLength)
			{
				rValue =msrswfLeftSpeed(lValue, rValue, step)/3.6;
				return rValue;
			}
			else if(rValue < msrswsmallTurnLength)
			{
				return .2;
			}
			else if(rValue < msrswtotalTurnLength)
			{
				rValue*=(-0.012378717796);
				rValue+=.9;
				return rValue;
			}
			else
			{
				return 0;
			}
		}
		else if(step == 2)
		{
			if(rValue < msrswfStraighToSwitch)
			{
				rValue*=(-0.005);
				rValue+=.9;
				rValue*=.85;
				return rValue;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return 0;
		}
	}
	
	public static double[] msrswfadjustment(double lValue, double rValue, int step)
	{
		double []adjustmentValues = new double[2];
		double ratio;
		if(step == 1)
		{
			if(rValue < msrswsmallTurnLength && lValue < msrswtotalTurnLength)
			{
				ratio = lValue/rValue;
				if(ratio <= 3.7 && ratio >= 3.5)
				{
					adjustmentValues[0] = 0;
					adjustmentValues[1] = 0;
				}
				else
				{
					if(ratio >= 3.7)
					{
						adjustmentValues[0] = -.2;
						adjustmentValues[1] = .1;
					}
					else//<3.5
					{
						adjustmentValues[0] = .2;
						adjustmentValues[1] = -.1;
					}
				}
			}
			else
			{
				adjustmentValues[0] = 0;
				adjustmentValues[1] = 0;
			}
		}
		else if(step == 2)
		{
			if(Math.abs(rValue - lValue) < .2)
			{
				adjustmentValues[0] = 0;
				adjustmentValues[1] = 0;
			}
			else if(lValue > rValue)
			{
				if(Math.abs(rValue - lValue) < .6)
				{
					adjustmentValues[0] = -.1;
					adjustmentValues[1] = .1;
				}
				else
				{
					adjustmentValues[0] = -.2;
					adjustmentValues[1] = .2;
				}
			}
			else if(lValue < rValue)
			{
				if(Math.abs(rValue - lValue) < .6)
				{
					adjustmentValues[0] = .1;
					adjustmentValues[1] = -.1;
				}
				else
				{
					adjustmentValues[0] = .2;
					adjustmentValues[1] = -.2;
				}
			}
			else
			{
				adjustmentValues[0] = 0;
				adjustmentValues[1] = 0;
			}
		}
		else
		{
			adjustmentValues[0] = 0;
			adjustmentValues[1] = 0;
		}
		return adjustmentValues;
	}
	
	public static double mslsswfLeftSpeed(double lValue, double rValue, int step)
	{
		if(step == 1)
		{
			if(lValue < mslswInitialStraight)
			{
				return .4;
			}
			else
			{
				return 0;
			}
		}
		if(step == 2)
		{
			if(lValue < mslswMediumTurn && rValue < mslswBigTurn)
			{
				lValue =mslsswfRightSpeed(lValue, rValue, step)/1.72222222222;
				return lValue;
			}
			else if(lValue < mslswMediumTurn)
			{
				return .2;
			}
			else
			{
				return 0;
			}
		}
		else
		{
			return 0;
		}
	}
	
	public static double mslsswfRightSpeed(double lValue, double rValue, int step)
	{
		if(step == 1)
		{
			if(rValue < mslswInitialStraight)
			{
				return .37;
			}
			else
			{
				return 0;
			}
		}
		else if(step == 2)
		{
			if(rValue < mslswBigTurn)
			{
				rValue*=(-0.00410722433785);
				rValue+=.8;
				return rValue;
				//.8 to .4
			}
		}
		else
		{
			return 0;
		}
	}
	
	public static double[] mslsswfadjustment(double lValue, double rValue, int step)
	{
		double []adjustmentValues = new double[2];
		double ratio;
		
		if(step == 1)
		{
			if(Math.abs(rValue - lValue) < .2)
			{
				adjustmentValues[0] = 0;
				adjustmentValues[1] = 0;
			}
			else if(lValue > rValue)
			{
				if(Math.abs(rValue - lValue) < .6)
				{
					adjustmentValues[0] = -.1;
					adjustmentValues[1] = .1;
				}
				else
				{
					adjustmentValues[0] = -.2;
					adjustmentValues[1] = .2;
				}
			}
			else if(lValue < rValue)
			{
				if(Math.abs(rValue - lValue) < .6)
				{
					adjustmentValues[0] = .1;
					adjustmentValues[1] = -.1;
				}
				else
				{
					adjustmentValues[0] = .2;
					adjustmentValues[1] = -.2;
				}
			}
			else
			{
				adjustmentValues[0] = 0;
				adjustmentValues[1] = 0;
			}
		}
		
		else
		{
			adjustmentValues[0] = 0;
			adjustmentValues[1] = 0;
		}
		
		return adjustmentValues;
	}
	

}
