package com.yushang.wallpaper;

public class Test4 implements Test3{

	@Override
	public void say1() {
		System.out.println("test1");
	}

	@Override
	public void say2() {
		System.out.println("test2");
	}
	
	public static void main(String[] args) {
		Test4 test4 = new Test4();
		test4.say1();
		test4.say2();
	}

}
