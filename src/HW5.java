import java.util.Random;

public class HW5 {


	public static void main (String[] args) {
		Random random = new Random();
		Comparable[] numbers = new Comparable[256];
		for(int i = 0; i < numbers.length - 1; i++) {

			numbers[i]= random.nextInt(50);
		}
		numbers[numbers.length - 1] = 3;
// Greatest value should be somewhere at the bot and it is not

		MinHeap	DebugTester = new MinHeap(numbers);
		//System.out.println("This prints out the unbuilt heap");
		//DebugTester.printDebug();
		//System.out.println(DebugTester.toString());

		//System.out.println("Printing out the built heap");
		DebugTester.buildheap();
		DebugTester.printDebug();

		//System.out.println("Printing out the heap with the Min value removed");
		DebugTester.removeMin();
		DebugTester.printDebug();
		//looking at how the remove is removing elements
		System.out.println("This removes the second element");
		System.out.println(DebugTester.remove(1));
		DebugTester.printDebug();

		System.out.println(" This prints out the heap with the value 4 included");
		DebugTester.insert(4);
		DebugTester.printDebug();

		DebugTester.modify(2, 100);
		DebugTester.printDebug();

		System.out.println(DebugTester.height(0));

		DebugTester.printBreadthFirst();
	}

}
