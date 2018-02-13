import java.util.Scanner;

public class GameGun
{
  static int bulletCount; 
  static int clipSize = 16;
  static int shotCount;
  static String[] clips;
  static Scanner dS = new Scanner(System.in);
  
  public static void main(String[]args)
  {
    bulletCount = 96;
    shotCount = 0;
    clips = new String[clipSize];
   
    resetClip();
    while(bulletCount > 0 || shotCount > 0)
    {
      System.out.println("Action: "); //THIS IS WHERE YOU LEFT OFF 
      String instruction = dS.next();
      if(instruction.equals("R"))
      {
        reload();
      }
      else if(instruction.equals("S"))
      {
        shoot();
      }
      printClip();
    }
  }
  
  public static void resetClip()
  {
    for(int i = 0; i < clips.length; i++)
    {
      clips[i] = "[]";
    }
  }
  
  public static void shoot()
  {
    if(shotCount > 0)
    {
      clips[shotCount - 1] = "[]";
      shotCount--;
      System.out.println("BOOM!");
    }
    else
    {
      System.out.println("RELOAD!");
    }
  }
  
  public static void reload()
  {
    if(bulletCount >= clipSize)
    {
      bulletCount = bulletCount - clipSize; 
      shotCount = clipSize;
    }
    else
    {
      shotCount = bulletCount; 
      bulletCount = 0;
    }
    resetClip();
    for(int i = 0; i < shotCount; i++)
    {
      clips[i] = " * ";
    }
  }
  
  public static void printClip()
  {
    String output = " ";
    System.out.println("Bullets:" + bulletCount);
    System.out.print("Clip: ");
    for(int i = 0; i < clipSize; i++)
    {
      output = output + clips[i];
    }
    System.out.println(output);
  }
}