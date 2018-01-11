package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import team3647ConstantsAndFunctions.Constants;
//import team3647subsystems.DigitalInputs;
import team3647subsystems.Drivetrain;
import team3647subsystems.Encoders;

public class Autonomous 
{
	int currentState;
	double leftEncoder, rightEncoder;
	double requiredLeftDist, requiredRightDist, requiredStraightDist = 0;
	int autoSelected;
	String gameData;
	boolean leftStation, rightStation, middleStation;
	double aimedRatio, currentRatio;
	double sum;
	boolean withinRange;
	
	public void runAuto(double lValue, double rValue)
	{
		selectAuto();
		leftEncoder = lValue;
		rightEncoder = rValue;
		switch(autoSelected)
		{
			case 1:
				middleStationLLSWSC();
				break;
			case 2:
				middleStationLR();
				break;
			case 3:
				middleStationRR();
				break;
			case 4:
				middleStationRL();
				break;
			case 5:
				leftStationLL();
				break;
			case 6:
				leftStationLR();
				break;
			case 7:
				leftStationRR();
				break;
			case 8:
				leftStationRL();
				break;
			case 9:
				rightStationLL();
				break;
			case 10:
				rightStationLR();
				break;
			case 11:
				rightStationRR();
				break;
			case 12:
				rightStationRL();
				break;
			case 13:
				break;
		}
	}
	
	public void setStation()
	{
//		DigitalInputs.setPinValues();
//		if(DigitalInputs.left && !DigitalInputs.right && !DigitalInputs.middle)
//		{
//			leftStation = true;
//			rightStation = false;
//			middleStation = false;
//		}
//		else if(!DigitalInputs.left && DigitalInputs.right && !DigitalInputs.middle)
//		{
//			leftStation = false;
//			rightStation = true;
//			middleStation = false;
//		}
//		else if(!DigitalInputs.left && !DigitalInputs.right && DigitalInputs.middle)
//		{
//			leftStation = false;
//			rightStation = false;
//			middleStation = true;
//		}
//		else
//		{
//			leftStation = false;
//			rightStation = false;
//			middleStation = false;
//		}
		
		leftStation = false;//
		rightStation = false;//
		middleStation = true;//
		
	}
	
