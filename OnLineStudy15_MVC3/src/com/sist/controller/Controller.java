package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// XML ==> Annotation
import java.util.*;
import com.sist.model.*;
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /*
     *   ��Ī  ==> ListModel == list.jsp   (list.jsp)
     *           DetailModel == detail.jsp (detail.jsp)
     *                                    ��ûó�� (Model)
     *   Ŭ���̾�Ʈ =============> Controller ======> Model <=====> DAO
     *          ��û (request)    (request)        ������� request�� ��´�
     *                              |       <===== 
     *                              |      request
     *                              |
     *                             JSP    request.setAttribute()
     *                             => request.getAttribute() ===> ${}
     *                             
     *       request       request    request       request  ==> session(�α���,��ٱ���)
     *   JSP ==> Controller ==> Model ==> Controller ==> JSP
     *   ======                =======    ==========     ===
     *    ��û                                     ��ûó��        �����                 ���
     */
	// 1. Ŭ���̾�Ʈ�� Controller ��û�� �ϸ� ==> ��û�� ó���ϴ� ModelŬ���� ã��
	// Controller�� � ��û���� �� ���� ���� => �ν�
	// list ==> ListModel 
	// detail ==> DetailModel
	private Map clsMap=new HashMap();
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. ��û�� �޴´�  ==> list,detail
		// 2. http://localhost/OnLineStudy15_MVC/(list).do
		// 3. http://localhost/OnLineStudy15_MVC/(detail).do
		// 4. http://localhost/OnLineStudy15_MVC/Controller?cmd=list
		String cmd=request.getRequestURI();///OnLineStudy15_MVC/(list).do 
		// URI ==> /OnLineStudy15_MVC/list.do(?page=2)=> request�� ���� ����
		System.out.println(cmd);
		cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
		// ����ڰ� ��û�� ��û���� Ȯ��
		
		// Ŭ������ ã�� ����
		Model model=(Model)clsMap.get(cmd);
		// ��ûó�� ==> �޼ҵ�
		String jsp=model.handlerRequest(request);
		// ������� ���� request => jsp�� ����
		RequestDispatcher rd=request.getRequestDispatcher(jsp);
		rd.forward(request, response);

	}
	@Override
	public void init(ServletConfig config) throws ServletException {
		clsMap.put("list",new ListModel());
		clsMap.put("detail", new DetailModel());// �̱��� 
	}
      

}