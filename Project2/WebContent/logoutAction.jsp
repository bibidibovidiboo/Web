<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jsp 게시판</title>
<link rel="stylesheet" href="css/custom.css">
</head>
<body>
	<%
		session.invalidate(); // 접속한 회원의 세션을 없앤다
	%>
	<script>	
		location.href = 'main.jsp';
	</script>
</body>
</html>

