//CSCI 1913 Lab 7
//Donald Huynh

class Map<Key, Value>
{
	private Key[] keys;
	private Value[] values;
	private int count = 0;
	
	public Map(int length)
	{
		if(length < 0)
		{
			throw new IllegalArgumentException();
		}
		else
		{
			keys = (Key[]) new Object[length];
			values = (Value[]) new Object[length];
		}
	}

	public Value get(Key key)
	{
		for(int i = 0; i < count; i++)
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
			else
			{
				return false;
			}
		}
		
		return leftKey.equals(rightKey);
		
	}

	public boolean isIn(Key key)
	{
		for(int i = 0; i < count; i++)
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
		if(isIn(key))
		{
			values[where(key)] = value;
		}
		else
		{
			if(count < keys.length)
			{
				keys[count] = key;
				values[count] = value;
				count ++;
			}
			else
			{
				throw new IllegalStateException();
			}
		}	
	}

	private int where(Key key)
	{
		for(int i = 0; i < count; i++)
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
