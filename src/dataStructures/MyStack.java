package dataStructures;

import dataStructures.IDataStructures.IStack;

import java.util.NoSuchElementException;

// LIFO approach
public class MyStack<T> implements IStack<T> {
	private Node<T> first;
	private int size;
	// clase auxiliar
	private static class Node<T>{
		private T item;
		private Node<T> next;
	}
	public MyStack() {
		this.first = null;
		this.size = 0;
	}

	@Override
	public boolean isEmpty() {
		return this.first == null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public void push(T item) {
		Node<T> oldFirst = this.first;
		this.first = new Node<T>();
		this.first.item = item;
		this.first.next = oldFirst;
		this.size++;
	}

	@Override
	public T pop() {
		if(isEmpty()) throw new NoSuchElementException("Stack underflow");
		T item = this.first.item;
		this.first = this.first.next;
		size--;
		return item;
	}

	@Override
	public T peek() {
		if(isEmpty()) throw new NoSuchElementException("Stack underflow");
		return this.first.item;
	}
}
