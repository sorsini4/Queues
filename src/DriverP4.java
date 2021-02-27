import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
/*
 * Purpose: Data Structure and Algorithms Lab 6 Problem 3
 * Status: Everything works from previous testing, but the displayPackages method
 * 		throws a NullPointerException
 * Last update: 10/13/20
 * Submitted:  10/13/20
 * Comment: test suite and sample run attached
 * @author: Steven Orsini
 * @version: 2020.10.09
 */
public class DriverP4 {

	static BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		
		DEQueue<Package> packages = new DEQueue<Package>();
		DEQueue<Item> samples = new DEQueue<Item>();

		boolean isDone = false;
		int userAns;

		System.out.println("Hello, and welcome! Please choose an option to proceed\n");
		
		System.out.print("1. Pick up an order\n2. Drop off an order\n3. Display number of packages and weight of bag\n");
		System.out.print("4. Display number of items and weight of bag of samples\n5. Enjoy an item from bag of samples");
		System.out.print("\n6. Enjoy all the samples in a bag of samples\n\n");
		
		while(!isDone) {

			System.out.print("You know the options. Enter a number to proceed\n> ");
			
			try {
				userAns = Integer.parseInt(input.readLine());
			} 
			catch (NumberFormatException e) {
				userAns = 10;
			} 
			catch (IOException e) {
				userAns = 10;
			}

			switch(userAns) {
			case 0:
				System.out.println("\nGoodbye!");
				isDone = true;
				break;
			case 1:
				System.out.println("\nYou chose to pick up an order.");
				pickUpOrder(packages);
				break;
			case 2:
				System.out.println("\nDropping off order...");
				dropOffOrder(packages, samples);
				break;
			case 3:
				System.out.println("\nYou chose to display number of packages and weight of bag.");
				displayPackages(packages);
				break;
			case 4:
				System.out.println("\nYou chose to display number of items and weight of sample bag.");
				displaySamples(samples);
				break;
			case 5:
				System.out.println("\nYou chose to enjoy an item from bag of samples.");
				enjoySample(samples);
				break;
			case 6:
				System.out.println("\nYou chose to enjoy all the samples in the samples bag.");
				enjoyAllSamples(samples);
				break;
			case 7:
				System.out.println("\nYou chose to pick up an express order.");
				pickUpExpressOrder(packages);
				checkForExpress(packages, samples);
				break;
			}
		}
	}

	public static void pickUpOrder(DEQueue<Package> packages) {
		String name, sender, receiver;
		double weight;
		int amt;

		try {
			System.out.print("Please enter the following information about the item being "
					+ "picked up:\n> Name: ");
			name = input.readLine();
			System.out.print("\n> Weight: ");
			weight = Double.parseDouble(input.readLine());
			System.out.print("\n> Number of items: ");
			amt = Integer.parseInt(input.readLine());
			System.out.print("\n> Sender: ");
			sender = input.readLine();
			System.out.print("\n> Receiver: ");
			receiver = input.readLine();
			
			packages.enqueueFirst(new Package(name, weight, sender, receiver, amt, false));
			
			System.out.println("A package of " + packages.peek().getName() + " weighed at "
					+ packages.peek().getWeight() / packages.peek().getAmtOfItems() + " lbs each are now in the bag.");
		}
		catch(ExtendedQueueException e) {
			System.out.println("The stack must have room for elements!\n");
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid entry. Please restart.\n\n");
		}
		catch(IOException e) {
			pickUpOrder(packages);
		}
	}

	public static void dropOffOrder(DEQueue<Package> packages, DEQueue<Item> samples) {
		char answer;

		try {
			System.out.print("Would you allow me to keep a sample " + packages.peek().getRecipient() 
					+ "?\n> ");
			answer = Character.toLowerCase(input.readLine().charAt(0));
			if(answer == 'y') {
				System.out.println("Thanks for letting me keep a " + packages.peek().getName() + "!");
				samples.enqueue(new Item(packages.peek().getName(), packages.peek().getWeight()));
			}
			else {
				System.out.println("Oh okay, Thanks anyways!\n");
			}
			packages.dequeueLast();
		}
		catch(InputMismatchException e) {
			System.out.println("Please enter a valid input.\n");
		} 
		catch(QueueException e1) {
			System.out.println("The package bag must have packages to perform this operation!\n");
		}
		catch(ExtendedQueueException e2) {
			System.out.println("The package bag must have packages to perform this operation!\n");
		}
		catch(IOException e3) {
			System.out.println("Caught " + e3);
		}
	}

	public static void displayPackages(DEQueue<Package> packages) {
		DEQueue<Package> temp = new DEQueue<Package>();
		double weight = 0;
		int counter = packages.numItems;
		try {
			for(int i = 0; i < counter; i++) {
				temp.enqueue(packages.dequeue());
				weight = weight + (temp.peekLast().getWeight());
			}

			for(int i = 0; i < counter; i++) {
				packages.enqueue(temp.dequeue());
			}

			System.out.println("Your bag has " + (packages.numItems) + " package(s) in it, with a weight"
					+ " of " + weight + ".\n");
		}
		catch(QueueException e) {
			System.out.println("There are no packages to display!");
		}
		catch(ExtendedQueueException e1) {
			System.out.println("There are no packages to display!");
		}
	}

	public static void displaySamples(DEQueue<Item> samples) {
		System.out.println("Your samples are: ");
		DEQueue<Item> temp = new DEQueue<Item>();
		double weight = 0;
		int counter = samples.numItems;
		try {
			for(int i = 0; i < counter; i++) {
				temp.enqueueFirst(samples.dequeue());
			}

			for(int i = 0; i < counter; i++) {
				samples.enqueueFirst(temp.dequeue());
			}

			System.out.println("Your bag has " + (samples.numItems) + " samples(s) in it, with a weight"
					+ " of " + weight + ".\n");
		}
		catch(QueueException e) {
			System.out.println("There are no samples to display!");
		}
		catch(ExtendedQueueException e1) {
			System.out.println("There are no samples to display!");
		}
	}

	public static void enjoySample(DEQueue<Item> samples) {
		try {
			System.out.println("This " + samples.dequeue().getName() + " is amazing! I love free stuff.\n");
		}
		catch(QueueException e) {
			System.out.println("The sample bag must have samples to perform this operation!\n");
		}
	}

	public static void enjoyAllSamples(DEQueue<Item> samples) {
		try {	
			if(!samples.isEmpty()) {
				System.out.println("Sample bag has been emptied.\n");
				samples.dequeueAll();
			}
			else {
				throw new QueueException("");
			}
		}
		catch(QueueException e) {
			System.out.println("The sample bag must have samples to perform this operation!\n");
		}
	}
	
	public static void pickUpExpressOrder(DEQueue<Package> packages) {
		String name, sender, receiver;
		double weight;
		int amt;

		try {
			System.out.print("Please enter the following information about the express item being "
					+ "picked up:\n> Name: ");
			name = input.readLine();
			System.out.print("\n> Weight: ");
			weight = Double.parseDouble(input.readLine());
			System.out.print("\n> Number of items: ");
			amt = Integer.parseInt(input.readLine());
			System.out.print("\n> Sender: ");
			sender = input.readLine();
			System.out.print("\n> Receiver: ");
			receiver = input.readLine();
			
			packages.enqueueFirst(new Package(name, weight, sender, receiver, amt, true));
			
			System.out.println("A package of " + packages.peek().getName() + " weighed at "
					+ packages.peek().getWeight() / packages.peek().getAmtOfItems() + " lbs each are now in the bag.");
		}
		catch(ExtendedQueueException e) {
			System.out.println("The stack must have room for elements!\n");
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid entry. Please restart.\n\n");
		}
		catch(IOException e) {
			pickUpOrder(packages);
		}
	}
	
	public static void checkForExpress(DEQueue<Package> packages, DEQueue<Item> samples) {
		DEQueue<Package> temp = new DEQueue<Package>();
		int counter = packages.numItems;
		
		for(int i = 0; i < counter; i++) {
			if(packages.peek().getIsExpress()) {
				
			}
			else {
				temp.enqueue(packages.dequeue());
			}
		}
		
		for(int i = 0; i < counter; i++) {
			packages.enqueueFirst(temp.dequeue());
		}
		
	}
}
