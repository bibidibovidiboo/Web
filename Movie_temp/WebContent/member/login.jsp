<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(function(){
	
	$('#logBtn').click(function(){
		$('#logFrm').submit(); //<form => action="파일명">

	});
});
</script>
</head>
<body>
	<c:if test="${sessionScope.id==null }">
		<form method="post" action="../member/login.do" id="logFrm">
			<div class="text-right">
				<input type="text" class="input-sm" name=id size="15" id="log_id"
					placeholder="아이디입력" style="color: black"> <input
					type="password" class="input-sm" name=pwd size="15" id="log_pwd"
					placeholder="비밀번호입력" style="color: black"> <input
					type="button" class="btn btn-danger btn-sm" id="logBtn" value="로그인">
				<%--
	            <input type=submit>
	            <button>
	            <input type=image>   ==> submit
	       --%>
			</div>
		</form>
	</c:if>
	<%--
       HTML(JSP)=> 처리 요청   ==> .do ==> Model <==> DAO
                               JSP <=== 결과값 전송 
   --%>
	<c:if test="${sessionScope.id!=null }">
		<form action="../member/logout.do">
			<%-- get(생략이 가능) --%>
			<div class="text-right">
				${sessionScope.name }(${sessionScope.admin=='y'?'관리자':'일반유저' })님
				로그인중입니다...
				<button class="btn btn-danger btn-sm">로그아웃</button>
			</div>
		</form>
	</c:if>
</body>
</html>