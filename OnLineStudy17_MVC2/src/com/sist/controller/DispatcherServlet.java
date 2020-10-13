package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// DispatcherServlet : Spring ���� Controller��
// XML�� ��ϵ� Ŭ������ �о �޸� �Ҵ� 
import javax.xml.parsers.*;
import org.w3c.dom.*; //dom => Ʈ������ 
import java.util.*;
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<String> clsList=new ArrayList<String>();

	/*
	 *    1. web.xml
	 *        = Framework 
	 *        = Spring , Struts , ����� , jsf
	 *    2. server.xml
	 *    3. Model ======> DAO 
	 *    4. JSP
	 */
	public void init(ServletConfig config) throws ServletException {
		// web.xml�� ��ϵ� ������ �б� ���� (ServletConfig)
		/*
		 *   <init-param>
		      <param-name>contextConfigLocation</param-name>
		      <param-value>
		        <!-- ������ XML������ ��� -->
		        C:\Users\ä����\git\online\OnLineStudy17_MVC2\WebContent\WEB-INF\application-context.xml
		      </param-value>
		    </init-param>
		 */
		/*
		 *    <servlet>
				  <servlet-name>salesServlet</servlet-name>
				  <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
				  <!-- contextLoader�� �ش� ��ġ�� ���� ������ �о�, �ش� ������ dispatcher servlet���� �����. -->
				  <init-param>
				    <param-name>contextConfigLocation</param-name>
				    <param-value>/WEB-INF/salesServlet-servlet.xml</param-value>
				  </init-param>
				  <load-on-startup>1</load-on-startup>
				</servlet>
				
				<!-- /sales�� �����ϴ� url ��û�� �޾� salesServlet���� ó���Ѵ�. -->
				<servlet-mapping>
				  <servlet-name>salesServlet</servlet-name>
				  <url-pattern>*.naver</url-pattern>
				</servlet-mapping>
				
				.jsp .asp .aspx .php
				
				a.do/admin/1234  ==> node.js
				a.do?id=admin&pwd=1234
		 */
		String path=config.getInitParameter("contextConfigLocation");
		System.out.println(path);
		
		// �Ľ� 
		try
		{
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();// �Ľ��ϴ� Ŭ���� ����
			DocumentBuilder db=dbf.newDocumentBuilder();// ���� XML�Ľ� 
			// �Ľ� => �޸𸮿� Ʈ�����·� ���� ==> �ʿ��� �����͸� �߰�,����,����,�б�
			// �Ľ��� �����ϴ� ���� Document
			Document doc=db.parse(new File(path));
			// �ֻ��� �±� <beans> ==> �����ͺ��̽� (���̺�)
			Element root=doc.getDocumentElement(); // root => beans
			// bean�±׸� ��Ƽ� ���� ==> NodeList
			NodeList list=root.getElementsByTagName("bean");
			for(int i=0;i<list.getLength();i++)
			{
				Element bean=(Element)list.item(i); //bean�±׸� �Ѱ��� ������ �´� 
				String cls=bean.getAttribute("class");
				System.out.println(cls);
				clsList.add(cls);
			}
		}catch(Exception ex) {}
	}
    // ����ڰ� ��û�Ҷ� ȣ��Ǵ� �޼ҵ� => Model Class <==> JSP
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try
		{
			// ����� ��û�� �ޱ�  ==> movie/list.do
			String cmd=request.getRequestURI();
			// http://localhost/OnLineStudy17_MVC2/main/main.do  ==> URL
			// /OnLineStudy17_MVC2/main/main.do  ==> URI
			// /OnLineStudy17_MVC2  => ContextPath
			cmd=cmd.substring(request.getContextPath().length()+1);
			
			// �ش� �� Ŭ������ ã�´� ==> �޼ҵ带 ȣ���Ѵ� (invoke())
			// invoke() ==> �޼ҵ�� ���α׷��� ������� ���� �� �ִ�
			for(String strCls:clsList) // clsList�� Ŭ�������� ��� (application-context.xml)
			{
				// �޸� �Ҵ� 
				// Class.forName() , new  ==> ���÷���  
				// ���÷��� ==> Ŭ������ �̸����� ������ �о�ͼ� ���� (����,�޼ҵ� ȣ��...)
				// ���� : OOP�� �ı�
				Class clsName=Class.forName(strCls);
				Object obj=clsName.newInstance();
				// obj�� ������ �ִ� �޼ҵ带 ã�Ƽ� ȣ�� ==> request�� ���� ����ش� 
				Method[] methods=clsName.getDeclaredMethods();
				// Annotation�� ã�Ƽ� �ؿ� �ִ� �޼ҵ带 ȣ�� 
				for(Method m : methods)
				{
					/*
					 *   @RequestMapping("main/main.do")
					 *   public String disp()
					 */
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(cmd.equals(rm.value()))
					{
						// invoke(Object obj, Object... args) => �ٺ��Ű����� 
						String jsp=(String)m.invoke(obj, request,response);
						
						// sendRedirect => request�� �ʿ䰡 ���� ��� 
						// forward => request�� �� �ִ� ������ ��� 
						// ȭ�� �̵� 
						if(jsp.startsWith("redirect"))
						{
							// return "redirect:list.do"
							// response
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						}
						else
						{
							// return "main/main.jsp";
							// request���� => RequestDispatcher
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return; // service �޼ҵ� ���� 
					}
				}
				
			}
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
	}

}