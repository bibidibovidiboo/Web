<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
div > p {
	color: red;
	font-size: 35px;
}
div span p {
	color: blue;
	font-size: 30px;
}
a {
	/* 검정색 , 언더라인 */
	text-decoration: none;
	color: black;
}
/* 클릭하기 위해 문자 위에 마우스를 올린 경우 : 가상 셀렉터 */
a:hover {
	text-decoration: underline;
	color: green;
}
img {
	opacity: 1.0;
}
img:hover {
	opacity: 0.3;
}
</style>
</head>
<body>
	<center>
		<div>
			<p>Java</p>
			<p>JavaScript</p>
			<span><p>Oracle</p></span>
		</div>
		
		<a href="#">Hello1</a><br>
		<a href="#">Hello2</a><br><br>
		
		<img src="1.jpg" width=300 height=350>	
		<img src="2.jpg" width=300 height=350>	
	</center>
</body>
</html>