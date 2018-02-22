import java.util.Scanner;

public class InventoryRunner
{
	static Scanner dS = new Scanner(System.in);
  
	static Inventory item1;
	
	public static void main(String[]args)
	{
		String manufacturer, name, category;
		int price;
		
		System.out.println("Will you be entering category and price? Y or N");
		String condition = dS.next();

		if(condition.equals("Y"))
		{
			System.out.println("Please enter the manufactuerer");
			manufacturer = dS.next();
			System.out.println("Please enter the name");
			name = dS.next();
			System.out.println("Please enter the category");
			category = dS.next();
			System.out.println("Please enter the price");
			price = dS.nextInt();
			item1 = new Inventory(manufacturer, name, category, price);
		}
		else
		{
			System.out.println("Please enter the manufactuerer");
			manufacturer= dS.next();
			System.out.println("Please enter the name");
			name = dS.next();
			item1 = new Inventory(manufacturer, name);
		}
    
		System.out.println(item1);
	}
}







