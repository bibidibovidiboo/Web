package com.sist.controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/////// XML �Ľ�
import javax.xml.parsers.*;
import org.w3c.dom.*;
//////////////////////////
import com.sist.model.*;
// ���� (Model <====> JSP)
/*
 *   Controller ��� => web.xml
 */
import java.util.*;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /*
     *    ���� ���� 
     *      = �޸� �Ҵ� (������ ȣ��) 
     *      = init() : ���� �б� , �������� 
     *      = service() : ����ڰ� ��û�� ó���ϴ� �޼ҵ� 
     *        ========= GET/POST ==> ���ÿ� ó���� ������ �޼ҵ� 
     *           GET  => doGet()
     *           POST => doPost()
     *      = destroy() : �Ҵ�� �޸𸮸� ȸ���Ѵ� 
     *      
     *      == ���ΰ�ħ,ȭ���̵� ==> destroy() ȣ�� ==> �޸� ȸ��
     *         ==========================================  �ٽ� ���󺹱� (���ο� ����,JSP ����)
     *         
     *         A a=new A(); ==> destroy() => �޸𸮴� ������� 
     *         ���ΰ�ħ , ȭ���̵� 
     *         A a=new A(); ==> ���ο� �޸𸮸� �ٽ� ����� (�ʱ�ȭ)
     */
	// XML�� �����͸� �о map�� ���� (Controller�� ����ϰ� �ִٰ� => ��û => �ٷ� ��Ŭ������ ã�� �� �ְ� �Ѵ�)
	// Callback�Լ��� 
	// ���� (��Ŭ���� ����)
	private Map clsMap=new HashMap(); // Ű , ��
	public void init(ServletConfig config) throws ServletException {
		// web.xml�� ��ϵ� => applicationContext.xml������ �б� 
		// web.xml�� ������ �����͸� �о���� Ŭ���� => ServletConfig
		String path=config.getInitParameter("contextConfigLocation");
		System.out.println(path);
		try
		{
			// XML�б�
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			// XML�� �Ľ��� �� �ְ� Ŭ���� ���� 
			DocumentBuilder db=dbf.newDocumentBuilder(); // �Ḻ̌� 
			// XML , WML , HDML (����)
			// XML �о �޸𸮿� ���� (������� :Document)
			Document doc=db.parse(new File(path));
			// �ֻ��� �±� ==> Ʈ�����·� ���� 
			Element root=doc.getDocumentElement();
			System.out.println(root.getTagName());// <beans>
			
			// bean => �±� �б�
			// bean�±׸� ��� ��� 
			// ���� �±׸� ������ ==> NodeList
			NodeList list=root.getElementsByTagName("bean");
			/*
			 *    <bean id="list.do" class="com.sist.model.ListModel"/>
				  <bean id="detail.do" class="com.sist.model.DetailModel"/>
				  <bean id="insert.do" class="com.sist.model.InsertModel"/>
			 */
			for(int i=0;i<list.getLength();i++)
			{
				// bean �б� 
				Element bean=(Element)list.item(i);
				String cmd=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println("cmd="+cmd+",class="+cls);
				// ���� 
				// Ŭ������ �޸� �Ҵ��� �Ѵ����� Ű,�ּ�
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance(); // Ŭ�����̸��� �о �޸� �Ҵ� 
				System.out.println("�Ҵ�� �ּ�:"+obj);
				
				// ����(Map)
				clsMap.put(cmd, obj);
			}
			/*
			 *   XML �Ľ�  => Spring,Mybatis ==> �Ľ� Ŭ���� 
			 *   SI , SM , �ַ�� , �����ӿ�ũ 
			 *   =======   ============== xml�� �Ľ� 
			 *   �̹� ������� Ŭ���� => ���� 
			 */
		}catch(Exception ex){}
	}
    // ����� ��û ó���ϴ� �޼ҵ� 
	// Callback�Լ��� 
	// Callback => ���α׷��Ӱ� ȣ���ϴ� �޼ҵ尡 �ƴ϶� �ý��ۿ� ���ؼ� �ڵ� ȣ��Ǵ� �޼ҵ� 
	// main()
	// ������ => �̺�Ʈ (�ݹ�)
	// ���α׷��Ӵ� �ݹ��� �Լ��� ���� �� ��� => �ݵ�� ȣ���� �ؾ� �Ѵ� 
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ����� ��û�ÿ� ó���� �Ǵ� ����
		// http://localhost/OnLineStudy16_MVC1/list.do URL
		// 1. ����� ��û�� �޴´� 
		String cmd=request.getRequestURI();// /OnLineStudy16_MVC1/list.do ==> URI
		//                                   =================== contextPath
		// 2. ModelŬ���� ã�� 
		cmd=cmd.substring(request.getContextPath().length()+1); // list.do , detail.do
		
		Model model=(Model)clsMap.get(cmd); // if�� ������� �ʾƵ� �ȴ� 
		// 1,2�� ó���ϱ� ���ؼ� ==> ���õ� �����͸� Map�� ���� ==> clsMap�� ����� Ŭ������ ã�´� 
		// 3. Model�� ���ؼ� ��ûó�� ==> ������� request,session�� ��´�
		String jsp=model.handlerRequest(request);// ó���Ŀ� request�� ���� ��� �ش� 
		// 4. JSP�� ã�´� 
		// 5. JSP�� request�� �����Ѵ�
		// request�� �ش� JSP�� �����ϴ� Ŭ���� => RequestDispatcher
		/*
		 *      Model 
		 *      
		 *      ==> return "board/list.jsp"
		 *      ==> return "redirect:list.do" => Spring
		 *      
		 *      insert_ok.jsp ==> list.jsp
		 *      update_ok.jsp
		 */
		if(jsp.startsWith("redirect"))
		{
			response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
			// ȭ�� �̵� ==> sendRedirect() => request�� �ʱ�ȭ 
		}
		else
		{
			RequestDispatcher rd=request.getRequestDispatcher(jsp);
			rd.forward(request, response);
			// ȭ�� �̵� ==> forward  ===> request�� ���� => jsp���� request�� ���� �����͸� �޾Ƽ� ��� 
		}
		
	}
    
}
