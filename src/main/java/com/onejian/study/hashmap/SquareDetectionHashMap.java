package com.onejian.study.hashmap;

/**
 * 开放定址法中的平方探测再散列解冲突
 * @author Administrator
 *
 */
public class SquareDetectionHashMap<K, V> extends OpenAddressingHashMap<K, V> {

	@Override
	int getNextTableIndex(int currentIndex, int len, Object... otherArgs) {
		return currentIndex*2 % len;
	}
}
