package Subsystems;

import edu.wpi.first.wpilibj.Spark;

public class Drive 
{
	Spark leftSpark;
	Spark rightSpark;
	
	public Drive()
	{
		leftSpark = new Spark(2);
		rightSpark = new Spark(1);
	}

}
