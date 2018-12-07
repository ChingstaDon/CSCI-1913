//CSCI 1913 Lab 9
//Donald Huynh

class ArrayQueue<Base>
{
	private int front;	//   Index of front object in OBJECTS.
	private int rear;	//   Index of rear object in OBJECTS.
	private Base[] objects;	//   The OBJECTs in the queue.

	//   Constructor. Make a new empty queu that can hold SIZE - 1 elements.
	public ArrayQueue(int size)
	{
		if (size <= 1)
		{
			throw new IllegalArgumentException("Illegal size.");
		}
		else
		{
			front = 0;
			rear = 0;
			objects = (Base []) new Object[size];
		}
	}

	//   DEQUEUE. Remove an object from the queue.
	
	public Base dequeue()
	{
		if (front == rear)
		{
			throw new IllegalStateException("Queue is empty.");
		}
		else
		{
			front = (front + 1) % objects.length;
			Base temp = objects[front];
			objects[front] = null;
			return temp;
		}
	}

	//   ENQUEUE. Add a new OBJECT to the queue.
	
	public void enqueue(Base object)
	{
		int nextRear = (rear + 1) % objects.length;
		if (front == nextRear)
		{
			throw new IllegalStateException("Queue is full.");
		}
		else
		{
			rear = nextRear;
			objects[rear] = object;
		}
	}

	//   IS EMPTY. Test if the queue is empty.
	
	public boolean isEmpty()
	{
		return front == rear;
	}

	// IS FULL. Test if the queue is full.
	
	public boolean isFull()
	{
		return front == (rear + 1) % objects.length;
	}

	public class Iterator
	{
		private int where;
		private Iterator()
		{
			where = front;
		}

		public boolean hasNext()
		{
			return where != rear;
		}

		public Base next()
		{
			if (where == rear)
			{
				throw new IllegalStateException("No next elements.");
			}
			else
			{
				where = (where + 1) % objects.length;
				Base temp = objects[where];
				return temp;
			}
		}

	}

	public Iterator iterator()
	{
		return new Iterator();
	}
}

//  QUETERATOR. Test ARRAY QUEUE's ITERATOR class. It's worth 20 points. Unlike
//  the example in the lecture, this code does NOT use the built-in ITERATOR
//  interface from the Java library. There are always many different ways to
//  do things when you write programs.

class Queterator
{

    //  MAIN. Start execution here.

    public static void main(String [] args)
    {

	//  Make an ARRAY QUEUE and enqueue some STRINGs.

	ArrayQueue<String> queue = new ArrayQueue<String>(4);

	queue.enqueue("A");
	queue.enqueue("B");
	queue.enqueue("C");

	//  Make a FIRST ITERATOR for QUEUE and use it to visit QUEUE's elements.

	ArrayQueue<String>.Iterator first = queue.iterator();
	while (first.hasNext())
	    {
		System.out.println(first.next());    //  A B C one per line    5 points
	    }

	//  The iterator hasn't changed QUEUE.

	System.out.println(queue.isEmpty());   //  false                 1 point
	System.out.println(queue.dequeue());   //  A                     1 point
	System.out.println(queue.dequeue());   //  B                     1 point
	System.out.println(queue.dequeue());   //  C                     1 point
	System.out.println(queue.isEmpty());   //  true                  1 point

	//  Let's enqueue more things to QUEUE.

	queue.enqueue("X");
	queue.enqueue("Y");
	queue.enqueue("Z");

	//  Now make a SECOND ITERATOR for QUEUE. The FIRST one does not work any more,
	//  because QUEUE has changed. Use SECOND to visit QUEUE's new elements.

	ArrayQueue<String>.Iterator second = queue.iterator();
	while (second.hasNext())
	    {
		System.out.println(second.next());   //  X Y Z one per line    5 points
	    }

	//  The new iterator hasn't changed QUEUE either.

	System.out.println(queue.isEmpty());   //  false                 1 point
	System.out.println(queue.dequeue());   //  X                     1 point
	System.out.println(queue.dequeue());   //  Y                     1 point
	System.out.println(queue.dequeue());   //  Z                     1 point
	System.out.println(queue.isEmpty());   //  true                  1 point	
    }
}
