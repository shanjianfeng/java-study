package com.onejian.study.hashmap;

/**
 * 链地址法
 * @author Administrator
 *
 * @param <K>
 * @param <V>
 */
public class ChainAddressHashMap<K, V> extends AbstractHashMap<K, V>{

	@SuppressWarnings("hiding")
	class Node<K, V> extends BaseNode<K, V> implements Cloneable{ 
		Node<K, V> next;
		Node(K key, V value, Node<K, V> next) {
			super(key, value);
			this.next = next;
		}
		
		@Override
		public Object clone() throws CloneNotSupportedException {
			return super.clone();

		}
	}

	Node<K, V>[] table;

	int tableNodeCount = 0;
	
    @SuppressWarnings("unchecked")
	ChainAddressHashMap() {
    	super();
    	table = new Node[DEFAULT_INITIAL_CAPACITY];
	}
	
    @SuppressWarnings("unchecked")
    ChainAddressHashMap(int initCapacity) {
    	int capacity = initCapacity;
    	if(initCapacity <= 0)
    		capacity = DEFAULT_INITIAL_CAPACITY;
    	threshold = (int)(0.8*capacity);
    	table = new Node[capacity];
    }
    
	@Override
	V insert(K key, V value) {
		if(key == null) {
			throw new NullPointerException();
		}
		
		//是否已经存在
		Node<K, V> existedNode = getNode(key);
		if(existedNode != null) {
			existedNode.value = value;
			return value;
		}
		
		int index = getTableIndex(hash(key));
		Node<K, V> node = new Node<K, V>(key, value, null);
		if(table[index] == null) {
			table[index] = node;
		}else {
			Node<K, V> tmp = table[index].next;
			table[index].next = node;
			node.next = tmp;
		}
		
		tableNodeCount++;
		if(tableNodeCount >= threshold) {
			int newSize = table.length << 1;
			if(newSize >= MAXIMUM_CAPACITY)
				return null;
			resize(newSize); // 扩大两倍
		}
		return value;
	}
	
	protected Node<K, V>[] resize(int size) {
		if(size <= table.length)
			return table;
		@SuppressWarnings("unchecked")
		Node<K, V>[] newTable = new Node[size];		
		System.arraycopy(table, 0, newTable, 0, table.length);
		table = newTable;
		return newTable;
	}
	
	protected int getTableIndex(int hashCode){
		// 减少%的开销，采用&的方法
		return hashCode & (table.length-1);
	}
	
	@Override
	boolean delete(K key) {
		int index = getTableIndex(hash(key));
		Node<K, V> node = table[index];
		Node<K, V> preNode = null;
		while(node != null) {
			if(node.key.equals(key)) {
				if(preNode != null)
					preNode.next = node.next;
				else
					table[index] = node.next;
			}
			preNode = node;
			node = node.next;
		}
		return false;
	}

	@Override
	boolean isContains(K key) {
		return getNode(key) == null ? false : true;
	}

	@Override
	V get(K key) {
		Node<K, V> node;
		return (node = getNode(key)) == null ? null : node.value;
	}
	
	protected Node<K, V> getNode(K key) {
		int index = getTableIndex(hash(key));
		Node<K, V> node = table[index];
		while(node != null) {
			if(node.key.equals(key))
				return node;
			node = node.next;
		}
		return null;
	}
	

	@Override
	public String toString() {
		String ret = "[";
		boolean isFirst = true;
		for(int i = 0; i < table.length; i++) {
			Node<K, V> node = table[i];
			while(node != null) {
				if(!isFirst)
					ret += ",";
				else
					isFirst = false;
				ret += "(" + node.key + "," + node.value + ")";
				node = node.next;
			}
		}
		ret += "]";
		return ret;
	}
}
