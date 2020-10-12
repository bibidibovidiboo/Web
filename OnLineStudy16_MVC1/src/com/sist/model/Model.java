package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public interface Model {
	// 기능 (요청) 처리하는 메소드 => Model의 모든 클래스가 요청을 처리하기 위한 메소드
	public String handlerRequest(HttpServletRequest request); // Call By References
	// 주소 (메모리) ==> 값을 주소에 채워주는 방식 (클래스,배열=> 메모리 주소를 이용하는 방식)
	/*
	 * 	매개변수 전송 방식 : Call by Value : 다른 메모리에 값을 복사 (일반 데이터)
	 *  			  Call by Reference : 같은 메모리 주소 원본이 넘어간다
	 */
}
