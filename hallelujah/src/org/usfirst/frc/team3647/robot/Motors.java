package org.usfirst.frc.team3647.robot;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Motors 
{
	public static Spark leftMotor = new Spark(2);
	public static Spark rightMotor = new Spark(1);
	
	public static DifferentialDrive drive = new DifferentialDrive(leftMotor, rightMotor);
	
	public static void driveForward(double leftEnc, double rightEnc, double speed)
	{
		if(Math.abs(leftEnc - rightEnc) < 10)
		{
			drive.tankDrive(speed, speed, false);
		}
		else if(Math.abs(leftEnc - rightEnc) < 20)
		{
			if(leftEnc > rightEnc)
		 	{
				drive.tankDrive(speed - .075, speed, false);
		 	}
			else
		 	{
		 		drive.tankDrive(speed, speed - .075, false);
		 	}
		 }
		 else if(Math.abs(leftEnc - rightEnc) < 30)
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed - .15, speed, false);
			 }
			 else
			 {
				 drive.tankDrive(speed, speed - .15, false);
			 }
		 }
		 else if(Math.abs(leftEnc - rightEnc) < 50)
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed - .22, speed, false);
			 }
			 else
			 {
				 drive.tankDrive(speed, speed - .22, false);
			 }
		 }
		 else
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed - .3, speed, false);
			 }
			 else
			 {
			 	drive.tankDrive(speed, speed - .3, false);
			 }
		 }
	}
	
	public static void driveBackward(double leftEnc, double rightEnc, double speed)
	{
		if(Math.abs(leftEnc - rightEnc) < 10)
		{
			drive.tankDrive(speed, speed, false);
		}
		else if(Math.abs(leftEnc - rightEnc) < 20)
		{
			if(leftEnc > rightEnc)
		 	{
				drive.tankDrive(speed + .075, speed, false);
		 	}
			else
		 	{
		 		drive.tankDrive(speed, speed + .075, false);
		 	}
		 }
		else if(Math.abs(leftEnc - rightEnc) < 30)
		{
			if(leftEnc > rightEnc)
			{
				drive.tankDrive(speed + .14, speed, false);
			}
			else
			{
				drive.tankDrive(speed, speed + .14, false);
			}
		 }
		else if(Math.abs(leftEnc - rightEnc) < 50)
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed +.22, speed, false);
			 }
			 else
			 {
				 drive.tankDrive(speed, speed + .22, false);
			 }
		 }
		 else
		 {
			 if(leftEnc > rightEnc)
			 {
				 drive.tankDrive(speed + .3, speed, false);
			 }
			 else
			 {
			 	drive.tankDrive(speed, speed + .3, false);
			 }
		 }
	}
	public static double  avg;
	public static boolean reachedDistance(double leftEnc, double rightEnc, double distance)
	{
		avg = Math.abs(leftEnc) + Math.abs(rightEnc);
		avg/=2;
		if(avg<distance)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
}
