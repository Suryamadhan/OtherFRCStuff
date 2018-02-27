public abstract class toyInventory 
{
    private String name;
    private int count;
        
    public toyInventory() 
    {
      this("");
    }
    
    public toyInventory(String name) 
    {
      this.name = name;
      count = 1;
    }
    public String getName()
    {
      return name;
    }
    
    public int getCount()
    {
      return count;
    }
    
    public void setName(String name)
    {
      this.name = name;
    }
    
    public void setCount(int count)
    {
      this.count = count;
    }
    
    public abstract String getType();
    
    public String toString()
    {
      return "Name: " + this.getName() + "; Count " + this.getCount();
    }
}