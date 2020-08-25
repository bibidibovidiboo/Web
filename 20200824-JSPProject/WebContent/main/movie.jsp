<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%

	String strPage=request.getParameter("page");
	if(strPage==null){
		strPage="1";
	}
    
	MusicDAO dao=new MusicDAO();
    // 데이터 받기
    int curpage=Integer.parseInt(strPage);
    int totalpage=dao.boardTotalPage();
    ArrayList<MovieVO> list=dao.movieAllData(curpage);
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
		<h1>다음영화</h1>
		<table class="table_content" width=1000>
			<tr>
				<td>
					<input type=text id=keyword size=25 placeholder="검색어 입력">
				</td>
			</tr>
		</table>
		<table width=1000 class="table_content">
			<tr>
				<th>순위</th>
				<th></th>
				<th>제목</th>
				<th>개봉일</th>
				<th>장르</th>
				<th>감독</th>
				<th>앨범</th>
			</tr>
			
			<tbody>
			<%
				for(MovieVO vo:list){
			%>
				<tr class="dataTr">
               		<td><%=vo.getNo() %></td>
               		<td>
                 	<img src=<%=vo.getPoster()%> width=60 height=80>
                 </td>
               		<td><%=vo.getTitle() %></td>
                 
                <td><%=vo.getRegdate() %></td>
                <td><%=vo.getGenre() %></td>
                <td><%=vo.getActor() %></td>
                <td><%=vo.getDirector() %></td>
				</tr>
			<%
			}
			%>
			</tbody>
		</table>
		<table class="table_content" width=1000>
			<tr>
				<td align=left></td>
				<td align=right>
					<a href="movie.jsp?page=<%=curpage>1?curpage-1:curpage%>">이전</a>
					<%=curpage %> page / <%=totalpage %> pages
					<a href="movie.jsp?page=<%=curpage<totalpage?curpage+1:curpage%>">다음</a>				
				</td>
			
			</tr>	
		</table>
	</center>

</body>
</html>