package com.sist.model;

import javax.servlet.http.HttpServletRequest;

public class DeleteModel implements Model {

	@Override
	public String execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		request.setAttribute("msg", "�����ϱ�");
		return "board/delete.jsp";
	}

}
