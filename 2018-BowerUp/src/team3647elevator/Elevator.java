package team3647elevator;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import team3647ConstantsAndFunctions.Functions;

public class Elevator 
{
	static int elevatorState = 0;
	/*
	 * 0. Start
	 * 1. Stop
	 * 2. PickUp
	 * 3. Switch
	 * 4. Scale
	 */
	static int aimedElevatorState;
	
	static boolean stop, pickUp, sWitch, scale, moving;
	
	
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
	
	public static void runElevator(double speed)
	{
		switch(elevatorState)
		{
			case 0:
				if(ElevatorLevel.reachedStop())
				{
					elevatorState = 1;
					aimedElevatorState = 1;
				}
				else
				{
					moveEleVader(-.2);
				}
				break;
			case 1:
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
				}
				break;
			case 2:
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
							moveEleVader(Functions.pickUpToStop(ElevatorLevel.elevatorEncoderValue));
						}
						break;
					case 2:
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
						if(ElevatorLevel.reachedPickUp())
						{
							stopEleVader();
							elevatorState = 2;
						}
						else
						{
							moveEleVader(Functions.switchToPickUp(ElevatorLevel.elevatorEncoderValue));
						}
						break;
					case 3:
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
						break;
				}
				break;
		}
	}

}
