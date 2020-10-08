package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ListModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		List<String> list=new ArrayList<String>();
		list.add("ȫ�浿");
		list.add("��û��");
		list.add("�ڹ���");
		list.add("������");
		list.add("�̼���");
		
		request.setAttribute("list", list);
		return "board/list.jsp";
	}

}