	public void selectAuto()
	{
		setStation();
//		gameData = DriverStation.getInstance().getGameSpecificMessage();
		gameData = "LL";//
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
	
	public void middleStationLLSWSC()
	{		
		switch(currentState)
		{
			case 1:
				requiredStraightDist = (Constants.initialStraightLLSWSC - 300);
				if(!Drivetrain.reachedDistance(leftEncoder, rightEncoder, requiredStraightDist))
				{
					Drivetrain.driveForward(leftEncoder, rightEncoder, .8);
				}
				else
				{
					currentState = 2;
				}
				break;
			case 2:
				requiredStraightDist = Constants.initialStraightLLSWSC;
				if(!Drivetrain.reachedDistance(leftEncoder, rightEncoder, requiredStraightDist))
				{
					Drivetrain.driveForward(leftEncoder, rightEncoder, .5);
				}
				else
				{
					requiredStraightDist = 0;
					Encoders.resetEncoders();//
					currentState = 3;//
					//Must reach around 36 inches
//					currentState = 8;
				}
				break;
			case 3:
				requiredLeftDist = (Constants.smallTurnForSwitchSWSC - 200);
				requiredRightDist = (Constants.bigTurnForSwitchSWSC - 400);
				aimedRatio = ((requiredRightDist)/(requiredLeftDist));
				currentRatio = ((rightEncoder/leftEncoder)/aimedRatio);
				sum = rightEncoder + leftEncoder;
				if(currentRatio >= .9 && currentRatio <= 1.1)
				{
					withinRange = true;
				}
				else
				{
					withinRange = false;
				}
				if(sum < requiredLeftDist + requiredRightDist)
				{
					if(withinRange || sum < 50)
					{
						Drivetrain.setLeftMotorSpeed(.325);
						Drivetrain.setRightMotorSpeed(-.65);
					}
					else
					{
						if(currentRatio > 1.1 && currentRatio < 1.18)
						{
							Drivetrain.setLeftMotorSpeed(.425);
							Drivetrain.setRightMotorSpeed(-.55);
						}
						else if(currentRatio > 1.18 && currentRatio < 1.25)
						{
							Drivetrain.setLeftMotorSpeed(.525);
							Drivetrain.setRightMotorSpeed(-.45);
						}
						else if(currentRatio > 1.25)
						{
							Drivetrain.setLeftMotorSpeed(.625);
							Drivetrain.setRightMotorSpeed(-.35);
						}
						else if(currentRatio < .9 && currentRatio > .82)
						{
							Drivetrain.setLeftMotorSpeed(.225);
							Drivetrain.setRightMotorSpeed(-.75);
						}
						else if(currentRatio < .82 && currentRatio > .75)
						{
							Drivetrain.setLeftMotorSpeed(.125);
							Drivetrain.setRightMotorSpeed(-.85);
						}
						else
						{
							Drivetrain.setLeftMotorSpeed(.025);
							Drivetrain.setRightMotorSpeed(-.95);
						}
					}
				}
				else
				{
					currentState = 4;
				}
				break;
			case 4:
				requiredLeftDist = Constants.smallTurnForSwitchSWSC;
				requiredRightDist = Constants.bigTurnForSwitchSWSC;
				aimedRatio = ((requiredRightDist)/(requiredLeftDist));
				currentRatio = ((rightEncoder/leftEncoder)/aimedRatio);
				sum = rightEncoder + leftEncoder;
				if(currentRatio >= .9 && currentRatio <= 1.1)
				{
					withinRange = true;
				}
				else
				{
					withinRange = false;
				}
				if(sum < requiredLeftDist + requiredRightDist)
				{
					if(withinRange || sum < 50)
					{
						Drivetrain.setLeftMotorSpeed(.22);
						Drivetrain.setRightMotorSpeed(-.44);
					}
					else
					{
						if(currentRatio > 1.1 && currentRatio < 1.18)
						{
							Drivetrain.setLeftMotorSpeed(.3);
							Drivetrain.setRightMotorSpeed(-.36);
						}
						else if(currentRatio > 1.18 && currentRatio < 1.25)
						{
							Drivetrain.setLeftMotorSpeed(.38);
							Drivetrain.setRightMotorSpeed(-.28);
						}
						else if(currentRatio > 1.25)
						{
							Drivetrain.setLeftMotorSpeed(.46);
							Drivetrain.setRightMotorSpeed(-.2);
						}
						else if(currentRatio < .9 && currentRatio > .82)
						{
							Drivetrain.setLeftMotorSpeed(.14);
							Drivetrain.setRightMotorSpeed(-.52);
						}
						else if(currentRatio < .82 && currentRatio > .75)
						{
							Drivetrain.setLeftMotorSpeed(.06);
							Drivetrain.setRightMotorSpeed(-.6);
						}
						else
						{
							Drivetrain.setLeftMotorSpeed(0);
							Drivetrain.setRightMotorSpeed(-.66);
						}
					}
				}
				else
				{
					requiredLeftDist = 0;
					requiredRightDist = 0;
					Encoders.resetEncoders();
					currentState = 5;
				}
				break;
			case 5:
				requiredLeftDist = 400;
				requiredRightDist = 200;
				aimedRatio = ((requiredLeftDist)/(requiredRightDist));
				currentRatio = ((leftEncoder/rightEncoder)/aimedRatio);
				sum = rightEncoder + leftEncoder;
				if(currentRatio >= .9 && currentRatio <= 1.1)
				{
					withinRange = true;
				}
				else
				{
					withinRange = false;
				}
				if(sum < requiredLeftDist + requiredRightDist)
				{
					if(withinRange || sum < 50)
					{
						Drivetrain.setLeftMotorSpeed(.44);
						Drivetrain.setRightMotorSpeed(-.22);
					}
					else
					{
						if(currentRatio > 1.1 && currentRatio < 1.18)
						{
							Drivetrain.setLeftMotorSpeed(.36);
							Drivetrain.setRightMotorSpeed(-.3);
						}
						else if(currentRatio > 1.18 && currentRatio < 1.25)
						{
							Drivetrain.setLeftMotorSpeed(.28);
							Drivetrain.setRightMotorSpeed(-.38);
						}
						else if(currentRatio > 1.25)
						{
							Drivetrain.setLeftMotorSpeed(.2);
							Drivetrain.setRightMotorSpeed(-.46);
						}
						else if(currentRatio < .9 && currentRatio > .82)
						{
							Drivetrain.setLeftMotorSpeed(.52);
							Drivetrain.setRightMotorSpeed(-.14);
						}
						else if(currentRatio < .82 && currentRatio > .75)
						{
							Drivetrain.setLeftMotorSpeed(.6);
							Drivetrain.setRightMotorSpeed(-.06);
						}
						else
						{
							Drivetrain.setLeftMotorSpeed(.66);
							Drivetrain.setRightMotorSpeed(0);
						}
					}
				}
				else
				{
					currentState = 6;
				}
				break;
			case 6:
				requiredLeftDist = Constants.bigTurnForSwitchSWSC;
				requiredRightDist = Constants.smallTurnForSwitchSWSC;
				aimedRatio = ((requiredLeftDist)/(requiredRightDist));
				currentRatio = ((leftEncoder/rightEncoder)/aimedRatio);
				sum = rightEncoder + leftEncoder;
				if(currentRatio >= .9 && currentRatio <= 1.1)
				{
					withinRange = true;
				}
				else
				{
					withinRange = false;
				}
				if(sum < requiredLeftDist + requiredRightDist)
				{
					if(withinRange || sum < 50)
					{
						Drivetrain.setLeftMotorSpeed(.65);
						Drivetrain.setRightMotorSpeed(-.325);
					}
					else
					{
						if(currentRatio > 1.1 && currentRatio < 1.18)
						{
							Drivetrain.setLeftMotorSpeed(.55);
							Drivetrain.setRightMotorSpeed(-.425);
						}
						else if(currentRatio > 1.18 && currentRatio < 1.25)
						{
							Drivetrain.setLeftMotorSpeed(.45);
							Drivetrain.setRightMotorSpeed(-.525);
						}
						else if(currentRatio > 1.25)
						{
							Drivetrain.setLeftMotorSpeed(.35);
							Drivetrain.setRightMotorSpeed(-.625);
						}
						else if(currentRatio < .9 && currentRatio > .82)
						{
							Drivetrain.setLeftMotorSpeed(.75);
							Drivetrain.setRightMotorSpeed(-.225);
						}
						else if(currentRatio < .82 && currentRatio > .75)
						{
							Drivetrain.setLeftMotorSpeed(.85);
							Drivetrain.setRightMotorSpeed(-.125);
						}
						else
						{
							Drivetrain.setLeftMotorSpeed(.95);
							Drivetrain.setRightMotorSpeed(0.025);
						}
					}
				}
				else
				{
					requiredLeftDist = 0;
					requiredRightDist = 0;
					Encoders.resetEncoders();
					currentState = 7;
				}
				break;
			case 7:
				requiredStraightDist = Constants.distanceSwitchLLSWSC;
				if(!Drivetrain.reachedDistance(leftEncoder, rightEncoder, requiredStraightDist))
				{
					Drivetrain.driveForward(leftEncoder, rightEncoder, .3);
				}
				else
				{
					//Must reach around 140 inches
					requiredStraightDist = 0;
					currentState = 8;
				}
				break;
			case 8:
				Drivetrain.setLeftMotorSpeed(0);
				Drivetrain.setRightMotorSpeed(0);
//				deliver box to switch
//				Encoders.resetEncoders();
//				Timer.delay(1);
				currentState = 9;
				Encoders.testEncoders();//
				break;
			case 9:
				requiredStraightDist = Constants.distanceSwitchLLSWSC;
				if(!Drivetrain.reachedDistance(leftEncoder, rightEncoder, requiredStraightDist))
				{
					Drivetrain.driveBackward(leftEncoder, rightEncoder, -.3);
				}
				else
				{
					requiredStraightDist = 0;
					Encoders.resetEncoders();
					currentState = 10;
				}
				break;
			case 10:
				requiredLeftDist = 400;
				requiredRightDist = 720;
				rightEncoder = Math.abs(rightEncoder);
				leftEncoder = Math.abs(leftEncoder);
				aimedRatio = ((requiredRightDist)/(requiredLeftDist));
				currentRatio = ((rightEncoder/leftEncoder)/aimedRatio);
				sum = rightEncoder + leftEncoder;
				if(currentRatio >= .9 && currentRatio <= 1.1)
				{
					withinRange = true;
				}
				else
				{
					withinRange = false;
				}
				if(sum < requiredLeftDist + requiredRightDist)
				{
					if(withinRange || sum < 50)
					{
						Drivetrain.setLeftMotorSpeed(-.3);
						Drivetrain.setRightMotorSpeed(.54);
					}
					else
					{
						if(currentRatio > 1.1 && currentRatio < 1.18)
						{
							Drivetrain.setLeftMotorSpeed(-.4);
							Drivetrain.setRightMotorSpeed(.44);
						}
						else if(currentRatio > 1.18 && currentRatio < 1.25)
						{
							Drivetrain.setLeftMotorSpeed(-.5);
							Drivetrain.setRightMotorSpeed(.34);
						}
						else if(currentRatio > 1.25)
						{
							Drivetrain.setLeftMotorSpeed(-.6);
							Drivetrain.setRightMotorSpeed(.24);
						}
						else if(currentRatio < .9 && currentRatio > .82)
						{
							Drivetrain.setLeftMotorSpeed(-.2);
							Drivetrain.setRightMotorSpeed(.64);
						}
						else if(currentRatio < .82 && currentRatio > .75)
						{
							Drivetrain.setLeftMotorSpeed(-.1);
							Drivetrain.setRightMotorSpeed(.74);
						}
						else
						{
							Drivetrain.setLeftMotorSpeed(0);
							Drivetrain.setRightMotorSpeed(.84);
						}
					}
				}
				else
				{
					currentState = 11;
				}
				break;
			case 11:
				requiredLeftDist = Constants.smallTurnFromSwitchSWSC;
				requiredRightDist = Constants.bigTurnFromSwitchSWSC;
				rightEncoder = Math.abs(rightEncoder);
				leftEncoder = Math.abs(leftEncoder);
				aimedRatio = ((requiredRightDist)/(requiredLeftDist));
				currentRatio = ((rightEncoder/leftEncoder)/aimedRatio);
				sum = rightEncoder + leftEncoder;
				if(currentRatio >= .9 && currentRatio <= 1.1)
				{
					withinRange = true;
				}
				else
				{
					withinRange = false;
				}
				if(sum < requiredLeftDist + requiredRightDist)
				{
					if(withinRange || sum < 50)
					{
						Drivetrain.setLeftMotorSpeed(-.45);
						Drivetrain.setRightMotorSpeed(.81);
					}
					else
					{
						if(currentRatio > 1.1 && currentRatio < 1.18)
						{
							Drivetrain.setLeftMotorSpeed(-.51);
							Drivetrain.setRightMotorSpeed(.75);
						}
						else if(currentRatio > 1.18 && currentRatio < 1.25)
						{
							Drivetrain.setLeftMotorSpeed(-.57);
							Drivetrain.setRightMotorSpeed(.69);
						}
						else if(currentRatio > 1.25)
						{
							Drivetrain.setLeftMotorSpeed(-.63);
							Drivetrain.setRightMotorSpeed(.63);
						}
						else if(currentRatio < .9 && currentRatio > .82)
						{
							Drivetrain.setLeftMotorSpeed(-.39);
							Drivetrain.setRightMotorSpeed(.87);
						}
						else if(currentRatio < .82 && currentRatio > .75)
						{
							Drivetrain.setLeftMotorSpeed(-.33);
							Drivetrain.setRightMotorSpeed(.93);
						}
						else
						{
							Drivetrain.setLeftMotorSpeed(-.27);
							Drivetrain.setRightMotorSpeed(.99);
						}
					}
				}
				else
				{
					requiredLeftDist = 0;
					requiredRightDist = 0;
					Encoders.resetEncoders();
					currentState = 12;
				}
				break;
			case 12:
				requiredStraightDist = (Constants.backUpToPickUpCubeSWSC - 360);
				if(!Drivetrain.reachedDistance(leftEncoder, rightEncoder, requiredStraightDist))
				{
					Drivetrain.driveBackward(leftEncoder, rightEncoder, -.8);
				}
				else
				{
					currentState = 13;
				}
				break;
			case 13:
				requiredStraightDist = Constants.backUpToPickUpCubeSWSC;
				if(!Drivetrain.reachedDistance(leftEncoder, rightEncoder, requiredStraightDist))
				{
					Drivetrain.driveBackward(leftEncoder, rightEncoder, -.5);
				}
				else
				{
					Drivetrain.setLeftMotorSpeed(0);
					Drivetrain.setRightMotorSpeed(0);
					Encoders.resetEncoders();
					Timer.delay(.4);
					currentState = 15;
				}
				break;
			case 14:
				requiredLeftDist = (Constants.bigTurnForSwitchSWSC);
				requiredRightDist = (Constants.smallTurnForSwitchSWSC);
				leftEncoder = Math.abs(leftEncoder);
				rightEncoder = Math.abs(rightEncoder);
				aimedRatio = ((requiredRightDist)/(requiredLeftDist));
				currentRatio = ((rightEncoder/leftEncoder)/aimedRatio);
				sum = rightEncoder + leftEncoder;
				if(currentRatio >= .9 && currentRatio <= 1.1)
				{
					withinRange = true;
				}
				else
				{
					withinRange = false;
				}
				if(sum < requiredLeftDist + requiredRightDist)
				{
					if(withinRange || sum < 50)
					{
						Drivetrain.setLeftMotorSpeed(-.3);
						Drivetrain.setRightMotorSpeed(.54);
					}
					else
					{
						if(currentRatio > 1.1 && currentRatio < 1.18)
						{
							Drivetrain.setLeftMotorSpeed(-.4);
							Drivetrain.setRightMotorSpeed(.44);
						}
						else if(currentRatio > 1.18 && currentRatio < 1.25)
						{
							Drivetrain.setLeftMotorSpeed(-.5);
							Drivetrain.setRightMotorSpeed(.34);
						}
						else if(currentRatio > 1.25)
						{
							Drivetrain.setLeftMotorSpeed(-.6);
							Drivetrain.setRightMotorSpeed(.24);
						}
						else if(currentRatio < .9 && currentRatio > .82)
						{
							Drivetrain.setLeftMotorSpeed(-.2);
							Drivetrain.setRightMotorSpeed(.64);
						}
						else if(currentRatio < .82 && currentRatio > .75)
						{
							Drivetrain.setLeftMotorSpeed(-.1);
							Drivetrain.setRightMotorSpeed(.74);
						}
						else
						{
							Drivetrain.setLeftMotorSpeed(0);
							Drivetrain.setRightMotorSpeed(.84);
						}
					}
				}
				else
				{
					currentState = 11;
				}
				break;
			case 15:
				Drivetrain.setLeftMotorSpeed(0);
				Drivetrain.setRightMotorSpeed(0);
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