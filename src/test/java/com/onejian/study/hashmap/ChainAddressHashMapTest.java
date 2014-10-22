package com.onejian.study.hashmap;

import utils.RandomGenerator;
import junit.framework.TestCase;

public class ChainAddressHashMapTest extends TestCase {
	AbstractHashMap<Integer, String> cahm; 
	public void testInsert() {
		cahm = new ChainAddressHashMap<Integer, String>();	
		RandomGenerator.String rgs = new RandomGenerator.String();
		RandomGenerator.Integer rgt = new RandomGenerator.Integer(10);		
		for(int i = 0; i < 10; i++) {
			cahm.insert(rgt.next(), rgs.next());
		}
//		System.out.println(cahm);
	}
	
	public void testDelete() {
		cahm = new ChainAddressHashMap<Integer, String>();		
		RandomGenerator.String rgs = new RandomGenerator.String();
		RandomGenerator.Integer rgt = new RandomGenerator.Integer(10);		
		int deleteKey = 0;
		for(int i = 0; i < 10; i++) {
			if(i == 3) {
				deleteKey = rgt.next();
				cahm.insert(deleteKey, rgs.next());
			} else
				cahm.insert(rgt.next(), rgs.next());	
		}
		System.out.println(cahm);
		assertNotNull(cahm.delete(deleteKey));
		System.out.println(cahm);
	}
	
}
