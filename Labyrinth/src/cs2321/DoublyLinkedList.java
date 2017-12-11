package cs2321;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.datastructures.Position;
import net.datastructures.PositionalList;

/*	Timothy Leach
 * 	Assignment 1
 * 	This class creates a doubly linked list with an iterator to traverse the elements 
 */

public class DoublyLinkedList<E> implements PositionalList<E> {

	private static class Node<E> implements Position<E>{
		private E element;
		private Node<E> previous;
		private Node<E> next;
		public Node(E e, Node<E> p, Node<E> n) {
			element = e;
			previous = p;
			next = n;
		}
		
		@Override
		public E getElement() throws IllegalStateException {
			if(next == null)
				throw new IllegalStateException("Position is no longer valid");
			return element;
		}
		
		public Node<E> getPrev(){
			return previous;
		}
		
		public Node<E> getNext(){
			return next;
		}
		
		public void setElement(E e) {
			element = e;
		}
		
		public void setPrev(Node<E> p) {
			previous = p;
		}
		
		public void setNext(Node<E> n) {
			next = n;
		}
		
	}
	
	private Node<E> head;
	private Node<E> tail;
	private int size = 0;
	

	
	public DoublyLinkedList() {
		head = new Node<>(null, null, null);
		tail = new Node<>(null, head, null);
		head.setNext(tail);
	}
	
	private Node<E> validate(Position<E> p) throws IllegalArgumentException{
		if(!(p instanceof Node))throw new IllegalArgumentException("invalid p");
		Node<E> node = (Node<E>) p;
		if(node.getNext() == null) {
			throw new IllegalArgumentException("p is no longer in the list");
		}
		return node;		
	}

	private Position<E> position(Node<E> node){
		if(node == head || node == tail) {
			return null;
		}
		return node;
	}

	@Override
	@TimeComplexity("O(1)")
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> first() {
		// TODO Auto-generated method stub
		return position(head.getNext());
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> last() {
		// TODO Auto-generated method stub
		return position(tail.getPrev());
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return position(node.getNext());
	}
	
	@TimeComplexity("O(1)")
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ){
		Node<E> newest = new Node<>(e, pred, succ);
		pred.setNext(newest);
		succ.setPrev(newest);
		size++;
		return newest;
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> addFirst(E e) {
		// TODO Auto-generated method stub
		return addBetween(e, head, head.getNext());
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> addLast(E e) {
		// TODO Auto-generated method stub
		return addBetween(e, tail.getPrev(), tail);
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> addBefore(Position<E> p, E e)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> addAfter(Position<E> p, E e)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	@Override
	@TimeComplexity("O(1)")
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		E answer = node.getElement();
		node.setElement(e);
		return answer;
	}

	@Override
	@TimeComplexity("O(1)")
	public E remove(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		Node<E> predecessor = node.getPrev();
		Node<E> successor = node.getNext();
		predecessor.setNext(successor);
		successor.setPrev(predecessor);
		size--;
		E answer = node.getElement();
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return answer;
	}

	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return new ElementIterator();
	}

	@Override
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		return new PositionIterable();
	}
	
	public E removeFirst() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return remove(first());
	}
	
	public E removeLast() throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return remove(last());
	}
	
	private class PositionIterator implements Iterator<Position<E>>{
		private Position<E> cursor = first();
		private Position<E> recent = null;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cursor!=null;
		}

		@Override
		public Position<E> next() throws NoSuchElementException {
			// TODO Auto-generated method stub
			if(cursor == null) throw new NoSuchElementException("nothing left");
			recent = cursor;
			cursor = after(cursor);
			return recent;
		}
		
		public void remove() throws IllegalStateException{
			if(recent == null) throw new IllegalStateException("nothing to remove");
			DoublyLinkedList.this.remove(recent);
			recent = null;
		}
		
	}
	
	private class PositionIterable implements Iterable<Position<E>>{

		@Override
		public Iterator<Position<E>> iterator() {
			// TODO Auto-generated method stub
			return new PositionIterator();
		}
		
	}
	
	private class ElementIterator implements Iterator<E>{
		Iterator<Position<E>> posIterator = new PositionIterator();

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return posIterator.hasNext();
		}

		@Override
		public E next() {
			// TODO Auto-generated method stub
			return posIterator.next().getElement();
		}
		
		public void remove() {
			posIterator.remove();
		}
		
	}
	
	public static void main(String[] args) {
		DoublyLinkedList<Integer> link = new DoublyLinkedList<Integer>();
		System.out.println(link.isEmpty());
		link.addFirst(1);
		link.addAfter(link.first(), 1);
		link.addLast(15);
		link.addLast(30904);
		link.addLast(3);
		Iterator<Integer> it = link.iterator();
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.next());
		System.out.println(it.hasNext());
		it.remove();
		System.out.println(it.next());
		
	}

}
