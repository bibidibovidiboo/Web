package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DetailModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "���뺸��");
		return "board/detail.jsp"; // ���� �����͸� �޴� ��
	}

}
