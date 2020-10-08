package com.sist.controller;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.sist.model.*;
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//////////////////////////////////////////////////
	String[] strCmd={
		"list",
		"detail",
		"insert",
		"update",
		"delete"
	};
	String[] strClass={
		"com.sist.model.ListModel",
		"com.sist.model.DetailModel",
		"com.sist.model.InsertModel",
		"com.sist.model.UpdateModel",
		"com.sist.model.DeleteModel"
	};
	// ���ο� ��� �߰����� �� ���� �߰��� ���ϸ� ������ �ȵ� ��
	
	////////////////////////////////////////////////// XML
	// <bean id="list" class="com.sist.model.ListModel">
	private Map clsMap=new HashMap();
	public void init(ServletConfig config) throws ServletException {
		try
		{
			for(int i=0;i<strClass.length;i++)
			{
				Class cls=Class.forName(strClass[i]);
				Object obj=cls.newInstance();// �޸� �Ҵ� (���÷���)
				// Ŭ�������� �޾Ƽ� �޸� �Ҵ� => ��� ������ ����(�޼ҵ�,������,������� ����)
				System.out.println(strCmd[i]+":"+obj);
				clsMap.put(strCmd[i], obj);
			}
		}catch(Exception ex){}
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // ������� ��ûó�� 
		 String cmd=request.getRequestURI();
		 System.out.println("cmd:"+cmd);
		 cmd=cmd.substring(request.getContextPath().length()+1,cmd.lastIndexOf("."));
		 System.out.println("cmd2:"+cmd);
		 
		 // Ŭ���� ã�´�
		 Model model=(Model)clsMap.get(cmd);
		 // �޼ҵ带 ȣ���ؼ� ==> ���(��û) ó��
		 String jsp=model.execute(request);
		 // request�� ���� ��Ƽ� ==> JSP�� ����
		 RequestDispatcher rd=request.getRequestDispatcher(jsp);
		 rd.forward(request, response);
		 
	}
}