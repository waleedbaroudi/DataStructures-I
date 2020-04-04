package implementation;


import java.util.Iterator;

import interfaces.iDeque;

public class LLDeque<E> implements iDeque<E> {

	private Node<E> header;
	private Node<E> trailer;

	private int size;

	private class Node<T> {
		private T element;
		private Node<T> next;
		private Node<T> prev;

		Node(T d, Node<T> n, Node<T> p) {
			element = d;
			next = n;
			prev = p;
		}

		public boolean hasNext() {
			return next != trailer;
		}
	}

	public LLDeque() {
		header = new Node<E>(null, null, header);
		trailer = new Node<E>(null, trailer, header);
		header.next = trailer;
		size = 0;
	}

	public String toString() {
		if (isEmpty())
			return "";
		StringBuilder sb = new StringBuilder(1000);
		sb.append("[");
		Node<E> tmp = header.next;
		while (tmp.next != trailer) {
			sb.append(tmp.element.toString());
			sb.append(", ");
			tmp = tmp.next;
		}
		sb.append(tmp.element.toString());
		sb.append("]");
		return sb.toString();
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
		Node<E> newNode = new Node<E>(o, header.next, header);
		header.next.prev = newNode;
		header.next = newNode;
		size++;
	}

	@Override
	public E removeFront() {
		if (isEmpty())
			return null;
		E value = header.next.element;
		header.next.next.prev = header;
		header.next = header.next.next;
		size--;
		return value;
	}

	@Override
	public E front() {
		if (isEmpty())
			return null;
		return header.next.element;
	}

	@Override
	public void addBehind(E o) {
		Node<E> newNode = new Node<E>(o, trailer, trailer.prev);
		trailer.prev.next = newNode;
		trailer.prev = newNode;
		size++;
	}

	@Override
	public E removeBehind() {
		if (isEmpty())
			return null;
		E value = trailer.prev.element;
		trailer.prev.prev.next = trailer;
		trailer.prev = trailer.prev.prev;
		size--;
		return value;
	}

	@Override
	public E behind() {
		if (isEmpty())
			return null;
		return trailer.prev.element;
	}

	@Override
	public void clear() {
		header.next = trailer;
		trailer.prev = header;
		size = 0;
	}

	@Override
	public Iterator<E> iterator() {
		return new LLDequeIterator();
	}

	private final class LLDequeIterator implements Iterator<E> {

		Node<E> current = header;

		@Override
		public boolean hasNext() {
			return current.hasNext();
		}

		@Override
		public E next() {
			current = current.next;
			return current.element;
		}
	}

}
