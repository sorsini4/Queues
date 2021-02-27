/*
 * Purpose: Data Structure and Algorithms Lab 7 Problem 1
 * Status: Completed and throughly tested
 * Last update: 10/28/20
 * Submitted:  10/28/20
 * @author: Steven Orsini
 * @version: 2020.10.28
 */
public class DEQueue<T> extends QueueRA<T> implements ExtendedQueueInterface<T>{
	
	public DEQueue() {
		super();
	}

	@Override
	public void enqueueFirst(T newItem) throws ExtendedQueueException {
		if(numItems == items.length) {
			resize();
		}
		items[front = (front + (items.length - 1)) % items.length] = newItem;
		this.numItems++;
	}

	@Override
	public T dequeueLast() throws ExtendedQueueException {
		T result;
		if(numItems != 0 && back == 0) {
			back = ( back + (items.length-1) ) % (items.length);
			result = items[back];
			items[back] = null;
			numItems--;
		}
		else if(numItems != 0 && back != 0) {
			result = items[--back];
			items[back] = null;
			numItems--;
		}
		else {
			throw new QueueException("The queue does not contain any elements.");
		}
		return result;
	}

	@Override
	public T peekLast() throws ExtendedQueueException {
		T result;
		if(numItems > 1 && back != 0) {
			int index = back - 1;
			result = items[index];
		}
		else if(numItems == 1) {
			result = items[front];
		}
		else if(back == 0) {
			result = items[numItems-1];
		}
		else {
			throw new ExtendedQueueException("The queue must have items in it to peek!");
		}
		return result;
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
