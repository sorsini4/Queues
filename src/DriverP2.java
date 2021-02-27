import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Purpose: Data Structure and Algorithms Lab 6 Problem 3
 * Status: Completed and throughly tested
 * Last update: 10/13/20
 * Submitted:  10/13/20
 * Comment: test suite and sample run attached
 * @author: Steven Orsini
 * @version: 2020.10.09
 */
public class DriverP2 {
	
	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		
		int userAnswer;
		boolean done = false;
		
		DEQueue<String> myQueue = new DEQueue<String>();
		
		System.out.print("Hello! Please choose one of the following options!\n0. Exit\n"
				+ "1. Insert item at back\n2. Insert item at front\n3. Remove item from front\n"
				+ "4. Remove item from back\n5. Display front item\n6. Display back item\n"
				+ "7. Clear queue\n8. Display content of queue\n");
		
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
			
			System.out.println(userAnswer + "\n");
			
			switch(userAnswer) {
			case 0:
				System.out.println("Goodbye!");
				done = true;
				break;
			case 1:
				insertItemToBack(myQueue);
				break;
			case 2:
				insertItemToFront(myQueue);
				break;
			case 3:
				removeFromFront(myQueue);
				break;
			case 4:
				removeFromBack(myQueue);
				break;
			case 5:
				displayFrontItem(myQueue);
				break;
			case 6:
				displayBackItem(myQueue);
				break;
			case 7:
				clearQueue(myQueue);
				break;
			case 8:
				displayContent(myQueue);
				break;
			default:
				System.out.println("Please enter a valid input.\n");
				break;
			}
		}
	}
	
	public static void insertItemToBack(DEQueue<String> myQueue) {
		System.out.print("\nYou chose to enter an item to the back of the queue.\n"
				+ "What item would you like to enter?\n> ");
		
		try {
			String userInput = input.readLine();
			System.out.print(userInput + "\n");
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
	
	public static void insertItemToFront(DEQueue<String> myQueue) {
		System.out.print("\nYou chose to enter an item to the front of the queue.\n"
				+ "What item would you like to enter?\n> ");
		
		try {
			String userInput = input.readLine();
			System.out.print(userInput + "\n");
			myQueue.enqueueFirst(userInput);
			System.out.println("Number " + userInput + " added to the front of the queue!");
		}
		catch(NumberFormatException e) {
			System.out.println("Incorrect number format. Try again!");
		}
		catch(IOException e) {
			System.out.println("Incorrect number format. Try again!");
		}
	}
	
	public static void removeFromFront(DEQueue<String> myQueue) {
		try {
			System.out.println("Removed " + myQueue.dequeue() + " from the front of the "
					+ "current queue.");
		}
		catch(QueueException e) {
			System.out.println("There must be elements in the queue to remove one!");
		}
	}
	
	public static void removeFromBack(DEQueue<String> myQueue) {
		try {
			System.out.println("Removed " + myQueue.dequeueLast() + " from the back of the "
					+ "current queue.");
		}
		catch(QueueException e) {
			System.out.println("There must be elements in the queue to remove one!");
		}
	}
	
	public static void displayFrontItem(DEQueue<String> myQueue) {
		try {
			System.out.println("The item in the front of the queue is: " + myQueue.peek());
		}
		catch(QueueException e) {
			System.out.println("There must be elements in the queue to retrieve one!");
		}
	}
	
	public static void displayBackItem(DEQueue<String> myQueue) {
		try {
			System.out.println("The item in the back of the queue is: " + myQueue.peekLast());
		}
		catch(QueueException e) {
			System.out.println("There must be elements in the queue to retrieve one!");
		}
	}
	
	public static void clearQueue(DEQueue<String> myQueue) {
		myQueue.dequeueAll();
		System.out.println("All items from the current queue removed!");
	}
	
	public static void displayContent(DEQueue<String> myQueue) {
		System.out.println("The current queue: " + myQueue.toString());
	}
}
