public class Inventory
{
	private String manufacturer, name, category;
	private int UPC, price;
  
	public Inventory()
	{
		manufacturer = "";
		name = "";
		category = "";
		UPC = 0;
		price = 0;
	}	
  
	public Inventory(String m, String n)
	{
		manufacturer = m;
		name = n;
		category = "Undefined";
		UPC = (int)(Math.random() * 999999999) + 1;
		price = 0;
	}
	
	public Inventory(String m, String n, String c, int p)
	{
		manufacturer = m;
		name = n;
		category = c;
		UPC = (int)(Math.random() * 999999999) + 1;
		price = p;
	}
	
	public String getManufacturer()
  	{
		return manufacturer;
  	}
  
	public String getName()
	{
		return name;
	}
  
	public String getCategory()
	{
		return category;
	}
  
	public int getUPC()
	{
		return UPC;
	}
  
	public int getPrice()
	{
		return price;
	}
  
	public String toString()
	{
		return "Item info: \n" + "Manufacturer: " + getManufacturer() + "\nName: " +
				getName() + "\nCategory: " + getCategory() + "UPC: " + Integer.toString(getUPC()) +
				"\nprice: " + Integer.toString(getPrice());
	}
}













