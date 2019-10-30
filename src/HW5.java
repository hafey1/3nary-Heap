import java.util.Random;
import java.util.Scanner;

public class HW5 {


	public static void main (String[] args) {
		Random random = new Random();
		Scanner keyboard = new Scanner(System.in);


		System.out.println("Please enter in how many values you would like in your 3-nary heap: ");


		int numOfValues = keyboard.nextInt();


		Comparable[] numbers = new Comparable[numOfValues];
		for(int i = 0; i < numbers.length - 1; i++) {

			numbers[i]= random.nextInt(50);
		}
		numbers[numbers.length - 1] = 3;
// Greatest value should be somewhere at the bot and it is not

		System.out.println("Please type Yay heaps to continue");
		String meme = keyboard.next();



		MinHeap	DebugTester = new MinHeap(numbers);
		System.out.println("This prints out the unbuilt heap");
		DebugTester.printBreadthFirst();

		System.out.println("Printing out the built heap");
		DebugTester.buildheap();
		DebugTester.printBreadthFirst();

		System.out.println("Printing out the heap with the Min value removed");
		DebugTester.removeMin();
		DebugTester.printBreadthFirst();

		System.out.println("This removes the second element");
		System.out.println(DebugTester.remove(1) + "\n");
		DebugTester.printBreadthFirst();

		System.out.println(" This prints out the heap with the value 4 included");
		DebugTester.insert(4);
		DebugTester.printBreadthFirst();

		System.out.println("\nThis will print out after the third value has been changed to 100 and it reheapifies");
		DebugTester.modify(2, 100);
		DebugTester.printBreadthFirst();

		System.out.println();
		System.out.println("And this will print out the Depth First Traversal");
		DebugTester.printDepthFirst();

		if (meme.equals("Yay heaps")) {
			System.out.println("\nRude");

		}
	}



}
