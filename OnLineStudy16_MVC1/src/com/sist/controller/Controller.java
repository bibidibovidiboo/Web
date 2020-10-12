package com.sist.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import com.sist.model.*;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Callback 함수
	private Map clsMap=new HashMap(); 
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		String path=config.getInitParameter("contextConfigLocation");
		System.out.println(path);
		try {
			// XML 읽기
			DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
			DocumentBuilder db=dbf.newDocumentBuilder(); // 파싱기
			Document doc=db.parse(new File(path));
			Element root=doc.getDocumentElement();
			System.out.println(root); // <beans>
			
			NodeList list=root.getElementsByTagName("bean");
			
			for(int i=0;i<list.getLength();i++) {
				// bean 읽기 
				Element bean=(Element)list.item(i);
				String cmd=bean.getAttribute("id");
				String cls=bean.getAttribute("class");
				System.out.println("cmd="+cmd+",class="+cls);
				
				Class clsName=Class.forName(cls);
				Object obj=clsName.newInstance(); // 클래스 이름을 읽어서 메모리 할당
				
			}
			
		}catch (Exception ex) {

		}
	}

	// 사용자 요청 처리하는 메소드
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
