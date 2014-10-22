package utils;

import java.util.Random;

/**
 * 生成随机数
 * @author Administrator
 *
 */
public class RandomGenerator {
	private static Random random = new Random();
	static char[] characters = ("abcdefghijklmnopqrstuvwxyz" + 
			"ABCDEFGHIGKLMNOPQRSTUVWXYZ").toCharArray();
	
	/**
	 * 随机生成字符
	 * @author Administrator
	 *
	 */
	public static class Character implements IGenerator<java.lang.Character> {
		@Override
		public java.lang.Character next() {		
			return characters[random.nextInt(characters.length)];
		}
	}
	
	/**
	 * 随机生成字符串
	 * @author Administrator
	 *
	 */
	public static class String implements IGenerator<java.lang.String> {
		private int DEFAULT_INITIAL_LENGTH = 10;
		private int length = DEFAULT_INITIAL_LENGTH;
		private IGenerator<java.lang.Character> charGenerator = new Character(); 
		
		public String() {}
		public String(int len) {
			this.length = len;
		}
		
		@Override
		public java.lang.String next() {
			char[] buf = new char[length];
			for(int i = 0; i < length; i++)
				buf[i] = charGenerator.next();
			return new java.lang.String(buf);
		}
	}
	
	/**
	 * 随机生成整数
	 * @author Administrator
	 *
	 */
	public static class Integer implements IGenerator<java.lang.Integer> {
		private int max = 1024;
		
		public Integer() {}
		
		public Integer(int max) {
			this.max = max;
		}
		
		@Override
		public java.lang.Integer next() {
			return random.nextInt(max);
		}		
	}
}
