package team3647elevator;

import edu.wpi.first.wpilibj.DigitalInput;
import team3647ConstantsAndFunctions.Constants;
import team3647subsystems.Drivetrain;

public class ElevatorLevel 
{
	public static double elevatorEncoderValue;
	
	public static DigitalInput bannerSensor = new DigitalInput(0); 
	
	public void setElevatorEncoder()
	{
		if(reachedStop())
		{
			resetElevatorEncoders();
		}
		elevatorEncoderValue = Elevator.leftElevator.getSensorCollection().getQuadraturePosition();
	}
	
	public static void resetElevatorEncoders()
	{
		Elevator.leftElevator.getSensorCollection().setQuadraturePosition(0, 10);
	}
	
	public static void testElevatorEncoders()
	{
		System.out.println("Elevator Encoder Value: " + elevatorEncoderValue);
	}
	
	public static boolean reachedStop()
	{
		if(bannerSensor.get())
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static boolean reachedPickUp()
	{
		if(elevatorEncoderValue > Constants.pickUp - 700 && elevatorEncoderValue < Constants.pickUp + 700)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static boolean reachedSwitch()
	{
		if(elevatorEncoderValue > Constants.sWitch - 700 && elevatorEncoderValue < Constants.sWitch + 700)
		{
			return true;
		}
		else
		{
			return false; 
		}
	}
	
	public static boolean reachedScale()
	{
		if(elevatorEncoderValue > Constants.scale - 700 && elevatorEncoderValue < Constants.scale + 700)
		{
			return true;
		}
		else
		{
			return false; 
		}
	}
	

}
