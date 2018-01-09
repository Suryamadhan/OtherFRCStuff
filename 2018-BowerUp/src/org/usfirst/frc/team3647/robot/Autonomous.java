package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.DriverStation;

public class Autonomous 
{
	double leftEncoder, rightEncoder;
	int autoSelected;
	String gameData;
	
	public Autonomous(double lValue, double rValue)
	{
		leftEncoder = lValue;
		rightEncoder = rValue;
	}
	
	public void runAuto()
	{
		
	}
	
	public String station()
	{
		return "";
	}
	
	public int selectAuto()
	{
		
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L' && gameData.charAt(0) == 'L')
		{
			return 1;
		} 
		else 
		{
			
		}
	}

}
/*
Middle Station:
LL = 1;
LR = 2;
RR = 3;
RL = 4;

Left Station:
LL = 5;
LR = 6;
RR = 7;
RL = 8;

Right Station:
LL = 9;
LR = 10;
RR = 11;
RL = 12;
 */
