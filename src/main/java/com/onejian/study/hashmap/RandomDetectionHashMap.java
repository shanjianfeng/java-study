package com.onejian.study.hashmap;

import utils.RandomGenerator;

/**
 * 开放定址法中的随机探查再散列解冲突
 * @author Administrator
 *
 */
public class RandomDetectionHashMap<K, V> extends OpenAddressingHashMap<K, V> {

	@Override
	int getNextTableIndex(int currentIndex, int len, Object... otherArgs) {
		RandomGenerator.Integer rgt = new RandomGenerator.Integer(len);	
		return rgt.next() % len;
	}
}
