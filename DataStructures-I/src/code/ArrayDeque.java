package code;


import given.iDeque;
//import java.util.Arrays;
import java.util.Iterator;

public class ArrayDeque<E> implements iDeque<E> {

	private E[] A;

	private int size;
	private int front;
	private int behind;
	private int cap;
	private final int INITIAL_CAP;

	public ArrayDeque() {
		this(1000);
		/*
		 * ADD CODE IF NEEDED
		 */
	}

	public ArrayDeque(int initialCapacity) {
		if (initialCapacity < 1)
			throw new IllegalArgumentException();
		A = createNewArrayWithSize(initialCapacity);
		size = 0;
		front = 0;
		behind = 0;
		cap = initialCapacity;
		INITIAL_CAP = initialCapacity;
		/*
		 * ADD CODE IF NEEDED
		 */
	}

	@SuppressWarnings({ "unchecked" })
	private E[] createNewArrayWithSize(int size) {
		return (E[]) new Object[size];
	}

	public String toString() {

		if (isEmpty())
			return "";
		StringBuilder sb = new StringBuilder(1000);
		sb.append("[");
		Iterator<E> iter = iterator();
		while (iter.hasNext()) {
			E e = iter.next();
			if (e == null)
				continue;
			sb.append(e);
			if (!iter.hasNext())
				sb.append("]");
			else
				sb.append(", ");
		}
		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private void extendCapacity() {
		E[] extended = (E[]) new Object[cap * 2];
		for (int i = 0; i < cap; i++) {
			int index = Math.floorMod(front + i, cap);
			extended[i] = A[index];
		}
		front = 0;
		cap *= 2;
		A = extended;
		behind = Math.floorMod(front + size, cap);
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public void addFront(E o) {
		if (size == cap)
			extendCapacity();
		front = Math.floorMod(front - 1, cap);
		A[front] = o;
		size++;
	}

	@Override
	public E removeFront() {
		if (isEmpty())
			return null;
		E value = A[front];
		A[front] = null;
		front = Math.floorMod(front + 1, cap);
		size--;
		return value;
	}

	@Override
	public E front() {
		if (isEmpty())
			return null;
		return A[front];
	}

	@Override
	public void addBehind(E o) {
		if (size == cap)
			extendCapacity();
		size++;
		A[behind] = o;
		behind = Math.floorMod(front + size, cap);
	}

	@Override
	public E removeBehind() {
		if (isEmpty())
			return null;
		int index = Math.floorMod(behind - 1, cap);
		E value = A[index];
		A[index] = null;
		size--;
		behind = Math.floorMod(front + size, cap);
		return value;
	}

	@Override
	public E behind() {
		if (isEmpty())
			return null;
		int index = Math.floorMod(behind - 1, cap);
		return A[index];
	}

	@Override
	public void clear() {
		A = createNewArrayWithSize(INITIAL_CAP);
		size = 0;
		front = 0;
		behind = 0;
		cap = INITIAL_CAP;
	}

	// Must print from front to back
	@Override
	public Iterator<E> iterator() {
		return new ArrayDequeIterator();
	}

	private final class ArrayDequeIterator implements Iterator<E> {
		int index = front;

		@Override
		public boolean hasNext() {
			return index != behind;
		}

		@Override
		public E next() {
			E value = A[index];
			index = Math.floorMod(index + 1, cap);
			return value;
		}
	}
}
