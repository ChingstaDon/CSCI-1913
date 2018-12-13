//CSCI 1913 Project 3
//Donald Huynh
import java.util.Random;

class ShuffleTree<Value>
{
  private class Node
  {
    private String key;
    private Value value;
    private Node left;
    private Node right;

    private Node(String key, Value value)
    {
      this.key = key;
      this.value = value;
      this.left = null;
      this.right = null;
    }
  }

  private String[] keys;
  private Value[] values;
  private int count;
  private Node root;
  private Random generator;

  public boolean isEmpty()
  {
    return this.root == null;
  }
  public ShuffleTree(int size)
  {
    if(size < 0)
    {
      throw new IllegalArgumentException("Invalid size!");
    }
    keys = new String[size];
    values = (Value[])new Object[size];
    count = 0;
    root = null;
    generator = new Random();
  }

  private void flush()
  {
    //System.out.println("Entered flush()");
    for(int i = 0; i <= count - 2; i++)
    {
      //System.out.println("Randomizing");
      int j = Math.abs(generator.nextInt()) % (count - i);
      String tempK = keys[i];
      Value tempV = values[i];
      keys[i] = keys[i+j];
      keys[i+j] = tempK;
      values[i] = values[i+j];
      values[i+j] = tempV;
    }

    //Node temp = root;
    for(int i = 0; i < count; i++)
    {
      //System.out.println("Growing Tree");
      if(this.isEmpty())
      {
        //System.out.printf("Root, Assigning Key: %s to Value: %d\n", keys[i], values[i]);
        root = new Node(keys[i], values[i]);
      }
      else
      {
        //System.out.println("Leaves");
        Node temp = root;
        while(true)
        {
          //System.out.println(i);
          int test = keys[i].compareTo(temp.key);
          if(test < 0)
          {
            if(temp.left == null)
            {
              //System.out.printf("Left, Assigning Key: %s to Value: %d\n", keys[i], values[i]);
              temp.left = new Node(keys[i], values[i]);
              break;
            }
            else
            {
              //System.out.println("left, flush");
              temp = temp.left;
            }
          }
          else if(test > 0)
          {
            if(temp.right == null)
            {
              //System.out.printf("Right, Assigning Key: %s to Value: %d\n", keys[i], values[i]);
              temp.right = new Node(keys[i], values[i]);
              break;
            }
            else
            {
              //System.out.println("right, flush");
              temp = temp.right;
            }
          }
          else
          {
            throw new IllegalStateException("Key already exists!");
          }
        }
      }
      keys[i] = null;
      values[i] = null;
    }
  }

  public Value get(String key)
  {
    if(count != 0)
    {
      this.flush();
      count = 0;
    }
    Node subtree = root;
    while(subtree != null)
    {
      int test = key.compareTo(subtree.key);
      if(test < 0)
      {
        subtree = subtree.left;
      }
      else if(test > 0)
      {
        subtree = subtree.right;
      }
      else
      {
        return subtree.value;
      }
    }
    throw new IllegalArgumentException("No such key.");
  }

  public int height()
  {
    return heighting(root);
  }

  private int heighting(Node root)
  {
    if(root == null)
    {
      return 0;
    }
    else
    {
      int left = heighting(root.left);
      int right = heighting(root.right);
      if(left > right)
      {
        return left + 1;
      }
      else
      {
        return right + 1;
      }
    }
  }

  public void put(String key, Value value)
  {
    //System.out.println(count);
    if(key == null)
    {
      throw new IllegalArgumentException("Invalid Key!");
    }
    else if(count == keys.length)
    {
      this.flush();
      count = 0;
      keys[count] = key;
      //System.out.println(keys[count]);
      values[count] = value;
      //System.out.println(values[count]);
      count++;
    }
    else
    {
      keys[count] = key;
      values[count] = value;
      //System.out.println(keys[count]);
      //System.out.println(values[count]);
      count++;
    }
  }
}

class ShuffleBored
{
  private final static String[] reserved =
   { "abstract",     "assert",    "boolean",     "break",
     "byte",         "case",      "catch",       "char",
     "class",        "const",     "continue",    "default",
     "do",           "double",    "else",        "extends",
     "final",        "finally",   "float",       "for",
     "goto",         "if",        "implements",  "import",
     "instanceof",   "int",       "interface",   "long",
     "native",       "new",       "package",     "private",
     "protected",    "public",    "return",      "short",
     "static",       "super",     "switch",      "synchronized",
     "this",         "throw",     "throws",      "transient",
     "try",          "void",      "volatile",    "while" };

  public static void main(String[] args)
  {
    // ShuffleTree<Integer> tree = new ShuffleTree<Integer>(30);
    // tree.put("abstract", 0);
    // System.out.format("%02 %s", tree.get("abstract"), "abstract");
    ShuffleTree<Integer> tree = new ShuffleTree<Integer>(30);

    for (int index = 0; index < reserved.length; index += 1)
    {
      //System.out.printf("%s %02d\n", reserved[index], index);
      tree.put(reserved[index], index);
    }

    System.out.println(tree.height());

    for (int index = 0; index < reserved.length; index += 1)
    {
      System.out.format("%02d %s", tree.get(reserved[index]), reserved[index]);
      System.out.println();
    }

    try
    {
      System.out.println(tree.get("friend"));
    }
    catch(IllegalArgumentException ignore)
    {
      System.out.println("No friend here...");
    }

  }
}
