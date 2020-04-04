package interfaces;

//Deque is a storage-type data structure that can be thought as an extension of stacks and queues
//It supports insertion or deletion of elements from either the front or the back of the data structure
public interface iDeque<E> extends Iterable<E> {
  
  //E is generic. It indicates the type of data to be stored in the deque
	
  //Returns the number of elements stored in the deque
  public int size();
  //Returns true if the deque contains no items; false otherwise
  public boolean isEmpty();

  //Inserts an element at the front of the deque
	public void addFront (E o);
  //Removes and returns the element at the front of the deque. Returns null if the deque is empty.
	public E removeFront();
	//Return the item in the front without removing it. Returns null if the deque is empty.
	public E front();
	
  //Inserts an element at the back of the deque
	public void addBehind(E o);
  //Removes and returns the element at the back of the deque. Returns null if the deque is empty.
	public E removeBehind();
  //Return the item in the back without removing it. Returns null if the deque is empty.
	public E behind();
	
  //Clear all the contents of the deque.
  public void clear();

}
