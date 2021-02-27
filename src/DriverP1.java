import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
 * Purpose: Data Structure and Algorithms Lab 6 Problem 1
 * Status: Completed and throughly tested
 * Last update: 10/10/20
 * Submitted:  10/13/20
 * Comment: test suite and sample run attached
 * @author: Steven Orsini
 * @version: 2020.10.09
 */
public class DriverP1 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		
		int userAnswer;
		boolean done = false;
		
		QueueRA<String> myQueue = new QueueRA<String>();
		
		System.out.print("Hello! Please choose one of the following options!\n0. Exit\n"
				+ "1. Insert item at back of the queue\n2. Remove item from front of the queue\n"
				+ "3. Display front item from the queue\n4. Clear queue\n5. Display content from queue\n");
		
		while(!done) {
			
			System.out.print("\nYou know the options. Enter a desired number for an operation\n> ");
			
			try {
				userAnswer = Integer.parseInt(input.readLine());
			}
			catch(NumberFormatException e) {
				userAnswer = 10;
			} 
			catch(IOException e1) {
				userAnswer = 10;
			}
			
			switch(userAnswer) {
			case 0:
				System.out.println("Goodbye!");
				done = true;
				break;
			case 1:
				insertItemToBack(myQueue);
				break;
			case 2:
				removeFromFront(myQueue);
				break;
			case 3:
				displayFrontItem(myQueue);
				break;
			case 4:
				clearQueue(myQueue);
				break;
			case 5:
				displayContent(myQueue);
				break;
			default:
				System.out.println("Please enter a valid input.\n");
				break;
			}
		}	
	}
	
	public static void insertItemToBack(QueueRA<String> myQueue) {
		System.out.print("\nYou chose to enter an item to the back of the queue.\n"
				+ "What item would you like to enter?\n> ");
		
		try {
			String userInput = input.readLine();
			myQueue.enqueue(userInput);
			System.out.println("Number " + userInput + " added to the back of the queue!");
		}
		catch(NumberFormatException e) {
			System.out.println("Incorrect number format. Try again!");
		}
		catch(IOException e) {
			System.out.println("Incorrect number format. Try again!");
		}
		
	}
	
	public static void removeFromFront(QueueRA<String> myQueue) {
		try {
			System.out.println("Removed " + myQueue.dequeue() + " from the front of the "
					+ "current queue.");
		}
		catch(QueueException e) {
			System.out.println("There must be elements in the queue to remove one!");
		}
	}
	
	public static void displayFrontItem(QueueRA<String> myQueue) {
		try {
			System.out.println("The item from the front of the queue is: " + myQueue.peek());
		}
		catch(QueueException e) {
			System.out.println("There must be elements in the queue to retrieve one!");
		}
	}
	
	public static void clearQueue(QueueRA<String> myQueue) {
		if(!myQueue.isEmpty()) {	
			myQueue.dequeueAll();
			System.out.println("All items from the current queue removed!");
		}
		else {
			System.out.println("Your queue is empty!");
		}
	}
	
	public static void displayContent(QueueRA<String> myQueue) {
		if(!myQueue.isEmpty()) {
			System.out.println("Here is the current queue: " + myQueue.toString());
		}
		else {
			System.out.println("Your queue is empty!");
		}
	}
}
