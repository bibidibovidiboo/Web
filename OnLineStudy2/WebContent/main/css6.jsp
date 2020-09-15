<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- 내부스타일 (한개의 파일에서만 적용) -->
<style type="text/css">
.book1{
	list-style-type: lower-alpha;
}
.book2{
	list-style-type: upper-roman;
}
.book3{
	list-style-type: none;
		/* display: inline-block; */
}
.book3 li{
	/* display: inline; */
}
</style>
</head>
<body>
<%--
	메뉴 ==> 부메뉴 (span)
	ol (order list)
	li (list item)
	=> block
--%>
	<ol class="book1">
		<li>자바의 정석</li>
		<li>오라클의 정석</li>
	</ol>
	<ol class="book2">
		<li>자바의 정석</li>
		<li>오라클의 정석</li>
	</ol>
	<ul class="book3">
		<li>자바의 정석</li>
		<li>오라클의 정석</li>
	</ul>
	
</body>
</html>