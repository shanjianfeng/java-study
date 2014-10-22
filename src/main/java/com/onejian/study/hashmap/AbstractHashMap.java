package com.onejian.study.hashmap;


public abstract class AbstractHashMap<K, V> {
	
	@SuppressWarnings("hiding")
	class BaseNode<K, V> {
		final K key;
		V value;   
		BaseNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	//阈值，达到阈值即进行扩容
    protected int threshold;
	
    static final int MAXIMUM_CAPACITY = 1 << 30;

    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    
    AbstractHashMap() {
    	threshold = (int)(0.8*DEFAULT_INITIAL_CAPACITY);
    }
    
	/*
	 * hash函数
	 */
	int hash(Object key) {
		return key == null ? 0 : key.hashCode();
	}
		
	abstract V insert(K key, V value);
		
	abstract boolean delete(K key);
	
	abstract boolean isContains(K key);
	
	abstract V get(K key);
}
