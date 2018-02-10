package team3647elevator;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import team3647ConstantsAndFunctions.Constants;
import team3647ConstantsAndFunctions.Functions;

public class Elevator 
{
	public static Integer elevatorState;
	/*
	 * 0. Start
	 * 1. Stop
	 * 2. PickUp
	 * 3. Switch
	 * 4. Scale
	 */
	static Integer aimedElevatorState;
	
	static boolean stop, pickUp, sWitch, scale, moving, manualOverride, originalPositionButton;
	static double overrideValue;
	
	public static WPI_TalonSRX leftElevator = new WPI_TalonSRX();
	public static WPI_TalonSRX rightElevator = new WPI_TalonSRX();
	
	public static VictorSPX leftElevatorSPX = new VictorSPX();
	public static VictorSPX rightElevatorSPX = new VictorSPX();
	
	public static DifferentialDrive elevatorDrive = new DifferentialDrive(leftElevator, rightElevator);
	
	public static void moveEleVader(double speed)
	{
		elevatorDrive.tankDrive(speed, speed, false);
	}
	
	public static void stopEleVader()
	{
		elevatorDrive.tankDrive(0, 0, false);
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
		if(jValue == 0)
		{
			manualOverride = false;
		}
		else
		{
			overrideValue = jValue;
			manualOverride = true;
		}
	}
	
	public static void runElevator()
	{
		switch(elevatorState)
		{
			case 0:
				if(ElevatorLevel.reachedStop())
				{
					elevatorState = 1;
					aimedElevatorState = 1;
				}
				// else if banner aint workin: override
				else
				{
					moveEleVader(-.2);
				}
				break;
			case 1:
				if(manualOverride)
				{
					aimedElevatorState = null;
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
								originalPositionButton = false;
							}
							else
							{
								moveEleVader(Functions.pickUpToStop(ElevatorLevel.elevatorEncoderValue));
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
						}
						break;
					case null:
						elevatorState = null;
						break;
				}
				break;
			case 2:
				if(manualOverride)
				{
					aimedElevatorState = null;
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
							stopEleVader();
							elevatorState = 1;
						}
						else
						{
							moveEleVader(Functions.pickUpToStop(ElevatorLevel.elevatorEncoderValue));
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
								moveEleVader(Functions.pickUpToStop(ElevatorLevel.elevatorEncoderValue));
							}
						}
						else
						{
							stopEleVader();
						}
						break;
					case 3:
						if(!manualOverride)
						{
							if(ElevatorLevel.reachedSwitch())
							{
								stopEleVader();
								elevatorState = 3;
							}
							else
							{
								moveEleVader(Functions.pickUpToSwitch(ElevatorLevel.elevatorEncoderValue));
							}
						}
						else
						{
							if(ElevatorLevel.reachedSwitch())
							{
								elevatorState = 3;
							}
							else
							{
								moveEleVader(overrideValue);
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
							moveEleVader(Functions.pickUpToScale(ElevatorLevel.elevatorEncoderValue));
						}
						break;
				}
				break;
			case 3:
				if(stop)
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
				else if(manualOverride)
				{
					if(overrideValue < 0)
					{
						aimedElevatorState = 2;
					}
					else
					{
						aimedElevatorState = 4;
					}
				}
				else
				{
					aimedElevatorState = 3;
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
							moveEleVader(Functions.switchToStop(ElevatorLevel.elevatorEncoderValue));
						}
						break;
					case 2:
						if(!manualOverride)
						{
							if(ElevatorLevel.reachedPickUp())
							{
								stopEleVader();
								elevatorState = 2;
							}
							else
							{
								moveEleVader(Functions.switchToPickUp(ElevatorLevel.elevatorEncoderValue));
							}
						}
						else
						{
							if(ElevatorLevel.reachedPickUp())
							{
								elevatorState = 2;
							}
							else
							{
								moveEleVader(overrideValue);
							}
						}
						break;
					case 3:
						stopEleVader();
						break;
					case 4:
						if(!manualOverride)
						{
							if(ElevatorLevel.reachedScale())
							{
								stopEleVader();
								elevatorState = 4;
							}
							else
							{
								moveEleVader(Functions.switchToScale(ElevatorLevel.elevatorEncoderValue));
							}
						}
						else
						{
							
						}
						break;
				}
				break;
			case 4:
				if(stop)
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
							elevatorState = 1;
						}
						else
						{
							moveEleVader(Functions.scaleToStop(ElevatorLevel.elevatorEncoderValue));
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
						}
						break;
					case 4:
						stopEleVader();
						break;
				}
				break;
			case null:
				if(stop || pickUp || sWitch || scale)
				{
					if(stop)
					{
						if(ElevatorLevel.reachedStop())
						{
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
						elevatorState = null;
						aimedElevatorState = null;
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
