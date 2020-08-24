<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    BoardDAO dao=new BoardDAO();
    // 데이터 받기
    ArrayList<BoardVO> list=dao.boardAllData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css">
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
</script>
</head>
<body>
	<center>
		<h1>게시판</h1>
		<table width=800 class="table_content">
			<tr>
				<td>
					<input type=text id=keyword size=25>
				</td>
			</tr>
		</table>
		<table border=1 width=800 id="user-table" class="table_content">
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>제목</th>
				<th>날짜</th>
				<th>조회수</th>
			</tr>
			
			<tbody>
			<%
				for(BoardVO vo:list){
			%>
				<tr>
               		<td><%=vo.getNo() %></td>
               		<td><%=vo.getName() %></td>
                	<td><%=vo.getSubject() %></td>
                	<td><%=vo.getRegdate() %></td>
                	<td><%=vo.getHit() %></td>
				</tr>
			<%
			}
			%>
			</tbody>
		</table>
	</center>

</body>
</html>