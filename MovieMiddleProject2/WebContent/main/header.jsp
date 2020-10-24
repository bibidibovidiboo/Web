<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#logBtn').click(function(){
		// selector => css
		let id=$('#log_id').val();// getter  , $('#log_id').val("admin") setter
		//$('#log_id').val("hong");
		if(id.trim()=="")
		{
			$('#log_id').focus();
			return;
			// <input type="text" class="input-sm" name=id size="15" id="log_id" placeholder="아이디입력"  style="color:black">
		}
		
		let pwd=$('#log_pwd').val();// getter  , $('#log_id').val("admin") setter
		//$('#log_id').val("hong");
		if(pwd.trim()=="")
		{
			$('#log_pwd').focus();
			return;
			// <input type="text" class="input-sm" name=id size="15" id="log_id" placeholder="아이디입력"  style="color:black">
		}
		// id입력
		// pwd입력 ===> login_ok.do ==> 데이터 전송 
		$('#logFrm').submit(); //<form => action="파일명">
		// <input type=submit> ==> 'null'
	});
}); 
</script>
</head>
<body>
<div class="jumbotron text-center">
  <c:if test="${sessionScope.id==null }">
	  <form method="post" action="../member/login.do" id="logFrm">
	    <div class="text-right">
	      <input type="text" class="input-sm" name=id size="15" id="log_id" placeholder="아이디입력"  style="color:black">
	      <input type="password" class="input-sm" name=pwd size="15" id="log_pwd" placeholder="비밀번호입력"  style="color:black">
	      <input type="button" class="btn btn-danger btn-sm" id="logBtn" value="로그인">
	      <%--
	            <input type=submit>
	            <button>
	            <input type=image>   ==> submit
	       --%>
	    </div>
	  </form>
  </c:if>
  
  <c:if test="${sessionScope.id!=null }">
	  <form action="../member/logout.do">
	    <div class="text-right">
	      ${sessionScope.name }(${sessionScope.admin=='y'?'관리자':'일반유저' })님 로그인중입니다...
	      <button class="btn btn-danger btn-sm">로그아웃</button>
	    </div>
	  </form>
  </c:if>
</div>
<%-- <div id="dialog" style="display:none">
  <jsp:include page="../member/login.jsp"></jsp:include> 
</div> --%>
</body>
</html>