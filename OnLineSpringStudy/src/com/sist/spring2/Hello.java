package com.sist.spring2;
/*
 * 	구현된 메소드를 가지고 갈 수 있다 : 필요한 곳에서만 사용이 가능 
 * 	구현 안 된 메소드 : 공통기반
 * 
 * 	DI : 주입 (스프링을 통해서 변수나 생성자 매게변수,메소드 호출)
 *  ADP : 
 */
public interface Hello {
	public void sayHello(String name);
	public default void display() {}
}
