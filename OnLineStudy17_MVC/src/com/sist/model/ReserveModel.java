package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.RequestMapping;

/*
 *   1. ��ȭ���
 *   2. ������
 *   ===========
 *   3. ��¥����
 *   4. �ð�����
 *   5. �ο�����
 *   6. �������� 
 */
public class ReserveModel {
	 @RequestMapping("reserve/movie.do")
     public String movieList(HttpServletRequest request)
     {
    	 // ������(jsp) ==> request�� ����ش� 
    	 request.setAttribute("msg", "����=>��ȭ����");
    	 request.setAttribute("main_jsp", "../reserve/movie.jsp");
   	     return "../main/main.jsp";// request�� ������ jsp���ϸ� 
     }
	 @RequestMapping("reserve/theater.do")
     public String theaterList(HttpServletRequest request)
     {
    	 request.setAttribute("msg", "����=>��������");
    	 request.setAttribute("main_jsp", "../reserve/theater.jsp");
   	     return "../main/main.jsp";// request�� ������ jsp���ϸ� 
     }
}

