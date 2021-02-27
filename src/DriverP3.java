import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
/*
 * Purpose: Data Structure and Algorithms Lab 6 Problem 3
 * Status: Completed
 * Last update: 10/13/20
 * Submitted:  10/13/20
 * Comment: test suite and sample run attached
 * @author: Steven Orsini
 * @version: 2020.10.09
 */
public class DriverP3 {

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

			System.out.print(userAns + "\n");
						
			switch(userAns) {
			case 0:
				System.out.println("\nGoodbye!");
				isDone = true;
				break;
			case 1:
				pickUpOrder(packages);
				break;
			case 2:
				dropOffOrder(packages, samples);
				break;
			case 3:
				packages = displayPackages(packages);
				break;
			case 4:
				samples = displaySamples(samples);
				break;
			case 5:
				enjoySample(samples);
				break;
			case 6:
				enjoyAllSamples(samples);
				break;
			default:
				System.out.println("\nPlease enter a valid input, an integer 0-6.");
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
			System.out.print(name + "\n");
			
			System.out.print("\n> Weight: ");
			weight = Double.parseDouble(input.readLine());
			System.out.print(weight + "\n");

			System.out.print("\n> Number of items: ");
			amt = Integer.parseInt(input.readLine());
			System.out.print(amt + "\n");

			System.out.print("\n> Sender: ");
			sender = input.readLine();
			System.out.print(sender + "\n");

			System.out.print("\n> Receiver: ");
			receiver = input.readLine();
			System.out.print(receiver + "\n");
			
			packages.enqueue(new Package(name, weight, sender, receiver, amt, false));
			
			System.out.println("A package of " + name + " weighed at " + weight + " lbs each are now in the bag.");
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
			System.out.println(answer + "\n");

			if(answer == 'y') {
				System.out.println("Thanks for letting me keep a " + packages.peek().getName() + "!");
				samples.enqueue(new Item(packages.peek().getName(), (packages.peek().getWeight()) / packages.peek().getAmtOfItems()));
			}
			else {
				System.out.println("Oh okay, Thanks anyways!\n");
			}
			packages.dequeue();
		}
		catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("No deliveries to process!\n");
		}
		catch(InputMismatchException e1) {
			System.out.println("Please enter a valid input.\n");
		} 
		catch(QueueException e2) {
			System.out.println("The package bag must have packages to perform this operation!\n");
		}
		catch(ExtendedQueueException e3) {
			System.out.println("The package bag must have packages to perform this operation!\n");
		}
		catch(IOException e4) {
			System.out.println("Caught " + e4);
		}
	}

	public static DEQueue<Package> displayPackages(DEQueue<Package> packages) {
		DEQueue<Package> temp = new DEQueue<Package>();
		double weight = 0;
		int counter = packages.numItems;

		try {
			for(int i = 0; i < counter; i++) {
				temp.enqueue(packages.dequeue()); 
				weight = weight + (temp.peekLast().getWeight());
			}
			
			System.out.println("Bag has " + (temp.numItems) + " package(s), and weighs "
					+ weight + " lbs.\n");
		}
		catch(QueueException e) {
			System.out.println("There are no packages to display!");
		}
		catch(ExtendedQueueException e1) {
			System.out.println("There are no packages to display!");
		}
		
		return temp; 		
	}

	public static DEQueue<Item> displaySamples(DEQueue<Item> samples) {
		DEQueue<Item> temp = new DEQueue<Item>();
		double weight = 0;
		int counter = samples.numItems;

		try {
			for(int i = 0; i < counter; i++) {
				temp.enqueue(samples.dequeue());
				weight = weight + (temp.peekLast().getWeight());
			}
			
			System.out.println("Sample bag has " + (temp.numItems) + " items and weighs "
					+ weight + " lbs.\n");
		}
		catch(QueueException e) {
			System.out.println("There are no samples to display!");
		}
		catch(ExtendedQueueException e1) {
			System.out.println("There are no samples to display!");
		}
		
		return temp;
	}

	public static void enjoySample(DEQueue<Item> samples) {
		try {
			System.out.println("This " + samples.dequeueLast().getName() + " is amazing! I love free stuff.\n");
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
			System.out.println("Sample bag is already empty.\n");
		}
	}
}
