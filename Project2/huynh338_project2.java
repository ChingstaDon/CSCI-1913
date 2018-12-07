//CSCI 1913 Project 2
//Donald Huynh
class Poly
{
  private class Term
  {
    private int coef;
    private int expo;
    private Term next;
    private Term(int coef, int expo, Term next)
    {
      this.coef = coef;
      this.expo = expo;
      this.next = next;
    }
  }
  private Term first;
  private Term last;
  private Term head;
  public Poly()
  {
    head = new Term(1,Integer.MAX_VALUE,null);
    first = head;
    last = head;
  }

  public boolean isZero()
  {
    return head.next == null;
  }

  public Poly plus(int coef, int expo)
  {
    if(coef == 0 || expo < 0 || expo >= last.expo)
    {
      throw new IllegalArgumentException();
    }
    Term temp = new Term(coef, expo, null);
    last.next = temp;
    last = temp;
    return this;
  }

  public Poly minus()
  {
    Poly result = new Poly();
    Term temp = first.next;
    while(temp != null)
    {
      result.plus(-temp.coef, temp.expo);
      temp = temp.next;
    }
    return result;
  }

  public Poly plus(Poly that)
  {
    Poly result = new Poly();
    Term left = this.first.next;
    Term right = that.first.next;
    while(left != null && right != null)
    {
      if(left.expo > right.expo)
      {
        result.plus(left.coef, left.expo);
        left = left.next;
      }
      else if(right.expo > left.expo)
      {
        result.plus(right.coef, right.expo);
        right = right.next;
      }
      else
      {
        int sum = left.coef + right.coef;
        if(sum != 0)
        {
          result.plus(sum, left.expo);
        }
        left = left.next;
        right = right.next;
      }
    }

    if(left != null)
    {
      result.last.next = left;
    }
    else
    {
      result.last.next = right;
    }
    return result;
  }

  public String toString()
  {
    StringBuilder builder = new StringBuilder();
    Term temp = head.next;
    if(temp == null)
    {
      builder.append('0');
    }
    else
    {
      Integer c = new Integer(temp.coef);
      Integer e = new Integer(temp.expo);
      builder.append(c.toString() + "x");
      appendExpo(builder, e);
      if(temp.next != null)
      {
        builder.append((temp.coef < 0) ? " - " : " + ");
      }
      temp = temp.next;
    }
    while(temp != null)
    {
      Integer c = new Integer((temp.coef < 0) ? -temp.coef : temp.coef);
      Integer e = new Integer(temp.expo);
      builder.append(c.toString() + "x");
      appendExpo(builder,e);
      if(temp.next != null)
      {
        builder.append((temp.coef < 0) ? " - " : " + ");
      }
      temp = temp.next;
    }

    return builder.toString();
  }

  private void appendExpo(StringBuilder builder, int expo)
  {
    if (expo == 0)
    {
      builder.append('⁰');
    }
    else
    {
      appendingExpo(builder, expo);
    }
  }

  private void appendingExpo(StringBuilder builder, int expo)
  {
    if (expo > 0)
    {
      appendingExpo(builder, expo / 10);
      builder.append("⁰¹²³⁴⁵⁶⁷⁸⁹".charAt(expo % 10));
    }
  } 
}


class PollyEsther
{
  public static void main(String[] args)
  {
    Poly p = new Poly().plus(3,5).plus(2,4).plus(2,3).plus(-1,2).plus(5,0);
    Poly q = new Poly().plus(7,4).plus(1,2).plus(-4,1).plus(-3,0);
    Poly z = new Poly();

    System.out.println(p);                 // 3x⁵ + 2x⁴ + 2x³ - 1x² + 5x⁰
    System.out.println(q);                 // 7x⁴ + 1x² - 4x¹ - 3x⁰
    System.out.println(z);                 // 0

    System.out.println(p.minus());         // -3x⁵ - 2x⁴ - 2x³ + 1x² - 5x⁰
    System.out.println(q.minus());         // -7x⁴ - 1x² + 4x¹ + 3x⁰
    System.out.println(z.minus());         // 0

    System.out.println(p.plus(q));         // 3x⁵ + 9x⁴ + 2x³ - 4x¹ + 2x⁰
    System.out.println(p.plus(z));         // 3x⁵ + 2x⁴ + 2x³ - 1x² + 5x⁰
    System.out.println(p.plus(q.minus())); // 3x⁵ - 5x⁴ + 2x³ - 2x² + 4x¹ + 8x⁰

  }
}
