package com.sist.spring2;

public class HelloMain {

	public static void main(String[] args) {
		Hello hello=new HelloImpl();
		hello.sayHello("심청이");
	}
}