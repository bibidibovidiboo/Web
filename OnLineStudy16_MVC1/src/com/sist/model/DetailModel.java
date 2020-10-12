package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import com.sist.dao.*;
// ==> �Խ��� �󼼺��� ó�� 
public class DetailModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// detail.do?no=1
		// request.setParameter("no",1); ==>  ==> request.getParameter("no") ==> 1
		// ����ڰ� ���� ���� request�� ��� �Ѱ��ִ� ���� (��Ĺ)
		// service(HttpServletRequest request) => request�� �Ű����� ==> ȭ���� ����ɶ����� request�� �ʱ�ȭ
		// 1. ����� ������ �Խù���ȣ�� �޴´� 
		String no=request.getParameter("no");
		// 2. BoardDAO�� ���ؼ� �Խù� �Ѱ��� �о� �´� (BoardVO) ==> SQL���� ���� => board-mapper.xml
		BoardVO vo=BoardDAO.boardDetailData(Integer.parseInt(no));
		// 3. �о�� BoardVO���� jsp�� ���� 
		
		request.setAttribute("vo", vo);
		return "board/detail.jsp";
	}

}