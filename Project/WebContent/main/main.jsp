
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String id = null;
	if (session.getAttribute("id") != null) {
		id = (String) session.getAttribute("id");
}
%>
<!DOCTYPE html>
<html>
<head>
<title>홈핏</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="../css/custom.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!--  네비게이션   -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">홈핏</a>
			</div>
			<ul class="nav navbar-nav nav-list">
				<li><a href="#">홈트레이닝</a></li>
				<li><a href="#">홈트샵</a></li>
				<li><a href="#">홈트코치</a></li>
				<li><a href="#">함께도전해요</a></li>
				<li><a href="#">커뮤니티</a></li>
				<li><a href="#">홈트꿀팁</a></li>
			</ul>

			<%
				// 로그인 안 했을 때 
			if (id == null) {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li>
					<a href="../member/join.jsp">
						<span class="glyphicon glyphicon-user"></span> 
						<span> SIGN UP</span>
					</a>
				</li>
				<li>
					<a href="../member/login.jsp">
						<span class="glyphicon glyphicon-log-in"></span> 
						<span> LOGIN</span>
					</a>
				</li>
			</ul>

			<%
				// 로그인 했을 때  
			} else {
			%>
			<ul class="nav navbar-nav navbar-right">
				<li><a><%=session.getAttribute("name")%>님</span></a></li>
				<li><a href="../member/logout_ok.jsp"><span
						class="glyphicon glyphicon-log-in"></span> LOGOUT</a></li>
			</ul>
			<%
				}
			%>
		</div>
	</nav>
	<!--  본문   -->
	<div class="container">
		<div class="jumbotron">
			<h1>MAIN PAGE</h1>
	</div>
</body>
</html>