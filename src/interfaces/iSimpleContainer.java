package interfaces;

//This interface defines a simple container that can only be modified from "a single point". 
//It support only three operations.
//Stacks and queues (and priority queues as we will see later on) fit the definition. 
public interface iSimpleContainer<E> {
	// <E> indicates generic type which enables to use any type of item in the
	// container

	// Add an item to the container
	public void push(E obj);

	// Remove and return an item from the container. Returns null if the deque is
	// empty.
	public E pop();

	// Return the next item to be popped without actually popping (removing) it.
	// Returns null if the deque is empty.
	public E peek();

	// Returns the number of elements stored in the container
	public int size();

	// Returns true if the container contains no items; false otherwise
	public boolean isEmpty();

	// Clear all the contents of the container.
	public void clear();

}
