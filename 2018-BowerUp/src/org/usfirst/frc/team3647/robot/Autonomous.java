package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.DriverStation;
import team3647subsystems.DigitalInputs;

public class Autonomous 
{
	int currentState;
	double leftEncoder, rightEncoder;
	int autoSelected;
	String gameData;
	boolean leftStation, rightStation, middleStation;
	
	public void runAuto(double lValue, double rValue)
	{
		selectAuto();
		leftEncoder = lValue;
		rightEncoder = rValue;
		switch(autoSelected)
		{
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			case 9:
				break;
			case 10:
				break;
			case 11:
				break;
			case 12:
				break;
			case 13:
				break;
		}
	}
	
	public void setStation()
	{
		DigitalInputs.setPinValues();
		if(DigitalInputs.left && !DigitalInputs.right && !DigitalInputs.middle)
		{
			leftStation = true;
			rightStation = false;
			middleStation = false;
		}
		else if(!DigitalInputs.left && DigitalInputs.right && !DigitalInputs.middle)
		{
			leftStation = false;
			rightStation = true;
			middleStation = false;
		}
		else if(!DigitalInputs.left && !DigitalInputs.right && DigitalInputs.middle)
		{
			leftStation = false;
			rightStation = false;
			middleStation = true;
		}
		else
		{
			leftStation = false;
			rightStation = false;
			middleStation = false;
		}
	}
	
	public void selectAuto()
	{
		setStation();
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		if(gameData.charAt(0) == 'L' && gameData.charAt(0) == 'L' && middleStation && !rightStation && !leftStation)
		{
			autoSelected = 1;
		} 
		else if(gameData.charAt(0) == 'L' && gameData.charAt(0) == 'R' && middleStation && !rightStation && !leftStation)
		{
			autoSelected = 2;
		}
		else if(gameData.charAt(0) == 'R' && gameData.charAt(0) == 'R' && middleStation && !rightStation && !leftStation)
		{
			autoSelected = 3;
		}
		else if(gameData.charAt(0) == 'R' && gameData.charAt(0) == 'L' && middleStation && !rightStation && !leftStation)
		{
			autoSelected = 4;
		}
		else if(gameData.charAt(0) == 'L' && gameData.charAt(0) == 'L' && !middleStation && !rightStation && leftStation)
		{
			autoSelected = 5;
		} 
		else if(gameData.charAt(0) == 'L' && gameData.charAt(0) == 'R' && !middleStation && !rightStation && leftStation)
		{
			autoSelected = 6;
		}
		else if(gameData.charAt(0) == 'R' && gameData.charAt(0) == 'R' && !middleStation && !rightStation && leftStation)
		{
			autoSelected = 7;
		}
		else if(gameData.charAt(0) == 'R' && gameData.charAt(0) == 'L' && !middleStation && !rightStation && leftStation)
		{
			autoSelected = 8;
		}
		else if(gameData.charAt(0) == 'L' && gameData.charAt(0) == 'L' && !middleStation && rightStation && !leftStation)
		{
			autoSelected = 9;
		} 
		else if(gameData.charAt(0) == 'L' && gameData.charAt(0) == 'R' && !middleStation && rightStation && !leftStation)
		{
			autoSelected = 10;
		}
		else if(gameData.charAt(0) == 'R' && gameData.charAt(0) == 'R' && !middleStation && rightStation && !leftStation)
		{
			autoSelected = 11;
		}
		else if(gameData.charAt(0) == 'R' && gameData.charAt(0) == 'L' && !middleStation && rightStation && !leftStation)
		{
			autoSelected = 12;
		}
		else
		{
			autoSelected = 13;
		}
		
	}
	
	public void middleStationLL()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void middleStationLR()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void middleStationRR()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void middleStationRL()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void leftStationLL()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void leftStationLR()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void leftStationRR()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void leftStationRL()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void rightStationLL()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void rightStationLR()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void rightStationRR()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
		}
	}
	
	public void rightStationRL()
	{
		switch(currentState)
		{
			case 1: 
				break;
				
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
