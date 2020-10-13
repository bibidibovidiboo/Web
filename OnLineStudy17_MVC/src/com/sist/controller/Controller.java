package com.sist.controller;

import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private List<String> list=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		list.add("com.sist.model.MovieModel");
		list.add("com.sist.model.ReserveModel");
		list.add("com.sist.model.MemberModel");
	}
   
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
				String cmd=request.getRequestURI();
				// /OnLineStudy17_MVC/movie/detail.jsp
				// movie/detail.do
				cmd=cmd.substring(request.getContextPath().length()+1);
				// Annotationã�� ��� 
				for(String strCls:list)
				{
				    // �޸� �Ҵ� 
					Class clsName=Class.forName(strCls);
					Object obj=clsName.newInstance();
					// �޼ҵ� ã��
					Method[] methods=clsName.getDeclaredMethods();
					for(Method m:methods)
					{
						RequestMapping rm=m.getAnnotation(RequestMapping.class);
						// ������̼��� �̿��ؼ� �ش� �޼ҵ� ã��
						if(cmd.equals(rm.value()))
						{
							// �ش� �޼ҵ� ȣ�� 
							String jsp=(String)m.invoke(obj, request);
							if(jsp.startsWith("redirect"))
							{
								// return "redirect:list.do"
								response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
							}
							else
							{
								// request�� ���� 
								RequestDispatcher rd=request.getRequestDispatcher(jsp);
								rd.forward(request, response);
							}
							return;//service�޼ҵ� ���� 
						}
					}
					
				}
		}catch(Exception ex){}
	}

}