package com.sist.model;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ListModel implements Model {

	@Override
	public String handlerRequest(HttpServletRequest request) {
		List<String> list=new ArrayList<String>();
		list.add("홍길동");
		list.add("심청이");
		list.add("박문수");
		list.add("춘향이");
		list.add("이순신");
		
		request.setAttribute("list", list);
		return "board/list.jsp";
	}

}
