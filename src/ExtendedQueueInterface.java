/*
 * Purpose: Data Structure and Algorithms Lab 6
 * Status: Completed and throughly tested
 * Last update: 10/09/20
 * Submitted:  10/13/20
 * Comment: test suite and sample run attached
 * @author: Steven Orsini
 * @version: 2020.10.09
 */
public interface ExtendedQueueInterface<T> extends QueueInterface<T>{
	
  public void enqueueFirst(T newItem) throws ExtendedQueueException;
  
  public T dequeueLast() throws ExtendedQueueException;
  
  public T peekLast() throws ExtendedQueueException;

}  
