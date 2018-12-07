//CSCI 1913 lab8
//Donald Huynh

class RunnyStack<Base>
{

	private int count;
	private int run;
	private class Run
	{
		private Base base;
		private Run next;
		private int length = 0;
		private Run(Base base, Run next)
		{
			this.base = base;
			this.next = next;
			length += 1;
		}
	}
	private Run top;

	public RunnyStack()
	{
		top = null;
		count = 0;
		run = 0;
	}

	public int depth()
	{
		return count;
	}
	public boolean isEmpty()
	{
		return top == null;
	}

	public Base peek()
	{
		if(isEmpty())
		{
			throw new IllegalStateException("Stack is empty");
		}
		else
		{
			return top.base;
		}
	}

	public void pop()
	{
		if(isEmpty())
		{
			throw new IllegalStateException("Stack is empty");
		}
		else
		{
			count -= 1;
			if(top.length > 1)
			{
				top.length -= 1;
			}
			else
			{
				run -= 1;
				top = top.next;
			}
		}
	}

	public void push(Base base)
	{
		count += 1;
		if(top != null && top.base == base)
		{
			top.length += 1;
		}
		else
		{
			run += 1;
			top = new Run(base, top);
		}
	}

	public int runs()
	{
		if(top == null)
		{
			return 0;
		}
		return run;
	}
}

class Camembert
{
  public static void main(String [] args)
  {
    RunnyStack<String> s = new RunnyStack<String>();

    System.out.println(s.isEmpty());         //  true       1 point
    System.out.println(s.depth());           //  0          1 point
    System.out.println(s.runs());            //  0          1 point

    try
    {
      s.pop();
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No pop");          //  No pop     1 point
    }

    try
    {
      System.out.println(s.peek());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No peek");         //  No peek    1 point
    }

    s.push("A");
    System.out.println(s.peek());            //  A          1 point
    System.out.println(s.depth());           //  1          1 point
    System.out.println(s.runs());            //  1          1 point

    System.out.println(s.isEmpty());         //  false      1 point

    s.push("B");
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  2          1 point
    System.out.println(s.runs());            //  2          1 point

    s.push("B");
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  3          1 point
    System.out.println(s.runs());            //  2          1 point

    s.push("B");
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  4          1 point
    System.out.println(s.runs());            //  2          1 point

    s.push("C");
    System.out.println(s.peek());            //  C          1 point
    System.out.println(s.depth());           //  5          1 point
    System.out.println(s.runs());            //  3          1 point

    s.push("C");
    System.out.println(s.peek());            //  C          1 point
    System.out.println(s.depth());           //  6          1 point
    System.out.println(s.runs());            //  3          1 point

    s.pop();
    System.out.println(s.peek());            //  C          1 point
    System.out.println(s.depth());           //  5          1 point
    System.out.println(s.runs());            //  3          1 point

    s.pop();
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  4          1 point
    System.out.println(s.runs());            //  2          1 point

    s.pop();
    System.out.println(s.peek());            //  B          1 point
    System.out.println(s.depth());           //  3          1 point
    System.out.println(s.runs());            //  2          1 point

    s.pop();
    s.pop();
    System.out.println(s.peek());            //  A          1 point
    System.out.println(s.depth());           //  1          1 point
    System.out.println(s.runs());            //  1          1 point

    s.pop();
    System.out.println(s.isEmpty());         //  true       1 point
    System.out.println(s.depth());           //  0          1 point
    System.out.println(s.runs());            //  0          1 point

    try
    {
      System.out.println(s.peek());
    }
    catch (IllegalStateException ignore)
    {
      System.out.println("No peek");         //  No peek    1 point
    }
  }
}
