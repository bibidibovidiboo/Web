<%@page import="java.util.*,com.sist.dao.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String strPage=request.getParameter("page");
	// 사용자가 전송(요청) => 요청한 데이터를 톰캣이 모든 데이터를 묶어서  request에 첨부
	// 모든 데이터는 request를 통해서 들어온다
	/*
		list.jsp?page=1 => request.getParameter("page"); ==> 1
	*/
	if(strPage==null) // 처음에 한번은 사용자가 페이지번호를 보낼 수 없다
	{
		strPage="1";
	}
	int curpage=Integer.parseInt(strPage); // 현재 보고 있는 페이지
	// 현재 출력할 내용 가지고 오기
	Map map=new HashMap(); //
	List<BoardVO> list=BoardDAO.freeBoardListData(map);
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>