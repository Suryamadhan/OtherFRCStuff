package team3647ConstantsAndFunctions;

public class Functions 
{
	public static double sinx(double x)
	{
		return Math.sin(x);
	}
	
	public static double MSRRSWsupposedLeftSpeed(double lEnc)
	{
		if(lEnc < (Constants.MSRRSWfirstbigTurn - 5087))
		{
			return .707;
		}
		else if(lEnc < Constants.MSRRSWfirstbigTurn)
		{
			return .53;
		}
		else if(lEnc < (Constants.MSRRSWfirstbigTurn + 1440))
		{
			return .15;
		}
		else if(lEnc < (Constants.MSRRSWfirstbigTurn + Constants.MSRRSWsecondsmallTurn))
		{
			return .2;
		}
//		else if(lEnc < (Constants.MSRRSWfirstbigTurn + Constants.MSRRSWfirstsmallTurn + Constants.MSRRSWStraight))
//		{
//			return .6;
//		}
		else
		{
			return 0;
		}
	}
	
	public static double MSRRSWsupposedRightSpeed(double rEnc)
	{
		if(rEnc < (Constants.MSRRSWfirstsmallTurn - 1440))
		{
			return .2;
		}
		else if(rEnc < Constants.MSRRSWfirstsmallTurn)
		{
			return .15;
		}
		else if(rEnc < (Constants.MSRRSWfirstsmallTurn + 5087))
		{
			return .53;
		}
		else if(rEnc < (Constants.MSRRSWfirstsmallTurn + Constants.MSRRSWsecondbigTurn))
		{
			return .707;
		}
//		else if(rEnc < (Constants.MSRRSWfirstbigTurn + Constants.MSRRSWfirstsmallTurn + Constants.MSRRSWStraight))
//		{
//			return .6;
//		}
		else
		{
			return 0;
		}
	}
	
