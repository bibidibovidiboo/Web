<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
/* CSS의 주석처리 */
/*
	오라클 대신 사용 : XML , JSON => JavaScript
	태그를 구분
	1) ID => 중복이 없는 태그 (한개만 제어할 때 사용)
	2) Class => 중복이 있는 경우 (.class명)
	ex) 포스터를 여러개 , 테이블 ,  input
	============ JavaScript , CSS에 사용 (디자인 , 태그제어)
	3) Name
	============ 자바에서 전송된 데이터를 받을 경우
*/
h1 {
	background-color: orange;
	color: blue;
}
#aaa{
	color : black;
}
#bbb{
	color : green;
}
#ccc{
	color : red;
}
.ddd{
	color : magenta;
}
</style>
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1 id="aaa">Java</h1>
		<h1 id="bbb">Oracle</h1>
		<h1 id="ccc">JSP</h1>	
		<!-- 동시에 제어 (반드시 class를 설정) -->
		<h1 class="ddd">Spring</h1>		
		<h1 class="ddd">Kotlin</h1>		
	</center>
</body>
</html>