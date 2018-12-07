//CSCI 1913 Lab 12
//Donald Huynh
class FamilyTree
{
  private class Node
  {
    private String name;
    private Node father;
    private Node mother;
    private Node(String name, Node father, Node mother)
    {
      this.name = name;
      this.father = father;
      this.mother = mother;
    }
  }
  private Node root;
  public FamilyTree(String ego)
  {
    root = new Node(ego, null, null);
  }
  private Node find(String name)
  {
    return find(name, root);
  }
  private Node find(String name, Node root)
  {
    Node subtree = root;
    if(subtree != null)
    {
      if(name.equals(subtree.name))
      {
        return subtree;
      }
      if(subtree.father != null)
      {
        return find(name, subtree.father);
      }
      else if(subtree.mother != null)
      {
        return find(name, subtree.mother);
      }
    }
    else
    {
      return null;
    }
  }
  // private Node find(String name, Node root)
  // {
  //   Node subtree = root;
  //   if(subtree.father != null)
  //   {
  //     if(name.equals(subtree.name))
  //     {
  //       return subtree;
  //     }
  //     return find(name, subtree.father);
  //   }
  //   else if(subtree.mother != null)
  //   {
  //     if(name.equals(subtree.name))
  //     {
  //       return subtree;
  //     }
  //     return find(name, subtree.mother);
  //   }
  //   else
  //   {
  //     if(name.equals(subtree.name))
  //     {
  //       return subtree;
  //     }
  //     return null;
  //   }
  // }
  public void addParents(String ego, String father, String mother)
  {
    Node found = find(ego);
    if(found == null)
    {
      throw new IllegalArgumentException("ego not found.");
    }
    else
    {
      found.father = new Node(father, null, null);
      found.mother = new Node(mother, null, null);
    }
  }
  public boolean isDescendant(String ego, String ancestor)
  {
    Node self = find(ego);
    Node other = find(ancestor);
    if((self == null) || (other == null))
    {
      return false;
    }
    else
    {
      return isDescendant(self, other);
    }
  }
  public boolean isDescendant(Node root, Node ancestor)
  {
    if(root != ancestor)
    {
      if(root.father != null)
      {
        return isDescendant(root.father, ancestor);
      }
      else if(root.mother != null)
      {
        return isDescendant(root.mother, ancestor);
      }
      else
      {
        return false;
      }
    }
    else
    {
      return true;
    }
  }
}

//  POTTERY. Driver class.

class Pottery
{

//  MAIN. For testing. Each comment shows a point value (there's a total of 40
//  points) and what it should print.

  public static void main(String [] args)
  {
    FamilyTree family = new FamilyTree("Al");

    family.addParents("Al",    "Harry",  "Ginny");
    family.addParents("Harry", "James",  "Lily" );
    family.addParents("Ginny", "Arthur", "Molly");

    try
    {
      family.addParents("Joanne", "Peter", "Anne");
    }
    catch (IllegalArgumentException ignore)
    {
      System.out.println("No Joanne.");  //  2 No Joanne.
    }

    System.out.println(family.isDescendant("Joanne", "Joanne"));  //  2 false

    System.out.println(family.isDescendant("Al", "Al"));          //  2 true
    System.out.println(family.isDescendant("Al", "Harry"));       //  2 true
    System.out.println(family.isDescendant("Al", "Ginny"));       //  2 true
    System.out.println(family.isDescendant("Al", "James"));       //  2 true
    System.out.println(family.isDescendant("Al", "Lily"));        //  2 true
    System.out.println(family.isDescendant("Al", "Arthur"));      //  2 true
    System.out.println(family.isDescendant("Al", "Molly"));       //  2 true
    System.out.println(family.isDescendant("Al", "Joanne"));      //  2 false

    System.out.println(family.isDescendant("Harry", "Harry"));    //  2 true
    System.out.println(family.isDescendant("Harry", "Al"));       //  2 false
    System.out.println(family.isDescendant("Harry", "James"));    //  2 true
    System.out.println(family.isDescendant("Harry", "Lily"));     //  2 true
    System.out.println(family.isDescendant("Harry", "Ginny"));    //  2 false
    System.out.println(family.isDescendant("Harry", "Arthur"));   //  2 false
    System.out.println(family.isDescendant("Harry", "Molly"));    //  2 false
    System.out.println(family.isDescendant("Harry", "Joanne"));   //  2 false

    System.out.println(family.isDescendant("Ginny", "Arthur"));   //  2 true
    System.out.println(family.isDescendant("Arthur", "Ginny"));   //  2 false
  }
}
