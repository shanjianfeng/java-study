package com.onejian.study.hashmap;


public abstract class OpenAddressingHashMap<K, V> extends AbstractHashMap<K, V>{
	enum Status {
		ACTIVE, DELETED, EMPTY
	}
	
	@SuppressWarnings("hiding")
	class Node<K, V> extends BaseNode<K, V>{
		Status status;
		Node(K key, V value) {
			super(key, value);
			status = Status.ACTIVE;
		}
	}
	
	Node<K, V>[] table;
	
    private int count = 0;
    
    @SuppressWarnings("unchecked")
    OpenAddressingHashMap(int initCapacity) {
    	int capacity = initCapacity;
    	if(initCapacity <= 0)
    		capacity = DEFAULT_INITIAL_CAPACITY;
    	threshold = (int)(0.8*capacity);
    	table = new Node[capacity];
    }
    
    @SuppressWarnings("unchecked")
    OpenAddressingHashMap() {
    	super();
    	table = new Node[DEFAULT_INITIAL_CAPACITY];
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
		while(table[index] != null 
				&& table[index].status != Status.EMPTY ) {
			index = getNextTableIndex(index, table.length);
		}
		table[index] = new Node<K, V>(key, value);	
		
		count++;
		if(count >= threshold) {
			int newSize = table.length << 1;
			if(newSize >= MAXIMUM_CAPACITY)
				return null;
			resize(newSize); // 扩大两倍
		}
		return value;
	}
	
	//获取下一个table下标
	abstract int getNextTableIndex(int currentIndex, int len, Object...otherArgs);
	
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
//		return hashCode % table.length;
	}
	
    /**
     * 逻辑删除
     */
	@Override
	boolean delete(K key) {
		Node<K, V> node = getNode(key);
		if(node == null)
			return false;
		node.status = Status.DELETED;
		return true;
	}

	@Override
	boolean isContains(K key) {		
		return getNode(key) == null ? false : true;
	}

	protected Node<K, V> getNode(K key) {
		int index = getTableIndex(hash(key));
		int start = index;
		boolean isRight = true;
		while(table[index] != null 
				&& table[index].status != Status.EMPTY) {
			if(table[index].status == Status.ACTIVE && table[index].key.equals(key))
				break;
			index = getNextTableIndex(index, table.length);
			if(!isRight && index >= start)
				return null;
			if(index >= table.length)
				isRight = false;
		}
		return table[index];
	}
	
	@Override
	V get(K key) {
		Node<K, V> node;
		return (node = getNode(key)) == null ? null : node.value;
	}

	@Override
	public String toString() {
		String ret = "[";
		boolean isFirst = true;
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null && table[i].status == Status.ACTIVE ) {
				if(!isFirst)
					ret += ",";
				else
					isFirst = false;
				ret += "(" + table[i].key + "," + table[i].value + ")";
			}
		}
		ret += "]";
		return ret;
	}
}
