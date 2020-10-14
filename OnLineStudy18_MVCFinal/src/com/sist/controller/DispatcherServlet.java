package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 *   MVC 
 *   M = Model => ��ûó��(�����ͺ��̽� ����) ������� ������ ���� ���� (request�� ���� ��´�)
 *   V = View  => ��û ������� �޾Ƽ� ��� 
 *   C = Controller => Model�� �����ؼ� ������� ������ ���Ŀ� JSP ������� ����
 *   
 *   �Ϲ� �ڹ� (Model) <========> JSP,���� ���
 *                 request,session
 *                 request.setAttribute(),session.setAttribute()
 *                 ====================== ======================
 *                   �Ѱ��� JSP���� ���              ������Ʈ ��ü JSP���� ����
 *                                                              request
 *    ����� ��û (.do)  ========> Controller(DispatcherServlet) ============= 
 *                      ##service�޼ҵ� => ��Ĺ�� ���� ȣ�� => ������� ���� 
 *                    request       service(HttpServletRequest request)
 *                    
 *                             ������ ��û 
 *    Model (request)     <===============> DAO,Manager
 *     ��û�����͸� �޾Ƽ� ó��           ó���� ������� �����ش�
 *     ��û������� �޾Ƽ� 
 *      request�� ÷�� 
 *      ============
 *      request.setAttribute() ===========> Controller ==========> �ش� JSP���� ==== JSP������ 
 *                                                                          request�� �ִ� ������ ���
 *                                          ==> �ش� JSP�� request
 *                                          forward(request,response)
 *                                          sendRedirect("���ϸ�") ==> _ok.jsp
 *                         ==> request�� �ִ� �����͸� ���  forward(request�� ����)
 *                             request�� �ִ� �����͸� ������� �ʴ� ��� request�� ������ �ٸ� ���Ϸ� �̵� 
 *                             
 *    Controller : Model�� �ִ� �޼ҵ带 ã�� ==> ����  ==> �޼ҵ�������� JSP�� ����
 *                 ====================
 *                 ���ǹ� , Annotataion (�����Ӵ�:�޼ҵ���� �������� �ʴ´�) 
 */
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
// C:\\Users\\ä����\\git\\online\\OnLineStudy18_MVCFinal\\src
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // Model�� ���� => ����� ��û => ã���ִ� ���� 
	// Model class�� ��Ƽ� �޸� �Ҵ� => ã�� 
	List<String> clsList=new ArrayList<String>();
	public void init(ServletConfig config) throws ServletException {
		String xmlPath=config.getInitParameter("contextConfigLocation");
		String path=config.getInitParameter("path");
		System.out.println("xml-path:"+xmlPath);
		System.out.println("path:"+path);
		try
		{
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder();// �ļ��� ����
			Document doc=db.parse(new File(xmlPath));
			// ��Ʈ �±�
			Element root=doc.getDocumentElement();
			// ȸ������ , �α���(����) => �� => ��Ű => ���ϱ� , ����ó�� 
			NodeList list=root.getElementsByTagName("component-scan");
			ComponentScan com=new ComponentScan();
			for(int i=0;i<list.getLength();i++)
			{
				Element cs=(Element)list.item(i);
				String pack=cs.getAttribute("base-package");
				List<String> cList=com.fileConfig(pack, path);
				for(String s:cList)
				{
					System.out.println(s);
					clsList.add(s);
				}
			}
			
		}catch(Exception ex) {}
		System.out.println("DispatcherServlet");
		for(String s:clsList)
		{
			System.out.println(s);
		}
	}
    // ��û ����� ==> �ش� JSP�� ���� 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �޼ҵ� ã�Ƽ� ���� => ������� JSP�� ����
		// ����ڰ� ��û�� ������ �޴´� 
		// http://localhost
		String cmd=request.getRequestURI();
		// main/main.do
		cmd=cmd.substring(request.getContextPath().length()+1);
		///OnLineStudy18_MVCFinal/
		try
		{
			// �޸� �Ҵ�  (ModelŬ���� �޸� �Ҵ�) => clsList
			// new MainModel() ==> Controller (�Ѱ�) ==> ���� (������ �ϸ� => ���������� ��ƴ�) => ����
			// XML => C/Java/C#/JavaScript
			for(String cls:clsList)
			{
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance();
				// MainModel m=new MainModel();
				/*
				 *     class A
				 *     class B
				 *     
				 *     //A a=new A();
				 *    // a=new B();
				 *    Object obj=new A();
				 *    obj=new B();
				 */
				// �޼ҵ带 ã�Ƽ� ȣ�� (invoke())
				Method[] methods=clsName.getDeclaredMethods();
				// Ŭ������ ����� ��� �޼ҵ带 ������ �´� 
				for(Method m:methods)
				{
					RequestMapping rm=m.getAnnotation(RequestMapping.class);
					if(cmd.equals(rm.value()))
					{
						 String jsp=(String)m.invoke(obj, request);
								// a.display()
						if(jsp.equals("redirect"))
						{
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
							// return redirect:list.do
							// return "../main/main.jsp"
						}
						else
						{
							// request���� 
							RequestDispatcher rd=request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;// ���� (response=>����ڿ��� ������ ���۽� �ѹ��� ����)
						
					}
				}
			}
			
		}catch(Exception ex) 
		{
			ex.printStackTrace();
		}
		
	}

}
