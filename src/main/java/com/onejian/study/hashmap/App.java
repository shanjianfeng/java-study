package com.onejian.study.hashmap;

/**
 * Hello world!
 *
 */
public class App 
{
	static class B {
		int i = 2 ;
	}
	static class A extends B {
		int j = 1;
		public A() {
			
		}
	}
    public static void main( String[] args )
    {
    	B[] test= new B[10];
    	test[1] = new A();
    	System.out.println(test[1].i);
        System.out.println( "Hello World!" );
    }
}
