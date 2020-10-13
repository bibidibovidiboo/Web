package com.sist.model;

import javax.servlet.http.HttpServletRequest;
/*
 *     list.jsp => HTML�� ����Ѵ� (�����Ͱ� ���� ����)
 *     list.do  => �����͸� list.jsp�� �����Ŀ� ��� 
 *     
 *     list.jsp
 *     <%
 *                                      list.do
 *         List<BoardVO> list=BoardDAO.boardListData(); =>ListModel
 *     %>
 *     <html>
 *       <body>
 *         for(BoardVO vo:list)
 *         {
 *         }
 *       </body>
 *     </html>
 *     
 *     list.jsp ==> �׳� ��� 
 *                                         request
 *     list.do ==> Controller ==> ListModel ===> JSP (request�� ������ �����͸� ��� : ${})
 */
public class InsertOkModel implements Model{

	@Override
	public String handlerRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return "redirect:list.do";// �̵� => ��� ==> list.jsp(X) , list.do
	}

}