<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- 1. lib에 라이브러리 추가 / 2. 여기서 로드 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <center>
      <h1>Java로 구구단 출력</h1>
      <table border=1 bordercolor=black width=500>
         <tr bgcolor=#ccffcc>
      <%
         for(int i=2;i<=9;i++)
         {
      %>
         <th><%=i+"단" %></th>
      <%
         }
      %>
         </tr>
      <%
         for(int i=1;i<=9;i++)
         {
      %>
         <tr>
      <% 
            for(int j=2;j<=9;j++)
            {
      %>   
            <td><%=j+"*"+i+"="+j*i %></td>
      <%
            }
      %>
         </tr>
      <%
         }
      %>
      
      </table>
      <br>
      <h1>JSTL/EL을 이용한 구구단</h1>
      <table border=1 bordercolor=black width=500>
        <tr>
          <c:forEach var="i" begin="2" end="9">
           <th>${ i }단</th>
           <%--
           ${i+"단"} => 오류
           EL => java만 사용하는것이 아니다
            --%>
          </c:forEach>
        </tr>
        <c:forEach var="i" begin="2" end="9">
        	<tr>
        		<c:forEach var="i" begin="2" end="9">
        			<td>${j }*${j }=${i*j }</td>
        		</c:forEach>
        	</tr>
        </c:forEach>
      </table>
     </center>
</body>
</html>