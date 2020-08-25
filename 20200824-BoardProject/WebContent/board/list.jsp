<%@page import="com.sun.org.apache.bcel.internal.generic.SIPUSH"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*,java.text.*"%>
<%
	// 게시판 페이지
	// list.jsp?page=1 => request.getParameter("page") => 1
	// list.jsp	=> request.getParameter("page") => null (list.jsp 뒤에 변수가 X)
	// list.jsp?page=	=> request.getParameter("page") => "" 공백
	String strPage=request.getParameter("page");
	
	// 처음 페이지는 null이니까 1페이지를 보여줘라 (이거 없음 오류남 ★)
	if(strPage==null){
		strPage="1";
	}
	
	/*
	request vs response
	1) request (내장 객체 => 미리 생성된 객체)
		= HttpServletRequest request
		= 주요기능
		1. 브라우저 정보 (사용자의 정보)
			= 사용자의 IP
			= 사용자의 PORT
		2. 사용자 요청정보 (사용자가 보낸 모든값을 받을 수 있는 기능)
			= 단일값 : getParameter("보낸변수명");
					list.jsp?page=1 => getParameter("pages");
					<input type=text name=no>
					=> getParameter("no");
			= 다중값 : checkbox , select
					=> getParameterValues()
			= 한글처리 : 한글 => 인코딩 => 디코딩 (한글이 정상적으로 들어온다)
					setCharactorEncoding("UTF-8")
	*/
    BoardDAO dao=new BoardDAO();
    // 데이터 받기
    int curpage=Integer.parseInt(strPage);
    int totalpage=dao.boardTotalPage();
    ArrayList<BoardVO> list=dao.boardAllData(curpage); // 여기 () 사이에 페이지 안넣으면 오류남
    
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
</head>
<body>
	<center>
		<h1>게시판</h1>
		<table class="table_content" width=700>
			<tr>
				<td>
					<a href="insert.jsp">새글</a>
				</td>
			</tr>
		</table>
		<table  width=700 class="table_content">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>이름</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			
			<tbody>
			<%
				for(BoardVO vo:list){
			%>
				<tr class="dataTr">
               		<td><%=vo.getNo() %></td>
                	<td>
                		<a href="detail.jsp?no=<%=vo.getNo()%>"><%=vo.getSubject() %></a>
                		<%
                			Date date=new Date();
                			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); // 출력시 이 형식으로 출력해달라
                			String today=sdf.format(date);
                			if(today.equals(vo.getRegdate().toString())){
                			%>
                				<font color=red><sup>new</sup></font>
                			<% 	
                			}
                		%>
                	</td>
                	<td><%=vo.getName() %></td>
                	<td><%=vo.getRegdate() %></td>
                	<td><%=vo.getHit() %></td>
				</tr>
			<%
			}
			%>
			</tbody>
		</table>
		<table class="table_content" width=700>
			<tr>
				<td align=left></td>
				<td align=right>
					<a href="list.jsp?page=<%=curpage>1?curpage-1:curpage%>">이전</a>
					<%=curpage %> page / <%=totalpage %> pages
					<a href="list.jsp?page=<%=curpage<totalpage?curpage+1:curpage%>">다음</a>				
				</td>
			
			</tr>
		
		</table>
	</center>

</body>
</html>