	public static double[] MSRRSWcorrection(double lEnc, double rEnc)
	{
		double leftAdjustment, rightAdjustment;
		double []adjustment = new double[2];
		double ratio, sum;
		sum = lEnc + rEnc;
		
		if(sum < 240)
		{
			leftAdjustment = 0;
			rightAdjustment = 0;
		}
		else if(sum < (Constants.MSRRSWfirstbigTurn + Constants.MSRRSWfirstsmallTurn - 6527))
		{
			ratio = (lEnc/rEnc);
			if(ratio >= 3.4 && ratio <= 3.65)
			{
				leftAdjustment = 0;
				rightAdjustment = 0;
			}
			else if(ratio >= 3.25 && ratio <= 3.8)
			{
				if(ratio >= 3.25 && ratio <= 3.4)
				{
					leftAdjustment = 0.05;
					rightAdjustment = -0.05;
				}
				else
				{
					leftAdjustment = -0.05;
					rightAdjustment = 0.05;
				}
			}
			else
			{
				if(ratio < 3.25)
				{
					leftAdjustment = 0.1;
					rightAdjustment = -0.1;
				}
				else
				{
					leftAdjustment = -0.1;
					rightAdjustment = 0.1;
				}
			}
		}
		else if(sum< (Constants.MSRRSWfirstbigTurn + Constants.MSRRSWfirstsmallTurn))
		{
			ratio = (lEnc/rEnc);
			if(ratio >= 3.4 && ratio <= 3.65)
			{
				leftAdjustment = 0;
				rightAdjustment = 0;
			}
			else if(ratio >= 3.25 && ratio <= 3.8)
			{
				if(ratio >= 3.25 && ratio <= 3.4)
				{
					leftAdjustment = 0.025;
					rightAdjustment = -0.025;
				}
				else
				{
					leftAdjustment = -0.025;
					rightAdjustment = 0.025;
				}
			}
			else
			{
				if(ratio < 3.25)
				{
					leftAdjustment = 0.05;
					rightAdjustment = -0.05;
				}
				else
				{
					leftAdjustment = -0.05;
					rightAdjustment = 0.05;
				}
			}
		}
		else if(sum < (Constants.MSRRSWfirstbigTurn + Constants.MSRRSWfirstsmallTurn + 6527))
		{
			ratio = (rEnc - Constants.MSRRSWfirstsmallTurn)/(lEnc - Constants.MSRRSWfirstbigTurn);
			if((rEnc - Constants.MSRRSWfirstsmallTurn) + (lEnc - Constants.MSRRSWfirstbigTurn) < 240)
			{
				leftAdjustment = 0;
				rightAdjustment = 0;
			}
			else if(ratio >= 3.4 && ratio <= 3.65)
			{
				leftAdjustment = 0;
				rightAdjustment = 0;
			}
			else if(ratio >= 3.25 && ratio <= 3.8)
			{
				if(ratio >= 3.25 && ratio <= 3.4)
				{
					leftAdjustment = -0.025;
					rightAdjustment = 0.025;
				}
				else
				{
					leftAdjustment = 0.025;
					rightAdjustment = -0.025;
				}
			}
			else
			{
				if(ratio < 3.25)
				{
					leftAdjustment = -0.05;
					rightAdjustment = 0.05;
				}
				else
				{
					leftAdjustment = 0.05;
					rightAdjustment = -0.05;
				}
			}
		}
		else if(sum < (Constants.MSRRSWfirstsmallTurn + Constants.MSRRSWfirstbigTurn + Constants.MSRRSWsecondbigTurn + Constants.MSRRSWsecondsmallTurn))
		{
			ratio = (rEnc - Constants.MSRRSWfirstsmallTurn)/(lEnc - Constants.MSRRSWfirstbigTurn);
			if(ratio >= 3.4 && ratio <= 3.65)
			{
				leftAdjustment = 0;
				rightAdjustment = 0;
			}
			else if(ratio >= 3.25 && ratio <= 3.8)
			{
				if(ratio >= 3.25 && ratio <= 3.4)
				{
					leftAdjustment = -0.05;
					rightAdjustment = 0.05;
				}
				else
				{
					leftAdjustment = 0.05;
					rightAdjustment = -0.05;
				}
			}
			else
			{
				if(ratio < 3.25)
				{
					leftAdjustment = -0.1;
					rightAdjustment = 0.1;
				}
				else
				{
					leftAdjustment = 0.1;
					rightAdjustment = -0.1;
				}
			}
		}
//		else if(sum < 2* (Constants.MSRRSWfirstbigTurn + Constants.MSRRSWfirstsmallTurn + Constants.MSRRSWStraight))
//		{
//			if(Math.abs(lEnc - rEnc) < 100)
//			{
//				leftAdjustment = 0;
//				rightAdjustment = 0;
//			}
//			else if(Math.abs(lEnc - rEnc) < 200)
//			{
//				if(lEnc > rEnc)
//			 	{
//					leftAdjustment = -.05;
//					rightAdjustment = 0;
//			 	}
//				else
//			 	{
//					leftAdjustment = 0;
//					rightAdjustment = -.05;
//			 	}
//			 }
//			 else if(Math.abs(lEnc - rEnc) < 300)
//			 {
//				 if(lEnc > rEnc)
//				 {
//					 leftAdjustment = -.1;
//					 rightAdjustment = 0;
//				 }
//				 else
//				 {
//					 leftAdjustment = 0;
//					 rightAdjustment = -.1;
//				 }
//			 }
//			 else if(Math.abs(lEnc - rEnc) < 500)
//			 {
//				 if(lEnc > rEnc)
//				 {
//					 leftAdjustment = -.18;
//					 rightAdjustment = 0;
//				 }
//				 else
//				 {
//					 leftAdjustment = 0;
//					 rightAdjustment = -.18;
//				 }
//			 }
//			 else
//			 {
//				 if(lEnc > rEnc)
//				 {
//					 leftAdjustment = -.27;
//					 rightAdjustment = 0;
//				 }
//				 else
//				 {
//					 leftAdjustment = 0;
//					 rightAdjustment = -.27;
//				 }
//			 }
//		}
		else
		{
			leftAdjustment = 0;
			rightAdjustment = 0;
		}
		adjustment[0] = leftAdjustment;
		adjustment[1] = rightAdjustment;
		
		return adjustment;
	}
}
