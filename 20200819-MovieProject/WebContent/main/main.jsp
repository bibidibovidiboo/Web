<!-- 
	jps�� ��� => html
	servlet�� ��� => java
	
	main.jsp ��ġ 
	C:\webDev\webStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\work\Catalina\localhost\20200819-MovieProject\org\apache\jsp\main
	
	jsp => doget �ȿ� �ڵ��� ���ش� (�ڵ����� out.println�� �ٿ���)
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
		String name="ȫ�浿";
	%>
	<h1><%= name %></h1>

</body>
</html>