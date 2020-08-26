<%@page import="com.sist.manager.MelonVO"%>
<%@page import="com.sist.manager.MelonVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
<%
	String strPage=request.getParameter("page");
	if(strPage==null){
		strPage="1";
	}
	// 이전 다음
	int curpage=Integer.parseInt(strPage);
	String mode=request.getParameter("mode");
	MelonDAO dao=new MelonDAO();
	String genre=dao.musicGetGenre(Integer.parseInt(mode)); // mode : 카테고리 번호
	ArrayList<MelonVO> list=dao.musicAllDate(Integer.parseInt(mode), curpage); // 카테고리번호 , 페이지번호
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>한국대중음악(<%=genre %>)</h1>
		<table class="table table-hover">
			<tr class="danger">
				<th class="text-center">순위</th>
				<th class="text-center"></th>
				<th class="text-center">곡명</th>
				<th class="text-center">가수명</th>
				<th class="text-center">앨범</th>
			</tr>
		<%
          for(MelonVO vo:list)
          {
        %>
             <tr>
		         <td class="text-center"><%=vo.getRank()+((curpage*10)-10) %></td> <!-- 페이지 순위 넘기기 -->
		         <td class="text-center">
		           <img src=<%=vo.getPoster() %> width=35 height=35 class="img-circle">
		         </td>
		         <td><%=vo.getTitle() %></td>
		         <td><%=vo.getSinger() %></td>
		         <td><%=vo.getAlbum() %></td>
		       </tr>
        <%
          }
       %>
		
		</table>
		<table class="table">
			<tr>
				<td class="text-center">
					<ul class="pagination">
					<%
						for(int i=1;i<=5;i++){
							
					%>
  						<li><a href="melon_main.jsp?mode=<%=mode %>&page=<%=i%>"><%=i %></a></li>
					<%
					}
					%>
					</ul>
				</td>
			</tr>
		</table>
		
	</center>
</body>
</html>
