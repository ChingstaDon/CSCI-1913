//CSCI 1913 Lab 7
//Donald Huynh

class Map<Key, Value>
{
	private Key[] keys;
	private Value[] values;

	public Map(int length)
	{
		if(length < 0)
		{
			throw new RuntimeException();
		}
		else
		{
			Map[] map = (Map[]) new Object[length];
		}
	}

	public Value get(Key key)
	{
		for(int i = 0; i < keys.length; i++)
		{
			if(isEqual(key, keys[i]))
			{
				return values[i];
			}
		}
		throw new IllegalArgumentException();
	}

	private boolean isEqual(Key leftKey, Key rightKey)
	{
		if(leftKey == null)
		{
			if(rightKey == null)
			{
				return true;
			}
		}
		return leftKey.equals(rightKey);
	}

	public boolean isIn(Key key)
	{
		for(int i = 0; i < keys.length; i++)
		{
			if(isEqual(key, keys[i]))
			{
				return true;
			}
		}
		return false;
	}

	public void put(Key key, Value value)
	{
		if(where(key) >= 0)
		{
			values[where(key)] = value;
		}
		else
		{
			throw new IllegalStateException();
		}
	}

	private int where(Key key)
	{
		for(int i = 0; i < keys.length; i++)
		{
			if(isEqual(key, keys[i]))
			{
				return i;
			}
		}
		return -1;
	}
}
class Hogwarts
{

//  MAIN. Make an instance of MAP and test it.

  public static void main(String [] args)
  {
    Map<String, String> map;

    try
    {
      map = new Map<String, String>(-5);
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No negatives");       //  No negatives  2 points.
    }

    map = new Map<String, String>(5);

    map.put("Harry",     "Ginny");
    map.put("Ron",       "Lavender");
    map.put("Voldemort", null);
    map.put(null,        "Wormtail");

    System.out.println(map.isIn("Harry"));      //  true          2 points.
    System.out.println(map.isIn("Ginny"));      //  false         2 points.
    System.out.println(map.isIn("Ron"));        //  true          2 points.
    System.out.println(map.isIn("Voldemort"));  //  true          2 points.
    System.out.println(map.isIn(null));         //  true          2 points.
    System.out.println(map.isIn("Joanne"));     //  false         2 points.

    System.out.println(map.get("Harry"));       //  Ginny         2 points.
    System.out.println(map.get("Ron"));         //  Lavender      2 points.
    System.out.println(map.get("Voldemort"));   //  null          2 points.
    System.out.println(map.get(null));          //  Wormtail      2 points.

    try
    {
      System.out.println(map.get("Joanne"));
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No Joanne");          //  No Joanne     2 points.
    }

    map.put("Ron",   "Hermione");
    map.put("Albus", "Gellert");
    map.put(null,    null);

    System.out.println(map.isIn(null));         //  true          2 points.
    System.out.println(map.isIn("Albus"));      //  true          2 points.

    System.out.println(map.get("Albus"));       //  Gellert       2 points.
    System.out.println(map.get("Harry"));       //  Ginny         2 points.
    System.out.println(map.get("Ron"));         //  Hermione      2 points.
    System.out.println(map.get("Voldemort"));   //  null          2 points.
    System.out.println(map.get(null));          //  null          2 points.

    try
    {
      map.put("Draco", "Pansy"); 
    }
    catch (IllegalStateException minnesota)
    {
      System.out.println("No Draco");           //  No Draco      2 points.
    }
  }
}
