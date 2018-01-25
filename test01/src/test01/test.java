package test01;

public class test 
{
	public static void main(String[] args)
	{
		int step = 1;
		int distance = 0;
		int requiredStraightDist = 1000;
		int curveDist = 1000;
		while(true)
		{
			switch(step)
			{
				case 1: //going straight
					if(distance <=- requiredStraightDist) //we have not reached the distance yet
					{
						distance++;
					}
					else // we have reached the straight distance
					{
						System.out.println("We have reached the straight distance.");
						distance = 0;
						step = 2;	
					}
					break;
				case 2: //start curving
					if(distance < curveDist)//still curving
					{
						distance++;
					}
					else //done curving
					{
						System.out.println("We have reached the curve distance.");
						distance = 0;
						step = 3;	
					}
					break;
				case 3:
					//just stop
					break;
			}
		}
	}

}
