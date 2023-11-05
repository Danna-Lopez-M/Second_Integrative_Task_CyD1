package dataStructures.IDataStructures;

// El elemento T debe ser comparable?
public interface IStack<T> {
	// Returns true if stack is empty.
	boolean isEmpty();
	// Returns the number of items in the stack.
	int size();
	// Adds the item to the stack.
	void push(T item);
	// Removes and returns the item most recently added to the stack
	T pop();
	// Returns (but does not remove) the item most recently added to the stack.
	T peek();
}
