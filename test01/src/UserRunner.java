import java.util.Scanner;

public class UserRunner
{
	static Scanner dS = new Scanner(System.in);
    
	static User user1;
  
	public static void main(String[]args)
	{
		String firstName, lastName, condition;
		System.out.println("First Name: ");
		firstName = dS.next();
		System.out.println("Last name: ");
		lastName = dS.next();
		System.out.println("Do you want to have an avatar? Y or N");
		condition = dS.next();
    
		if(condition.equals("Y"))
		{
			System.out.println("Please enter your avatar");
			String avatar = dS.next();
			user1 = new User(firstName, lastName, avatar);
		}	
		else
		{
			user1 = new User(firstName, lastName);
		}
    
		System.out.println(user1);
	}
}