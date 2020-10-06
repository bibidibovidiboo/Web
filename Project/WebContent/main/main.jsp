<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	// 로그인 처리
	String id=(String)session.getAttribute("id"); 
	// 로그인 화면 변환 
	String log_jsp="";
	if(id==null) // 로그인이 안된 상태 
		log_jsp="../user/login.jsp";
	else // 로그인이 된 상태
		log_jsp="../user/logout.jsp";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSP 프로젝트</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- nav -->
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">홈핏</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">메인</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="#"><span class="glyphicon glyphicon-user"></span>
						Sign Up</a></li>
				<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
						Login</a></li>
			</ul>
		</div>
	</nav>
	<!-- container  -->
	<div class="container">
		<!-- log_jsp 화면 include -->
		<jsp:include page="<%=log_jsp %>"></jsp:include>
	</div>
</body>
</html>