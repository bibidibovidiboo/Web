<%@page import="com.sist.dao.ReplyBoardDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%--
나중에 이거 배우면 getparameter을 이렇게 사용할 수 있음 (나중에 배움)
<jsp:useBean id="vo" class="com.sist.dao.ReplyBoardVO">
	<jsp:setProperty name="vo" property="*"/>
</jsp:useBean>
 --%>
<%
	try{
		request.setCharacterEncoding("UTF-8"); // 이거 없으면 한글이 깨짐 (한글 변환 코드 - 디코딩)
	}catch(Exception ex){}
	String name=request.getParameter("name");
	String subject=request.getParameter("subject");
	String content=request.getParameter("content");
	String pwd=request.getParameter("pwd");
	String pno=request.getParameter("pno");
	String strPage=request.getParameter("page");
	
	ReplyBoardVO vo=new ReplyBoardVO();
	vo.setName(name);
	vo.setSubject(subject);
	vo.setContent(content);
	vo.setPwd(pwd);
	
	// DAO 연결
	ReplyBoardDAO dao=new ReplyBoardDAO();
	// 답변 메소드 호출
	dao.boardReplyInsert(Integer.parseInt(pno),vo);
	
	// 이동
	response.sendRedirect("list.jsp?page="+strPage);
%>