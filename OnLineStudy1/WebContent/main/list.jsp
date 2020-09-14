<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
/*
	자바
	오라클 : 공유에 필요한 데이터를 저장하는 공간
	HTML : 사용자 => 화면을 보여줄 내용
	CSS : HTML을 디자인
	JavaScript : 브라우저 안에서 이벤트 발생 (마우스 클릭...)
*/
	// 데이터 가지고오기
	EmpDAO dao=new EmpDAO();
	ArrayList<MovieVO> list=dao.movieListData();
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table1.css">
<!-- 외부 CSS -->
</head>
<body>
	<center>
		<h1>영화목록</h1>
		<table id="table_content" width=900>
			<tr>
				<th>번호</th>
				<th></th>
				<th>제목</th>
				<th>감독</th>
				<th>출연</th>
				<th>장르</th>
			</tr>
			<%-- 출력 --%>
			<%
				for(MovieVO vo:list)
				{
			%>
				<tr class="dataTr">
					<td align=center><%=vo.getMno() %></td>
					<td align=center>
						<img src="<%=vo.getPoster() %>" width=25 height=35>
					</td>
					<td><%=vo.getTitle() %></td>
					<td><%=vo.getDirector() %></td>
					<td><%=vo.getActor() %></td>
					<td><%=vo.getGenre() %></td>

				</tr>
			<% 
			}
			
			%>		
		
		</table>
	</center>

</body>
</html>