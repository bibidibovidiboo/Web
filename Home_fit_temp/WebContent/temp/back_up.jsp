<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>레벨별 출력 확인</p>
	<c:forEach items="${list1 }" var="vo" begin="0" end="3">

		<p style="width: 100%">${vo.evo.subject }</p>
		<a href="../ex/detail.do?home_no=${vo.evo.home_no }"><img
			src="${vo.evo.poster }"></a>
	</c:forEach>

	<p>카테고리별 출력 확인</p>
	<c:forEach items="${list2 }" var="vo" begin="0" end="3">
		<p style="width: 100%">${vo.evo.subject }</p>
		<a href="../ex/detail.do?home_no=${vo.evo.home_no }"><img
			src="${vo.evo.poster }"></a>
	</c:forEach>
</body>
</html>