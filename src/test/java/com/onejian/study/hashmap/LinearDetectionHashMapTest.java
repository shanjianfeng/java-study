package com.onejian.study.hashmap;

import utils.RandomGenerator;
import junit.framework.TestCase;

public class LinearDetectionHashMapTest extends TestCase {
		
	public void testInsert() {
		LinearDetectionHashMap<Integer, String> ldhm = new LinearDetectionHashMap<Integer, String>();	
		RandomGenerator.String rgs = new RandomGenerator.String();
		RandomGenerator.Integer rgt = new RandomGenerator.Integer(10);		
		for(int i = 0; i < 10; i++) {
			ldhm.insert(rgt.next(), rgs.next());
		}
//		System.out.println(ldhm);
	}
	
	public void testDelete() {
		LinearDetectionHashMap<Integer, String> ldhm = new LinearDetectionHashMap<Integer, String>();	
		RandomGenerator.String rgs = new RandomGenerator.String();
		RandomGenerator.Integer rgt = new RandomGenerator.Integer(10);		
		int deleteKey = 0;
		for(int i = 0; i < 10; i++) {
			if(i == 3) {
				deleteKey = rgt.next();
				ldhm.insert(deleteKey, rgs.next());
			} else
				ldhm.insert(rgt.next(), rgs.next());			
		}
//		System.out.println(ldhm);
		assertNotNull(ldhm.delete(deleteKey));
//		System.out.println(ldhm);
	}
	
	public void testGet() {
		LinearDetectionHashMap<Integer, String> ldhm = new LinearDetectionHashMap<Integer, String>();	
		RandomGenerator.String rgs = new RandomGenerator.String();
		RandomGenerator.Integer rgt = new RandomGenerator.Integer(10);		
		int getKey = 0;
		for(int i = 0; i < 10; i++) {
			if(i == 3) {
				getKey = rgt.next();
				ldhm.insert(getKey, rgs.next());
			} else
				ldhm.insert(rgt.next(), rgs.next());
		}
//		System.out.println(ldhm);
		assertNotNull(ldhm.get(getKey));
//		System.out.println(ldhm);
	}
	
	
}
