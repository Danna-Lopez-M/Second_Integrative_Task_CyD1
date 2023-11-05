package dataStructures.IDataStructures;

public interface IQueue<T> {
	// Returns true if queue is empty.
	boolean isEmpty();
	// Returns the number of items in the queue.
	int size();
	// Returns the item least recently added to the queue
	T peek();
	// adds the item to the queue
	void enqueue(T item);
	// Removes and returns the item on this queue that was least recently added.
	T dequeue();
}
