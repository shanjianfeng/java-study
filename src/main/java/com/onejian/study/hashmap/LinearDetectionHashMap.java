package com.onejian.study.hashmap;

/**
 * 开放定址法中的线性探测再散列解冲突
 * @author Administrator
 *
 */
public class LinearDetectionHashMap<K, V> extends OpenAddressingHashMap<K, V> {

	@Override
	int getNextTableIndex(int currentIndex, int len, Object... otherArgs) {
		return (++currentIndex) % len;
	}
}
