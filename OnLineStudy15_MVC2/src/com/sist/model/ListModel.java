package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class ListModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "�Խ��� ���");
		return "board/list.jsp";
	}

}
