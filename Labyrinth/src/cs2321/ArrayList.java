package cs2321;

/*	Timothy Leach
 * 	Assignment 1
 * 	This class creates an arraylist that uses generics and an iterator to travers between the elements 
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {

	public static final int defaultCapacity = 16;
	private E[] storage;
	private int size = 0;
	
	public ArrayList() {
		this(defaultCapacity);
	}

	@SuppressWarnings("unchecked")
	@TimeComplexity("O(1)")
	public ArrayList(int capacity) {
		storage = (E[])new Object[capacity];
	}

	@Override
	@TimeComplexity("O(1)")
	public int size() {
		return size;
	}

	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		return (size == 0);
	}

	@Override
	@TimeComplexity("O(1)")
	public E get(int i) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		return storage[i];
	}

	@Override
	@TimeComplexity("O(1)")
	public E set(int i, E e) throws IndexOutOfBoundsException {
		checkIndex(i, size);
		E temp = storage[i];
		storage[i] = e;
		return temp;
	}

	@SuppressWarnings("unchecked")
	@Override
	@TimeComplexity("O(n)")
	public void add(int i, E e) throws IndexOutOfBoundsException {
		/*
		 * TCJ
		 * All elements are being shifted right
		 */
		checkIndex(i, size+1);
		if(size == storage.length) {
			E[] newArray = (E[])new Object[size*2];
			for(int j = 0; j < storage.length; j++) {
				newArray[j] = storage[j];
			}
			storage = newArray;
		}
		for(int k = size-1; k >= i; k--) {
			storage[k+1] = storage[k];
		}
		storage[i] = e;
		size++;
		
	}
	
	@Override
	@TimeComplexity("O(n)")
	public E remove(int i) throws IndexOutOfBoundsException {
		/*
		 * TCJ
		 * All elements are being shifted to the left
		 */
		checkIndex(i, size);
		E temp = storage[i];
		for(int k = i; k < size - 1; k++) {
			storage[k] = storage[k+1];
		}
		storage[size-1] = null;
		size--;
		return temp;
	}

	
	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator();
	}

	@TimeComplexity("O(n)")
	public void addFirst(E e) throws IndexOutOfBoundsException {
		add(0,e);
		size++;
	}
	
	@TimeComplexity("O(n)")
	public void addLast(E e) throws IndexOutOfBoundsException {
		if(size == 0) {
			add(0,e);
		}else {
		add(size-1, e);
		}
	}
		
	
	@TimeComplexity("O(n)")
	public E removeFirst() throws IndexOutOfBoundsException {
		E temp; 
		temp = remove(0);
		return temp;
	}
	
	@TimeComplexity("O(n)")
	public E removeLast() throws IndexOutOfBoundsException {
		E temp = remove(size-1);
		return temp;
	}
	
	@TimeComplexity("O(1)")
	protected void checkIndex(int i, int n) throws IndexOutOfBoundsException{
		if(i < 0 || i >= n) {
			throw new IndexOutOfBoundsException("Illegal index: " + i);
		}
	}
	
	private class ArrayIterator implements Iterator<E>{
		
		private int j = 0;
		private boolean removeable = false;
		@Override
		public boolean hasNext() {
			return j < size;
		}

		@Override
		@TimeComplexity("O(1)")
		public E next() throws NoSuchElementException{
			if( j == size) throw new NoSuchElementException("No next element");
			removeable = true;
			return storage[j++];
		}
		
		@TimeComplexity("O(n)")
		public void remove() throws IllegalStateException{
			if(!removeable) throw new IllegalStateException("There is nothing to remove");
			ArrayList.this.remove(j-1);
			j--;
			removeable = false;
		}
		
		
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> stuff = new ArrayList<Integer>();
		for(int i = 0; i < 50; i++) {
			stuff.add(i,i+1);
		}
		stuff.set(0, 12);
		stuff.set(49, 209);
		System.out.println(stuff.get(49));
		//Iterator<Integer> it = stuff.iterator();
		
	}
}
