<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%
    MusicDAO dao=new MusicDAO();
    // 데이터 받기
    ArrayList<MovieVO> list=dao.movieAllData();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#keyword').keyup(function(){
		var k=$(this).val();
		$('#user-table > tbody > tr').hide();
		var temp=$('#user-table > tbody > tr > td:nth-child(5n+3):contains("'+k+'")');
		$(temp).parent().show();
	});
});
</script>
</head>
<body>
	<center>
		<h1>Movie</h1>
		<table width=800>
			<tr>
				<td>
					<input type=text id=keyword size=25>
				</td>
			</tr>
		</table>
		<table border=1 width=800 id="user-table">
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th></th>
				<th>개봉일</th>
				<th>장르</th>
				<th>감독</th>
				<th>앨범</th>
			</tr>
			
			<tbody>
			<%
				for(MovieVO vo:list){
			%>
				<tr>
               		<td><%=vo.getNo() %></td>
               		<td><%=vo.getTitle() %></td>
                 <td>
                 	<img src=<%=vo.getPoster()%> width=30 height=30>
                 </td>
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
	</center>

</body>
</html>