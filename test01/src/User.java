public class User
{
	private String fName, lName, avatar;
	private int ID;
  
	public User()
	{
		fName = "";
		lName = "";
		avatar = "";
		ID = 0;
	}
  
	public User(String fn, String ln)
	{ 
		fName = fn;
		lName = ln;
		avatar = "Undefined";
		ID = (int)(Math.random() * 100000) + 1;
	}
  
	public User(String fn, String ln, String avr)
	{
		fName = fn;
		lName = ln;
		avatar = avr;
		ID = (int)(Math.random() * 100000) + 1;
	}
  
	public void setAvatar(String avr)
	{
		avatar = avr;
	}
  
	public String getFirstName()
	{
		return fName;
	}

	public String getLastName()
	{
		return lName;
	}
  
	public String getAvatar()
	{
		return avatar;
	}

	public int getID()
	{
		return ID;
	}

	public String toString()
	{	
		return "User info: \n" + "First Name: " + getFirstName() + "\nLast Name" +
				getLastName() + "\nAvatar:" + getAvatar() + "\nID: " + 
				Integer.toString(getID());
	}

}








