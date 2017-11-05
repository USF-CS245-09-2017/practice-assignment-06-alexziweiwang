/**
 * BinaryHeap.java
 * 
 * @author Alex Wang
 *
 */
public class BinaryHeap {

	private int size;
	private int[] heap;

	/**
	 * Constructor
	 */
	public BinaryHeap() {
		size = 0;
		heap = new int[31];
	}

	/**
	 * Adds an int (or Integer) instance to the priority queue.
	 * 
	 * @param i
	 *            int given to be added
	 */
	public void add(int i) {
		if (heap.length == size) {
			grow_heap();
		}
		heap[size++] = i; // add into that position
		int index = size - 1;// last item's position
		while (heap[index] < heap[parent(index)]) {
			swap(heap, parent(index), index);
			index = parent(index);
		}
	}

	/**
	 * Removes the highest priority item — i.e. the lowest number — from the
	 * priority queue
	 * 
	 * @return the item deleted
	 * @throws Exception
	 *             for the case when heap is empty
	 */
	public int remove() throws Exception {
		if (size == 0) {
			heap = new int[31];
			throw new Exception("can't remove since the heap is empty");
		}

		int temp = heap[0]; // remove the first element in the heap
		heap[0] = heap[--size]; // get the last one in the heap
		bubble_down(0);

		return temp;
	}

	/**
	 * Enlarge the size of heap
	 */
	public void grow_heap() {
		int[] new_heap = new int[2 * heap.length];
		System.arraycopy(heap, 0, new_heap, 0, heap.length);
		heap = new_heap;
	}

	/**
	 * Get the index of parent item
	 * 
	 * @param index
	 *            index given to find the parent of it
	 * @return index of parent
	 */
	private int parent(int index) {
		return (index - 1) / 2;
	}

	/**
	 * Get the index of left child
	 * 
	 * @param index
	 *            index given to find the left child of it
	 * @return index of left child
	 * 
	 */
	private int left_child(int index) {
		return index * 2 + 1;
	}

	/**
	 * Get the index of right child
	 * 
	 * @param index
	 *            index given to find the right child of it
	 * @return index of right child
	 * 
	 */
	private int right_child(int index) {
		return index * 2 + 2;
	}

	/**
	 * Swap two items
	 * 
	 * @param arr
	 *            the array where swapping happens
	 * @param a
	 *            the first item of swapping
	 * @param b
	 *            the second item of swapping
	 */
	private void swap(int[] arr, int a, int b) {
		int temp = arr[a];
		arr[a] = arr[b];
		arr[b] = temp;

	}

	/**
	 * Keep swapping until all the value of child item being larger than its
	 * parent
	 * 
	 * @param index
	 *            given index to starting bubbling
	 */
	private void bubble_down(int index) {
		if (left_child(index) < size) { // make sure it's a valid child
			int child = left_child(index); // child -- an index
			if (right_child(index) < size && heap[right_child(index)] < heap[child]) {
				// compare two children and get the smaller one
				child = right_child(index);
			}
			if (heap[index] > heap[child]) {// number in parent position bigger
				// than that in child position
				swap(heap, index, child);
				bubble_down(child);
			}
		}

	}

	/**
	 * Print every item in the heap
	 */
	public void printHeap() {
		System.out.println("the heap now looks like this (" + size + ")-->" + heap[0]);
		for (int num : heap) {
			System.out.print(num + ",");
		}
		System.out.println("\n");
	}

	/**
	 * Get size of heap
	 * 
	 * @return size of heap
	 */
	public int getSize() {
		return size;
	}
}
