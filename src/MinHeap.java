import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class MinHeap {
	//This is Max Schaffers implementation of a max heap
	//being tweaked to make a min heap with some added functionality

	private Comparable[] Heap; //Pointer to the heap array
	private int size; // Max size of the heap
	private int n; // Number of things now in heap

	//refactor minheap to maxheap because we are creating it
	// int num, int max
	public MinHeap(Comparable[] h)
	{	Heap = h;
		for (int i = 0; i < h.length; i++) {
			if (h[i] == null) {
				n = i;
				break;
			}
			if (i == h.length - 1) {
				n = i + 1 ;
			}
		}
		//n = num;
		size = h.length;
	}

	@Override
	public String toString() {
		String output = "";
		for (int i = 0; i < Heap.length; i++) {
			output = output + " " + Heap[i] + " ";
		}

		return output;
	}

	public Comparable[] getHeap() {
		return Heap;
	}

	public void printDebug() {
		System.out.println(Heap[0]);
		for (int i = 1; i < size; i = i + 3) {
//			try {
//				System.out.printf(Heap[i] + " || ");
//				System.out.printf(" " + Heap[i + 1] + " || ");
//				System.out.println(" " + Heap[i + 2] + "\n");
//			} catch (ArrayIndexOutOfBoundsException e) {
//				break;
//			}
			if (i <= n - 1)
			System.out.printf(Heap[i] + " || ");
			if (i + 1 <= n - 1) {
				System.out.printf(" " + Heap[i + 1] + " || ");
				if (i + 2 <= n - 1) {
					System.out.printf(" " + Heap[i + 2]);
				}
			}
			System.out.println();
		}
	}

	//public void insert(Comparable key)


	public void modify(int pos, Comparable newVal) {
		if ((pos < 0) || (pos >= n))
			return;
		Heap[pos] = newVal;
		update(pos);
	}

	private void update(int pos) {
		// If a value is small it needs to go up
		while ((pos > 0) && (Heap[pos].compareTo(Heap[parent(pos)]) <= 0)) {
			this.swap(pos, parent(pos));
			pos = parent(pos);
		}
		siftdown(pos);
	}
	//this is a helper method for the breadthFirst to find height
	public int height(int root) {

		if (this.leftchild(root) == -1)
			return 1;

		return 1 + height(leftchild(root));
	}


	// Return current size of the heap
	public int heapsize() {
		return n;
	}

	// Return true if pos a leaf position, false otherwise
	public boolean isLeaf(int pos) {
		return (pos > (n/3) && (pos < n));
	}

	// Return position for left child of pos
	public int leftchild(int pos) {
		if(pos > n/3)
			return -1;
		return 3*pos + 1;
	}

	// Return position for the right child of pos
	public int midchild(int pos) {
		if(pos >= n/3)
			return -1;
		return 3*pos + 2;
	}


	public int rightchild(int pos) {
		if (pos >= (n-1)/3)
			return -1;
		return 3*pos + 3;
	}
	// Returns the parent of the pos
	public int parent(int pos) {
		if (pos <= 0)
			return -1;
		return (pos-1)/3;
	}
	//this inserts the value to the heap
	// and then
	public void insert(Comparable key) {
		if (n >= size) {
			System.out.println("Heap is full");
			return;
		}
		int curr = n++;
		Heap[curr] = key;

		while ((curr != 0) && (Heap[curr].compareTo(Heap[parent(curr)]) <= 0)) {
			// create a temp value
			// insert it to the end
			this.swap(parent(curr), curr);
			curr = parent(curr);


			// This is the Books method Swap.swap(Heap, curr, parent(curr));
		}

	}

	public void buildheap()
	{
		for (int i = (n - 1)/ 3; i >= 0; i--) {
			siftdown(i);
		}
	}

	private void swap (int parent, int child) {
		Comparable temp = Heap[parent];
		Heap[parent] = Heap[child];
		Heap[child] = temp;
	}


	private void siftdown(int pos) {

		// the position you are given is outside of the current size of the heap do nothing
		if ((pos < 0) || (pos >= n))
			return;

		//if index is not a leaf
		// create a a comparative value of the left child
		// then change the index to the greatest of the children

		while (!isLeaf(pos)) {
			int j = leftchild(pos);
			try {
				//look for possible smallest value of the siblings

				//covers the case of jumping from first child, to second, to third
				if ((j < (n - 1)) && (Heap[j].compareTo(Heap[j + 1]) >= 0)) {
					j++;
					if ((j < (n - 1)) && (Heap[j].compareTo(Heap[j + 1]) >= 0))
						j++;
				}
				//this covers the case of comparing the left child with the right child
				else if (j < (n - 2) && (Heap[j].compareTo(Heap[j + 2]) >= 0))
					j = j + 2;

				// this needs to consider going to both other nodes not just one
				if (Heap[pos].compareTo(Heap[j]) < 0)
					return;
				this.swap(pos, j);
				//Swap.swap(Heap, pos, j);
				// how do we redesign the heapify so that it hits the highest internal node and then moves downward to lower values

				pos = j;  // Move down
				//Swap.swap(Heap, curr, parent(curr));
				//Comparable temp = Heap[parent(curr)];
			} catch (ArrayIndexOutOfBoundsException e) {
				break;
			}
		}
	}

	Comparable removeMin() {
		//can't remove from an empty heap
		if (n == 0)
			return -1;
		this.swap(0, --n);
		siftdown(0);
		return Heap[n];
	}

	Comparable remove(int pos) {
		if ((pos < 0) || (pos >= n)) {
			return -1;
		}
		if (pos == (n-1)) n--;
		else {
			this.swap(pos, --n);
			update(pos);
		}
		return Heap[n];
	}

	public void printBreadthFirst() {
		int height = this.height(0);
		int index = 0;
		// black magic iteration bby
		// if i just wanted to do a breadth first search i would do a simple queue ahhhh
		for (int i = 0; i < height; i++) {
			for(int j = 0; j < Math.pow(3,i); j++) {
				if (index >= n)
					break;
				System.out.printf(Heap[index++] + " ");
			}
			System.out.println();
		}
		//for (int i = 0; i)
		//Queue<Integer> breadthFirstQueue= new LinkedList<Integer>();
		//((LinkedList<Integer>) breadthFirstQueue).push(0);
		//printBreadthFirst(breadthFirstQueue);
	}



	public int printDepthFirst() {
		Deque<Integer> depthQueue = new LinkedList<Integer>();
		printDepthFirst(0, depthQueue);

		return 1;
	}

	public int printDepthFirst(int root, Deque<Integer> depthTracker) {
		depthTracker.offer(root);

		if (leftchild(root) > n) {
			return -1;
		}
		printBreadthFirst(this.leftchild(root), depthTracker);

		return 1;
	}

}
