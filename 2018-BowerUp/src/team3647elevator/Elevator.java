package team3647elevator;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import team3647ConstantsAndFunctions.Constants;
import team3647ConstantsAndFunctions.Functions;

public class Elevator 
{
	public static int elevatorState;
	/*
	 * 0. Start
	 * 1. Stop
	 * 2. PickUp
	 * 3. Switch
	 * 4. Scale
	 */
	public static int aimedElevatorState;
	
	public static boolean stop, pickUp, sWitch, scale, moving, manualOverride, originalPositionButton;
	static double overrideValue;
	
	public static WPI_TalonSRX leftElevator = new WPI_TalonSRX(52);
	public static WPI_TalonSRX rightElevator = new WPI_TalonSRX(62);
	
	public static VictorSPX leftElevatorSPX = new VictorSPX(54);
	public static VictorSPX rightElevatorSPX = new VictorSPX(57);
	
	public static DifferentialDrive elevatorDrive = new DifferentialDrive(leftElevator, rightElevator);
	
	public static void moveEleVader(double speed)
	{
		elevatorDrive.tankDrive(-speed, speed, false);
//		leftElevatorSPX.set(ControlMode.PercentOutput, -speed);
//		rightElevatorSPX.set(ControlMode.PercentOutput, -speed);
		leftElevatorSPX.follow(leftElevator);
		rightElevatorSPX.follow(rightElevator);
	}
	
	public static void stopEleVader()
	{
		moveEleVader(0);
	}
	
	public static void setElevatorButtons(boolean stopButton, boolean pickUpButton, boolean switchButton, boolean scaleButton)
	{
		stop = stopButton;
		pickUp = pickUpButton;
		sWitch = switchButton;
		scale = scaleButton;
	}
	
	public static void setManualOverride(double jValue)
	{
		if(Math.abs(jValue) >.2 )
		{
			manualOverride = false;
		}
		else
		{
			overrideValue = jValue;
			manualOverride = true;
		}
	}
	
