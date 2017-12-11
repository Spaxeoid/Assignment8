package cs2321;


import java.util.Iterator;
import java.util.NoSuchElementException;

import net.datastructures.Entry;
import net.datastructures.Map;

public class UnorderedMap<K,V> extends AbstractMap<K,V> {

	public UnorderedMap() {
		// TODO Auto-generated constructor stub
	}
	
	private ArrayList<mapEntry<K,V>> table = new ArrayList<>();
		

	private int findIndex(K key) {
		int n = table.size();
		for(int i = 0; i < n; i++) {
			if(table.get(i).getKey().equals(key)) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return table.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		int i = findIndex(key);
		if(i == -1) {
			return null;
		}
		return table.get(i).getValue();
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		int i = findIndex(key);
		if(i == -1) {
			table.add(0,new mapEntry<>(key,value));
			return null;
		}
		V temp = table.get(i).getValue();
		table.get(i).setValue(value);
		return temp;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		int i = findIndex(key);
		int n = size();
		if(i == -1) {
			return null;
		}
		V answer = table.get(i).getValue();
		if(i != n-1) {
			table.set(i, table.get(n-1));
		}
		table.remove(n-1);
		return answer;
	}

	private class EntryIterator implements Iterator<Entry<K,V>>{

		private int i = 0;
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return i < table.size();
		}

		@Override
		public Entry<K, V> next() {
			// TODO Auto-generated method stub
			if(i == table.size()) {
				throw new NoSuchElementException();
			}
			return table.get(i++);
		}
	}
	
	private class EntryIterable implements Iterable<Entry<K,V>>{
		public Iterator<Entry<K,V>> iterator(){
			return new EntryIterator();
		}
	}

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return new EntryIterable();
	}

}
