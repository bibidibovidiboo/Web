<!-- 
	jps의 기반 => html
	servlet의 기반 => java
	
	main.jsp 위치 
	C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\20200819-MovieProject\org\apache\jsp\main
	
	jsp => doget 안에 코딩을 해준다 (자동으로 out.println을 붙여줌)
 -->
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		String name="홍길동";
	%>
	<h1><%= name %></h1>

</body>
</html>