	public static void runDarthVader()
	{
		switch(elevatorState)
		{
			case 0://start
				if(ElevatorLevel.reachedStop())
				{
					stopEleVader();
					aimedElevatorState = 1;
					elevatorState = 1;
				}
				else
				{
					moveEleVader(-.2);
				}
				break;
			case 1://stop
				if(manualOverride)
				{
					aimedElevatorState = -1;
				}
				else if(stop)
				{
					aimedElevatorState = 1;
					originalPositionButton = true;
				}
				else if(pickUp)
				{
					aimedElevatorState = 2;
					originalPositionButton = false;
				}
				else if(sWitch)
				{
					aimedElevatorState = 3;
					originalPositionButton = false;
				}
				else if(scale)
				{
					aimedElevatorState = 4;
					originalPositionButton = false;
				}
				switch(aimedElevatorState)
				{
					case 1:
						if(originalPositionButton)
						{
							if(ElevatorLevel.reachedStop())
							{
								stopEleVader();
								originalPositionButton = false;
							}
							else
							{
								moveEleVader(-.2);//
							}
						}
						else
						{
							stopEleVader();
						}
						break;
					case 2:
						if(ElevatorLevel.reachedPickUp())
						{
							stopEleVader();
							elevatorState = 2;
						}
						else
						{
							moveEleVader(.25);//
						}
						break;
					case 3:
						if(ElevatorLevel.reachedSwitch())
						{
							stopEleVader();
							elevatorState = 3;
						}
						else
						{
							moveEleVader(.4);//
						}
						break;
					case 4:
						if(ElevatorLevel.reachedScale())
						{
							stopEleVader();
							elevatorState = 4;
						}
						else
						{
							moveEleVader(.5);//
						}
						break;
					case -1:
						elevatorState = -1;
						break;
				}
				break;
			case 2://pickup
				if(manualOverride)
				{
					aimedElevatorState = -1;
				}
				else if(stop)
				{
					aimedElevatorState = 1;
					originalPositionButton = false;
				}
				else if(pickUp)
				{
					aimedElevatorState = 2;
					originalPositionButton = true;
				}
				else if(sWitch)
				{
					aimedElevatorState = 3;
					originalPositionButton = false;
				}
				else if(scale)
				{
					aimedElevatorState = 4;
					originalPositionButton = false;
				}
				switch(aimedElevatorState)
				{
					case 1:
						if(ElevatorLevel.reachedStop())
						{
							stopEleVader();
							elevatorState = 1;
						}
						else
						{
							moveEleVader(-.2);//
						}
						break;
					case 2:
						if(originalPositionButton)
						{
							if(ElevatorLevel.reachedPickUp())
							{
								stopEleVader();
								originalPositionButton = false;
							}
							else
							{
								if(ElevatorLevel.elevatorEncoderValue > Constants.pickUp)
								{
									moveEleVader(-.2);//
								}
								else
								{
									moveEleVader(.2);//
								}
								
							}
						}
						break;
					case 3:
						if(ElevatorLevel.reachedSwitch())
						{
							stopEleVader();
							elevatorState = 3;
						}
						else
						{
							moveEleVader(.35);//
						}
						break;
					case 4:
						if(ElevatorLevel.reachedScale())
						{
							stopEleVader();
							elevatorState = 4;
						}
						else
						{
							moveEleVader(.45);//
						}
						break;
					case -1:
						elevatorState = -1;
						break;
				}
				break;
			case 3:
				if(manualOverride)
				{
					aimedElevatorState = -1;
				}
				else if(stop)
				{
					aimedElevatorState = 1;
					originalPositionButton = false;
				}
				else if(pickUp)
				{
					aimedElevatorState = 2;
					originalPositionButton = false;
				}
				else if(sWitch)
				{
					aimedElevatorState = 3;
					originalPositionButton = true;
				}
				else if(scale)
				{
					aimedElevatorState = 4;
					originalPositionButton = false;
				}
				switch(aimedElevatorState)
				{
					case 1:
						if(ElevatorLevel.reachedStop())
						{
							stopEleVader();
							elevatorState = 1;
						}
						else
						{
							moveEleVader(-.2);//
						}
						break;
					case 2:
						if(ElevatorLevel.reachedPickUp())
						{
							stopEleVader();
							elevatorState = 2;
						}
						else
						{
							moveEleVader(-.2);//
						}
						
						break;
					case 3:
						if(originalPositionButton)
						{
							if(ElevatorLevel.reachedSwitch())
							{
								stopEleVader();
								originalPositionButton = false;
							}
							else
							{
								if(ElevatorLevel.elevatorEncoderValue > Constants.sWitch)
								{
									moveEleVader(-.2);//
								}
								else
								{
									moveEleVader(.25);//
								}
								
							}
						}
						break;
					case 4:
						if(ElevatorLevel.reachedScale())
						{
							stopEleVader();
							elevatorState = 4;
						}
						else
						{
							moveEleVader(.4);//
						}
						break;
					case -1:
						elevatorState = -1;
						break;
				}
				break;
			case 4:
				if(manualOverride)
				{
					aimedElevatorState = -1;
				}
				else if(stop)
				{
					aimedElevatorState = 1;
					originalPositionButton = false;
				}
				else if(pickUp)
				{
					aimedElevatorState = 2;
					originalPositionButton = false;
				}
				else if(sWitch)
				{
					aimedElevatorState = 3;
					originalPositionButton = false;
				}
				else if(scale)
				{
					aimedElevatorState = 4;
					originalPositionButton = true;
				}
				switch(aimedElevatorState)
				{
					case 1:
						if(ElevatorLevel.reachedStop())
						{
							stopEleVader();
							elevatorState = 1;
						}
						else
						{
							moveEleVader(-.2);//
						}
						break;
					case 2:
						if(ElevatorLevel.reachedPickUp())
						{
							stopEleVader();
							elevatorState = 2;
						}
						else
						{
							moveEleVader(-.3);//
						}
						
						break;
					case 3:
						if(ElevatorLevel.reachedSwitch())
						{
							stopEleVader();
							elevatorState = 3;
						}
						else
						{
							moveEleVader(-.25);//
						}
						break;
					case 4:
						if(originalPositionButton)
						{
							if(ElevatorLevel.reachedSwitch())
							{
								stopEleVader();
								originalPositionButton = false;
							}
							else
							{
								if(ElevatorLevel.elevatorEncoderValue > Constants.scale)
								{
									moveEleVader(-.1);//
								}
								else
								{
									moveEleVader(.25);//
								}
								
							}
						}
						break;
					case -1:
						elevatorState = -1;
						break;
				}
				break;
			case -1:
				if(stop || pickUp || sWitch || scale)
				{
					if(stop)
					{
						if(ElevatorLevel.reachedStop())
						{
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
							aimedElevatorState = 1;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= 0 && ElevatorLevel.elevatorEncoderValue < Constants.pickUp)
						{
							elevatorState = 1;
							aimedElevatorState = 1;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.pickUp && ElevatorLevel.elevatorEncoderValue < Constants.sWitch)
						{
							elevatorState = 2;
							aimedElevatorState = 1;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.sWitch && ElevatorLevel.elevatorEncoderValue < Constants.scale)
						{
							elevatorState = 3;
							aimedElevatorState = 1;
						}
						else
						{
							elevatorState = 4;
							aimedElevatorState = 1;
						}	
					}
					else if(pickUp)
					{
						if(ElevatorLevel.reachedStop())
						{
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
							aimedElevatorState = 2;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= 0 && ElevatorLevel.elevatorEncoderValue < Constants.pickUp)
						{
							elevatorState = 1;
							aimedElevatorState = 2;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.pickUp && ElevatorLevel.elevatorEncoderValue < Constants.sWitch)
						{
							elevatorState = 2;
							aimedElevatorState = 2;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.sWitch && ElevatorLevel.elevatorEncoderValue < Constants.scale)
						{
							elevatorState = 3;
							aimedElevatorState = 2;
						}
						else
						{
							elevatorState = 4;
							aimedElevatorState = 2;
						}
					}
					else if(sWitch)
					{
						if(ElevatorLevel.reachedStop())
						{
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
							aimedElevatorState = 3;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= 0 && ElevatorLevel.elevatorEncoderValue < Constants.pickUp)
						{
							elevatorState = 1;
							aimedElevatorState = 3;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.pickUp && ElevatorLevel.elevatorEncoderValue < Constants.sWitch)
						{
							elevatorState = 2;
							aimedElevatorState = 3;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.sWitch && ElevatorLevel.elevatorEncoderValue < Constants.scale)
						{
							elevatorState = 3;
							aimedElevatorState = 3;
						}
						else
						{
							elevatorState = 4;
							aimedElevatorState = 3;
						}
					}
					else if(scale)
					{
						if(ElevatorLevel.reachedStop())
						{
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
							aimedElevatorState = 4;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= 0 && ElevatorLevel.elevatorEncoderValue < Constants.pickUp)
						{
							elevatorState = 1;
							aimedElevatorState = 4;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.pickUp && ElevatorLevel.elevatorEncoderValue < Constants.sWitch)
						{
							elevatorState = 2;
							aimedElevatorState = 4;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.sWitch && ElevatorLevel.elevatorEncoderValue < Constants.scale)
						{
							elevatorState = 3;
							aimedElevatorState = 4;
						}
						else
						{
							elevatorState = 4;
							aimedElevatorState = 4;
						}
					}
					else
					{
						elevatorState = -1;
						aimedElevatorState = -1;
					}
				}
				else
				{
					moveEleVader(overrideValue);
				}
				break;
		}
	}
	
	
	
	
	
