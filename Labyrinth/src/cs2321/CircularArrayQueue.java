
/**
 * Implement Queue ADT using a fixed-length array in circular fashion 
 *
 * @author ruihong-adm
 * @param <E> - formal type 
 *
 */

package cs2321;

/*	Timothy Leach
 * 	Assignment 1
 * 	This class creates a circular array
 * 
 */

import net.datastructures.Queue;

public class CircularArrayQueue<E> implements Queue<E> {
	private E[] storage;
	private int front = 0;
	private int queueSize = 0;
	public static final int defaultCapacity = 16;

	@SuppressWarnings("unchecked")
	public CircularArrayQueue(int capacity) {
		// TODO Auto-generated constructor stub
		storage = (E[])new Object[capacity]; 
	}
	
	public CircularArrayQueue() {
		this(defaultCapacity);
	}
	
	@Override
	@TimeComplexity("O(1)")
	public int size() {
		// TODO Auto-generated method stub
		return queueSize;
	}

	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return queueSize == 0;
	}

	/* Throw IllegalStateException when the queue is full */
	@Override
	@TimeComplexity("O(1)")
	public void enqueue(E e) throws IllegalStateException {
		// TODO Auto-generated method stub
		if(queueSize == storage.length) throw new IllegalStateException("Queue is full");
		int avail = (front + queueSize) % storage.length;
		storage[avail] = e;
		queueSize++;
	}

	@Override
	@TimeComplexity("O(1)")
	public E first() {
		// TODO Auto-generated method stub
		if(isEmpty()) return null;
		return storage[front];
	}

	@Override
	@TimeComplexity("O(1)")
	public E dequeue() {
		// TODO Auto-generated method stub
		if(isEmpty()) return null;
		E answer = storage[front];
		storage[front] = null;
		front = (front + 1) % storage.length;
		queueSize--;
		return answer;
	}
	
	@TimeComplexity("O(n)")
	void rotate() {
		/*
		 * TCJ
		 * all elements are being shifted to the left and the first one is being placed all the way to the right
		 */
		if(isEmpty()) throw new IllegalStateException("Queue is empty");
		E temp = storage[0];
		for(int i=0; i < queueSize-1; i++) {
			storage[i] = storage[i+1];
		}
		storage[queueSize-1] = temp;
	}
	
	public static void main(String[] args) {
		CircularArrayQueue<Integer> caq = new CircularArrayQueue<Integer>();
		for(int i = 0; i < defaultCapacity; i++) {
			caq.enqueue(i);
		}
		caq.rotate();
		caq.rotate();
		System.out.println(caq.size());
		System.out.println(caq.first());
		int j = caq.size();
		for(int i=0; i<j; i++) {
			System.out.println(caq.dequeue());
		}
		
	}

}
