/*
 * Purpose: Data Structure and Algorithms Lab 6
 * Status: Completed and throughly tested
 * Last update: 10/13/20
 * Submitted:  10/13/20
 * Comment: test suite and sample run attached
 * @author: Steven Orsini
 * @version: 2020.10.09
 */
public class QueueRA<T> implements QueueInterface<T> {

	protected T[] items;
	protected int front, back, numItems; 
	
	@SuppressWarnings("unchecked")
	public QueueRA() {
		front = 0;
		numItems = 0;
		back = 0;
		items = (T[]) new Object[3];	
	}
	
	@Override
	public boolean isEmpty() {
		return numItems == 0;
	}

	@Override
	public void enqueue(T newItem) throws QueueException {
		if(numItems == items.length) {
			resize();
		}
		items[back] = newItem;
		back = (back + 1) % items.length;
		this.numItems++;	
	}

	@Override
	public T dequeue() throws QueueException {
		T result;
		if(numItems != 0) {
			result = items[front];
			items[front] = null;
			front = (front + 1) % items.length;
			numItems--;
		}
		else {
			throw new QueueException("The queue does not contain any elements.");
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void dequeueAll() {
		back = 0;
		front = 0;
		numItems = 0;
		items = (T[]) new Object[3];
	}

	@Override
	public T peek() throws QueueException {
		T result;
		if(numItems != 0) {
			result = items[front];
		}
		else {
			throw new QueueException("The queue does not contain any elements.");
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	protected void resize() {
	    T[] newItems = (T[]) new Object[numItems * 2];
	    
	    int i = 0; 
	    
	    do {
	    	newItems[i] = items[front];
	    	front = (front + 1) % items.length;
	    	i += 1;
	    } while(front != back);
	    
	    this.front = 0;
	    this.back = numItems;
	    this.items = newItems;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		String toReturn = "";

		if(numItems != 0) {
			int i = front;
			do {

				toReturn += items[i] + " ";
				i = (i+1) % items.length;

			} while(i != back);
		}
		return toReturn;
	}
	
}
