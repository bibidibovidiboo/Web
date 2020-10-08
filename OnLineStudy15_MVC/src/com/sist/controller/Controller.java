package com.sist.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.model.*;
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // XML�Ľ� , �����б� => ������ ���� 
	public void init(ServletConfig config) throws ServletException {
		//ServletConfig config => ��� web.xml�� ��� (��ϵ� �����͸� ���� �� �ִ�)
	}

    /*
     *    ����ڰ� �����ϴ� ���(GET / POST) 
     *      �޼ҵ� ó���� �޶� ���� 
     *      GET ==> doGet()
     *      POST ==> doPost()
     *      ==================
     *        doGet()+doPost() => service()
     *        
     *      ### Servlet => ���α׷��Ӱ� �����ϴ� ���� �ƴ϶� ��Ĺ�� �����Ѵ� 
     *         ================================================
     *         ��Ĺ�� ���� => web.xml�� ��� (������=>web.xml�� ���)
     */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// /Controller?cmd=list  ==> ListModel ==> list.jsp
		String cmd=request.getParameter("cmd");
		String jsp="";
		if(cmd.equals("list"))
		{
			ListModel model=new ListModel();
			model.execute(request);
			jsp="view/list.jsp";
		}
		else if(cmd.equals("find"))
		{
			FindModel model=new FindModel();
			model.execute(request);
			jsp="view/find.jsp";
		}
		else if(cmd.equals("detail"))
		{
			DetailModel model=new DetailModel();
			model.execute(request);
			jsp="view/detail.jsp";
		}
		
		// �ش� JSP�� request���� 
		RequestDispatcher rd=request.getRequestDispatcher(jsp);
		rd.forward(request, response);
		
	}

}