package cs2321;

import net.datastructures.*;

public class HashMap<K, V> extends AbstractMap<K,V> implements Map<K, V> {
	
	int capacity; // The size of the hash table
	int n = 0;
	long scale, shift;
	
	private UnorderedMap<K,V>[] table;
	
	@SuppressWarnings("unchecked")
	public void createTable() {
		table = (UnorderedMap<K,V>[]) new UnorderedMap[capacity];
	}
	
	/**
	 * Constructor that takes a hash size
	 * @param hashsize Table capacity: the number of buckets to initialize in the HashMap
	 */
	public HashMap(int hashsize) {
		// TODO Add necessary initialization
		capacity = hashsize;
		createTable();
	}
	
	public HashMap() {
		capacity = 17;
		createTable();
	}
	
	private int hashValue(K key) {
		return Math.abs(key.hashCode()) % capacity;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return n == 0;
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		return bucketGet(hashValue(key), key);
	}

	@Override
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		V answer = bucketPut(hashValue(key), key, value);
		if( n > capacity / 2) {
			resize(2*capacity-1);
		}
		return answer;
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		return bucketRemove(hashValue(key), key);
	}
	
	private void resize(int newCap) {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
		for(Entry<K,V> e: entrySet()) {
			buffer.addLast(e);
		}
		capacity = newCap;
		createTable();
		n = 0;
		for(Entry<K,V> e: buffer) {
			put(e.getKey(), e.getValue());
		}
	}
	
	protected V bucketGet(int h, K k) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null) {
			return null;
		}
		return bucket.get(k);
	}
	
	protected V bucketPut(int h, K k, V v) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null) {
			bucket = table[h] = new UnorderedMap<>();
		}
		int oldSize = bucket.size();
		V answer = bucket.put(k, v);
		n+= (bucket.size() - oldSize);
		return answer;
	}
	
	
	protected V bucketRemove(int h, K k) {
		UnorderedMap<K,V> bucket = table[h];
		if(bucket == null) {
			return null;
		}
		int oldSize = bucket.size();
		V answer = bucket.remove(k);
		n-= (oldSize - bucket.size());
		return answer;
	}
	

	@Override
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for(int h = 0; h < capacity; h++) {
			if(table[h] != null) {
				for(Entry<K,V> entry: table[h].entrySet()) {
					buffer.addLast(entry);
				}
			}
		}
		return buffer;
	}



}
