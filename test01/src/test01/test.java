package test01;

public class test 
{
	static String step = "start";
	public static void main(String[] args)
	{
		while(true)
		{
			switch(step)
			{
				case "start":
					System.out.println("Robot initiaization");
					step = "stop";
					break;
				case "stop":
					System.out.println("Robot stop");
					step = "done";
					break;
				case "done":
					//System.out.println();
					break;
			}
		}
	}

}
