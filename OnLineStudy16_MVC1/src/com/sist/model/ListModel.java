package com.sist.model;

import javax.servlet.http.HttpServletRequest;
// �Խ��� ���ó�� ==>
/*
 *    <%@ page contentType="text/html;charset=UTF-8" %>
 *    <%
 *         ���ó�� 
 *         List�� ���� ä���  ==> Model
 *    %>
 *    ==============================
 *    <html>
 *     <body>
 *        List�� ����ϴ� ���� 
 *     </body>
 *    </html>
 *    ============================== View
 */
import java.util.*;
import com.sist.dao.*;
public class ListModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		// �Խù� ����� ������ �´� => request�� ���� ��Ƽ� JSP�� ����
		String page=request.getParameter("page"); // ����ڰ� ��û�� �������� �޴´� 
		if(page==null) // �Խ����� �����Ҷ� => ù������ 
			page="1"; //default 
		int curpage=Integer.parseInt(page);
		// ���� ������ ==> list����� ������ �´�
		Map map=new HashMap();
		// WHERE num BETWEEN #{start} AND #{end}
		// start(������ġ,����ġ) => MyBatis���� ó��  
		int rowSize=10;
		int start=(rowSize*curpage)-(rowSize-1); // rownum�� ������ 1���� 
		int end=rowSize*curpage;
		/*
		 *   1page ==> 1 AND 10
		 *   2page ==> 11 AND 20
		 */
		map.put("start", start);
		map.put("end", end);
		
		List<BoardVO> list=BoardDAO.boardListData(map);
		
		int totalpage=BoardDAO.boardTotalPage();
		
		/*
		 *   JSP�� �����ؾߵ� �����ʹ� 3�� (����������,�������� , list���)
		 */
		request.setAttribute("list", list);
		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
		// ���⼭ ���۵� ���� �ƴ϶� ==> Controller���� request�� �޾Ƽ� ���� 
		/*
		 *    ����� ��û   ====> Controller  ===> Model ===== BoardDAO 
		 *    Model ==== Controller ===> JSP
		 */
		return "board/list.jsp";// � JSP�� request�� ���������� 
	}

}
