<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--
	<c:forEach var=i begin=i end=9>
		<tr>
			<c:forEach var=j begin=2 end=9>
			<td></td>
		</c:forEach>
		</tr>
	</c:forEach>
 --%>
<body>
   <center>
     <h1>구구단</h1>
     <table width=560 border="1">
       <tr>
         <%
            for(int i=2;i<=9;i++)
            {
         %>
            	<th><%= i+"단" %></th>
         <%
            }
         %>
       </tr>
       <%
            for(int i=1;i<=9;i++) // 줄수
            {
        %>
                <tr>
        <%
            	for(int j=2;j<=9;j++) // 데이터 출력
            	{
        %>
                    <td><%= j+"*"+i+"="+j*i %></td>
        <%
            	}
        %>
                 </tr>
        <%
            }
       %>
     </table>
   </center>
</body>
</html>