	public static void runElevator()
	{
		switch(elevatorState)
		{
			case 0:
				if(ElevatorLevel.reachedStop())
				{
					ElevatorLevel.resetElevatorEncoders();
					stopEleVader();
					elevatorState = 1;
					aimedElevatorState = 1;
				}
				else
				{
					moveEleVader(-.3);
				}
				break;
			case 1:
				if(manualOverride)
				{
					aimedElevatorState = -1;
				}
				else if(stop)
				{
					originalPositionButton = true;
					aimedElevatorState = 1;
				}
				else if(pickUp)
				{
					aimedElevatorState = 2;
				}
				else if(sWitch)
				{
					aimedElevatorState = 3;
				}
				else if(scale)
				{
					aimedElevatorState = 4;
				}
				switch(aimedElevatorState)
				{
					case 1:
						if(originalPositionButton)
						{
							if(ElevatorLevel.reachedStop())
							{
								ElevatorLevel.resetElevatorEncoders();
								stopEleVader();
								originalPositionButton = false;
							}
							else
							{
								moveEleVader(Functions.pickUpToStop(ElevatorLevel.elevatorEncoderValue));
					//			moveEleVader(-.2);
							}
						}
						else
						{
							stopEleVader();
						}
						break;
					case 2:
						if(ElevatorLevel.reachedPickUp())
						{
							stopEleVader();
							elevatorState = 2;
						}
						else
						{
							moveEleVader(Functions.stopToPickUp(ElevatorLevel.elevatorEncoderValue));
							//moveEleVader(.2);
						}
						break;
					case 3:
						if(ElevatorLevel.reachedSwitch())
						{
							stopEleVader();
							elevatorState = 3;
						}
						else
						{
							moveEleVader(Functions.stopToSwitch(ElevatorLevel.elevatorEncoderValue));
							//moveEleVader(-.2);
						}
						break;
					case 4:
						if(ElevatorLevel.reachedScale())
						{
							stopEleVader();
							elevatorState = 4;
						}
						else
						{
							moveEleVader(Functions.stopToScale(ElevatorLevel.elevatorEncoderValue));
							//moveEleVader(.35);
						}
						break;
					case -1:
						elevatorState = -1;
						break;
				}
				break;
			case 2:
				if(manualOverride)
				{
					aimedElevatorState = -1;
				}
				else if(stop)
				{
					aimedElevatorState = 1;
				}
				else if(pickUp)
				{
					originalPositionButton = true;
					aimedElevatorState = 2;
				}
				else if(sWitch)
				{
					aimedElevatorState = 3;
				}
				else if(scale)
				{
					aimedElevatorState = 4;
				}
				switch(aimedElevatorState)
				{
					case 1:						
						if(ElevatorLevel.reachedStop())
						{
							ElevatorLevel.resetElevatorEncoders();
							stopEleVader();
							elevatorState = 1;
						}
						else
						{
							moveEleVader(Functions.pickUpToStop(ElevatorLevel.elevatorEncoderValue));
			//				moveEleVader(-.2);
						}
						break;
					case 2:
						if(originalPositionButton)
						{
							if(ElevatorLevel.reachedPickUp())
							{
								originalPositionButton = false;
							}
							else
							{ 
								if(ElevatorLevel.elevatorEncoderValue > Constants.pickUp)
								{
									moveEleVader(Functions.switchToPickUp(ElevatorLevel.elevatorEncoderValue));
									//moveEleVader(-.2);
								}
								else
								{
									moveEleVader(Functions.stopToPickUp(ElevatorLevel.elevatorEncoderValue));
									//moveEleVader(.2);
								}
								
							}
						}
						
						else
						{
							stopEleVader();
						}
						break;
					case 3:
						if(ElevatorLevel.reachedSwitch())
						{
							stopEleVader();
							elevatorState = 3;
						}
						else
						{
							moveEleVader(Functions.pickUpToSwitch(ElevatorLevel.elevatorEncoderValue));
							//moveEleVader(.27);
						}
						break;
					case 4:
						if(ElevatorLevel.reachedScale())
						{
							stopEleVader();
							elevatorState = 4;
						}
						else
						{
							moveEleVader(Functions.pickUpToScale(ElevatorLevel.elevatorEncoderValue));
							//moveEleVader(.37);
						}
						break;
					case -1:
						elevatorState = -1;
						break;
				}
				break;
			case 3:
				if(manualOverride)
				{
					aimedElevatorState = -1;
				}
				else if(stop)
				{
					aimedElevatorState = 1;
				}
				else if(pickUp)
				{
					aimedElevatorState = 2;
				}
				else if(sWitch)
				{
					aimedElevatorState = 3;
				}
				else if(scale)
				{
					aimedElevatorState = 4;
				}
				switch(aimedElevatorState)
				{
					case 1:
						if(ElevatorLevel.reachedStop())
						{
							stopEleVader();
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
						}
						else
						{
							moveEleVader(Functions.switchToStop(ElevatorLevel.elevatorEncoderValue));
						//	moveEleVader(-.2);
						}
						break;
					case 2:
						if(ElevatorLevel.reachedPickUp())
						{
							stopEleVader();
							elevatorState = 2;
						}
						else
						{
							moveEleVader(Functions.switchToPickUp(ElevatorLevel.elevatorEncoderValue));
							//moveEleVader(-.2);
						}
						break;
					case 3:
						if(originalPositionButton)
						{
							if(ElevatorLevel.reachedSwitch())
							{
								originalPositionButton = false;
							}
							else
							{ 
								if(ElevatorLevel.elevatorEncoderValue > Constants.sWitch)
								{
									moveEleVader(Functions.scaleToSwitch(ElevatorLevel.elevatorEncoderValue));
									//moveEleVader(-.2);
								}
								else
								{
									moveEleVader(Functions.pickUpToSwitch(ElevatorLevel.elevatorEncoderValue));
									//moveEleVader(.25);
								}
								
							}
						}
						else
						{
							stopEleVader();
						}
						break;
					case 4:
						if(ElevatorLevel.reachedScale())
						{
							stopEleVader();
							elevatorState = 4;
						}
						else
						{
							moveEleVader(Functions.switchToScale(ElevatorLevel.elevatorEncoderValue));
							//moveEleVader(.34);
						}
						break;
					case -1:
						elevatorState = -1;
						break;
				}
				break;
			case 4:
				if(manualOverride)
				{
					aimedElevatorState = -1;
				}
				else if(stop)
				{
					aimedElevatorState = 1;
				}
				else if(pickUp)
				{
					aimedElevatorState = 2;
				}
				else if(sWitch)
				{
					aimedElevatorState = 3;
				}
				else if(scale)
				{
					aimedElevatorState = 4;
				}
				switch(aimedElevatorState)
				{
					case 1:
						if(ElevatorLevel.reachedStop())
						{
							stopEleVader();
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
						}
						else
						{
							moveEleVader(Functions.scaleToStop(ElevatorLevel.elevatorEncoderValue));
							//moveEleVader(-.2);
						}
						break;
					case 2:
						if(ElevatorLevel.reachedPickUp())
						{
							stopEleVader();
							elevatorState = 2;
						}
						else
						{
							moveEleVader(Functions.scaleToPickUp(ElevatorLevel.elevatorEncoderValue));
				//			moveEleVader(-.2);
						}
						break;
					case 3:
						if(ElevatorLevel.reachedSwitch())
						{
							stopEleVader();
							elevatorState = 3;
						}
						else
						{
							moveEleVader(Functions.scaleToSwitch(ElevatorLevel.elevatorEncoderValue));
//							moveEleVader(-.2);
						}
						break;
					case 4:
						if(originalPositionButton)
						{
							if(ElevatorLevel.reachedScale())
							{
								originalPositionButton = false;
							}
							else
							{
								moveEleVader(Functions.scaleToStop(ElevatorLevel.elevatorEncoderValue));
								//moveEleVader(-.2);
							}
						}
						else
						{
							stopEleVader();
						}
						break;
					case -1:
						elevatorState = -1;
						break;
				}
				break;
			case -1:
				if(stop || pickUp || sWitch || scale)
				{
					if(stop)
					{
						if(ElevatorLevel.reachedStop())
						{
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
							aimedElevatorState = 1;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= 0 && ElevatorLevel.elevatorEncoderValue < Constants.pickUp)
						{
							elevatorState = 1;
							aimedElevatorState = 1;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.pickUp && ElevatorLevel.elevatorEncoderValue < Constants.sWitch)
						{
							elevatorState = 2;
							aimedElevatorState = 1;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.sWitch && ElevatorLevel.elevatorEncoderValue < Constants.scale)
						{
							elevatorState = 3;
							aimedElevatorState = 1;
						}
						else
						{
							elevatorState = 4;
							aimedElevatorState = 1;
						}	
					}
					else if(pickUp)
					{
						if(ElevatorLevel.reachedStop())
						{
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
							aimedElevatorState = 2;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= 0 && ElevatorLevel.elevatorEncoderValue < Constants.pickUp)
						{
							elevatorState = 1;
							aimedElevatorState = 2;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.pickUp && ElevatorLevel.elevatorEncoderValue < Constants.sWitch)
						{
							elevatorState = 2;
							aimedElevatorState = 2;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.sWitch && ElevatorLevel.elevatorEncoderValue < Constants.scale)
						{
							elevatorState = 3;
							aimedElevatorState = 2;
						}
						else
						{
							elevatorState = 4;
							aimedElevatorState = 2;
						}
					}
					else if(sWitch)
					{
						if(ElevatorLevel.reachedStop())
						{
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
							aimedElevatorState = 3;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= 0 && ElevatorLevel.elevatorEncoderValue < Constants.pickUp)
						{
							elevatorState = 1;
							aimedElevatorState = 3;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.pickUp && ElevatorLevel.elevatorEncoderValue < Constants.sWitch)
						{
							elevatorState = 2;
							aimedElevatorState = 3;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.sWitch && ElevatorLevel.elevatorEncoderValue < Constants.scale)
						{
							elevatorState = 3;
							aimedElevatorState = 3;
						}
						else
						{
							elevatorState = 4;
							aimedElevatorState = 3;
						}
					}
					else if(scale)
					{
						if(ElevatorLevel.reachedStop())
						{
							ElevatorLevel.resetElevatorEncoders();
							elevatorState = 1;
							aimedElevatorState = 4;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= 0 && ElevatorLevel.elevatorEncoderValue < Constants.pickUp)
						{
							elevatorState = 1;
							aimedElevatorState = 4;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.pickUp && ElevatorLevel.elevatorEncoderValue < Constants.sWitch)
						{
							elevatorState = 2;
							aimedElevatorState = 4;
						}
						else if(ElevatorLevel.elevatorEncoderValue >= Constants.sWitch && ElevatorLevel.elevatorEncoderValue < Constants.scale)
						{
							elevatorState = 3;
							aimedElevatorState = 4;
						}
						else
						{
							elevatorState = 4;
							aimedElevatorState = 4;
						}
					}
					else
					{
						elevatorState = -1;
						aimedElevatorState = -1;
					}
				}
				else
				{
					moveEleVader(overrideValue);
				}
				break;
		}
	}
}
