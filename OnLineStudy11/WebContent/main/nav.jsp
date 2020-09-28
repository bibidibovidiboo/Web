<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 메뉴가 올라가는 자리 --%>
<%--
	링크 : 이동 (메뉴클릭, 버튼) = 페이지 흐름=> 사이트 주소를 주는 방법
	한글 변환 => 반드시 링크가 걸린 jsp에서 한글을 변환한다

--%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%--
	btn-sm : 중간
	btn-lg : 큰버튼
	btn-xs ; 작은 버튼
--%>
  <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="../main/main.jsp">SIST Recipe</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="../main/main.jsp">Home</a></li>
      <li><a href="#">예매하기</a></li>
      <li><a href="../main/main.jsp?mode=9">게시판</a></li>
      <li><a href="../main/main.jsp?mode=1">자료실</a></li>
      <li><a href="#">마이페이지</a></li>
    </ul>
  </div>
</nav>
</body>
</html>