package com.sist.model;

import javax.servlet.http.HttpServletRequest;

import com.sist.controller.RequestMapping;

// interface�� ������� �ʴ´� (�Ϲ��ڹ� => POJO)
// ��ü ��� ���
// �󼼺���
public class MovieModel {
  @RequestMapping("movie/list.do")
  public String movieListData(HttpServletRequest request)
  {
	  request.setAttribute("msg", "��ȭ���");
	  // include�� ���Ե� jsp���� ����
	  request.setAttribute("main_jsp", "../movie/list.jsp");
	  return "../main/main.jsp";// request�� ������ jsp���ϸ� 
  }
  @RequestMapping("movie/detail.do")
  public String movieDetailData(HttpServletRequest request)
  {
	  request.setAttribute("msg", "��ȭ�󼼺���");
	  request.setAttribute("main_jsp", "../movie/detail.jsp");
	  return "../main/main.jsp";// request�� ������ jsp���ϸ� // forward , sendRedirect (jsp => _ok.jsp)
	  /*
	   *   1. ȭ�鿡 ����� ������ �ִ� ��� (request�� ����) ==> forward
	   *   2. ȭ�鿡 ����ϴ� ������ �ƴ� ��� (�����ͺ��̽� �߰�,����,����) ==> �����Ŀ� sendRedirect() �ٸ� ȭ������ �̵�
	   *      ��) insert_ok.jsp ==> list.jsp
	   *          login_ok.jsp ==> main.jsp
	   *          
	   */
	  
  }
}