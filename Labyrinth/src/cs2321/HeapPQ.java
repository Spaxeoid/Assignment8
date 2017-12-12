package cs2321;

import java.util.Comparator;

import net.datastructures.*;
/**
 * A Adaptable PriorityQueue based on an heap. 
 * 
 * Course: CS2321 Section ALL
 * Assignment: #3
 * @author
 */

public class HeapPQ<K,V> implements AdaptablePriorityQueue<K,V> {
	
	protected ArrayList<Entry<K,V>> heap = new ArrayList<>();
	private Comparator<K> comp = new DefaultComparator<K>();
	
	protected static class PQEntry<K,V> implements Entry<K,V>{
		private K k;
		private V v;
		
		public PQEntry(K key, V value) {
			k = key;
			v = value;
		}
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return k;
		}
		
		public void setKey(K key) {
			k = key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return v;
		}
		
		public void setValue(V value) {
			v = value;
		}	
		
	}
	
	protected int parent(int j) {
		return (j-1) / 2;
	}
	protected int left(int j) {
		return 2*j + 1;
	}
	protected int right(int j) {
		return 2*j + 2;
	}
	protected boolean hasLeft(int j) {
		return left(j) < heap.size();
	}
	protected boolean hasRight(int j) {
		return right(j) < heap.size();
	}
	protected void swap(int i, int j) {
		Entry<K,V> temp = heap.get(i);
		heap.set(i, heap.get(j));
		heap.set(j, temp);
	}

	
	public HeapPQ() {
		//TODO: implement this method
	}
	
	public HeapPQ(Comparator<K> c) {
		//TODO: implement this method
		comp = c;
	}
	
	/**
	 * The entry should be bubbled up to its appropriate position 
	 * @param int move the entry at index j higher if necessary, to restore the heap property
	 */
	public void upheap(int j){
		//TODO: implement this method
		while(j > 0) {
			int p = parent(j);
			if(comp.compare(heap.get(j).getKey(), heap.get(p).getKey()) >= 0) {
				break;
			}
			swap(j, p);
			j = p;
		}
		
	}
	
	/**
	 * The entry should be bubbled down to its appropriate position 
	 * @param int move the entry at index j lower if necessary, to restore the heap property
	 */
	
	public void downheap(int j){
		//TODO: implement this method
		while(hasLeft(j)) {
			int leftIndex = left(j);
			int smallChildIndex = leftIndex;
			if(hasRight(j)) {
				int rightIndex = right(j);
				if(comp.compare(heap.get(leftIndex).getKey(), heap.get(rightIndex).getKey()) > 0) {
					smallChildIndex = rightIndex;
				}
			}
			if(comp.compare(heap.get(smallChildIndex).getKey(), heap.get(j).getKey()) >= 0) {
				break;
			}
			swap(j, smallChildIndex);
			j = smallChildIndex;
		}
		
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return heap.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	@Override
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		checkKey(key);
		PQEntry<K,V> newest = new PQEntry<>(key, value);
		heap.addLast(newest);
		upheap(heap.size()-1);
		return newest;	
	}

	@Override
	public Entry<K, V> min() {
		// TODO Auto-generated method stub
		if(heap.isEmpty()) return null;
		return heap.get(0);
		
	}

	@Override
	public Entry<K, V> removeMin() {
		// TODO Auto-generated method stub
		if(heap.isEmpty()) return null;
		Entry<K, V> answer = heap.get(0);
		swap(0, heap.size() -1);
		heap.removeLast();
		downheap(0);
		return answer;
	}
	

	@Override
	public void replaceKey(Entry<K, V> entry, K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		checkKey(key);
		checkKey(entry.getKey());
		PQEntry<K,V> locator = new PQEntry<K,V>(key, entry.getValue());
		int i = 0;
		while(i < size() && entry != heap.get(i)) {
			i++;
		}
		if(i < size()) {
			heap.set(i, locator);
			downheap(i);
			upheap(i);
		}
	}

	@Override
	public void replaceValue(Entry<K, V> entry, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		checkKey(entry.getKey());
		PQEntry<K,V> newVal = new PQEntry<K,V>(entry.getKey(), value);
		int i = 0;
		while(i < size() && entry != heap.get(i)) {
			i++;
		}
		if(entry == heap.get(i)) {
			heap.set(i,  newVal);
			downheap(i);
			upheap(i);
		}
	}
	
	
	protected boolean checkKey(K key) throws IllegalArgumentException{
		try {
			return(comp.compare(key, key) == 0);
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("Incompatible key");
		}
		
		
	}
	
	@Override
	public void remove(Entry<K, V> entry) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		checkKey(entry.getKey());
		int i = 0;
		while(i<size() && entry != heap.get(i)) {
			i++;
		}
		if(entry == heap.get(i)) {
			swap(i, heap.size() -1);
			downheap(i);
		}
		
		
	}
	
	public static void main(String[] args) {
		HeapPQ<Integer, Integer> heap = new HeapPQ<Integer, Integer>();
		heap.insert(1, 1);
		heap.insert(2, 2);
		heap.insert(3, 3);
		heap.insert(4, 4);
		heap.insert(5, 5);
		
		System.out.println(heap.size());
		heap.removeMin();
		heap.replaceValue(heap.min(), 3);
		System.out.println(heap.min().getValue());
	}

}
