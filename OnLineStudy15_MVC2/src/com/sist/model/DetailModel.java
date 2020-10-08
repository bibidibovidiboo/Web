package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DetailModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "내용보기");
		return "board/detail.jsp"; // 위의 데이터를 받는 곳
	}

}
