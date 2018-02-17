package team3647ConstantsAndFunctions;

public class NewFunctions 
{
	public static double msrsrfStraighToSwitch = 102;
	public static double msrswtotalTurnLength = 56.5486677646;
	public static double msrswsmallTurnLength = 15.7079632679;
	
	public static double msrswfLeftSpeed(double lValue, double rValue, double step)
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
			if(lValue < msrsrfStraighToSwitch)
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
	
	public static double msrswfRightSpeed(double lValue, double rValue, double step)
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
			if(rValue < msrsrfStraighToSwitch)
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
	
	public static double[] msrswfadjustment(double lValue, double rValue, double step)
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
			if(Math.abs(rValue - lValue) < 24)
			{
				adjustmentValues[0] = 0;
				adjustmentValues[1] = 0;
			}
			else if(lValue > rValue)
			{
				if(Math.abs(rValue - lValue) < 100)
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
				if(Math.abs(rValue - lValue) < 100)
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
