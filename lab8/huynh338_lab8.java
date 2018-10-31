//CSCI 1913 lab8
//Donald Huynh

class RunnyStack<Base>
{
	private class Node
	{
		private Base value;
		private Node next;
		private int count = 0;
		private Node(Base value, Node next)
		{
			this.value = value;
			this.next = next;
			count += 1;
		}
	}
	private Node top;

	public RunnyStack()
	{
		top = null;	
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
			return top.value;
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
			System.out.println("pop")
		}
	}
}
