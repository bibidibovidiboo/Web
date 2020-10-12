<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그아웃</title>
</head>
<body> 
	<div class="logout jumbotron" style="background-color: #fff">
		<h3 style="text-align:center">
			<%= session.getAttribute("name") %>님이 로그인 되었습니다.
		</h3>
		<div style="text-align:center; padding-bottom: 10px">
			<%
				String admin=(String)session.getAttribute("admin");
				// 관리자와 일반유저로 분리 
				if(admin.equals("y"))
				{
			%>		
					<span>관리자</span>	
			<% 
			
				}
				else{
			%>	
					<span>일반유저</span>
			<%
				}
			%>
		</div>
		<div style="text-align:center">
			<form method=post action="../user/logout_ok.jsp">
				<input type=submit value="로그아웃">
			</form>
		</div>
	</div>
</body>
</html>