<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
<%
	// 1. 사용자가 보낸 id,pwd 받기
	String id=request.getParameter("id");
	String pwd=request.getParameter("pwd");
	// 2. DAO에서 보낸 값 받기 
	MemberVO vo=MemberDAO.memberLogin(id, pwd);
	// 3. 로그인이 되면 => main.jsp
	// id가 틀린 경우 => 로그인 창으로 이동하기 
	if(vo.getMsg().equals("NOID")) {
%>		
	<!--  Ajex  -->
	<script>
    alert("ID가 존재하지 않습니다");
	history.back();
	</script>
<% 		
	}
	// id가 존재 => 비밀번호가 틀린 경우 로그인창으로 이동하기 
	else if(vo.getMsg().equals("NOPWD")){
%>
		<script>
    	alert("비밀번호가 틀립니다");
    	history.back();
    	</script>
<% 		
	}
	// id 존재하고 pwd 맞는 경우 => id,name,admin여부를 서버에 저장하고 main.jsp로 이동 (session)
	else{
		session.setAttribute("id", vo.getId());
		session.setAttribute("name", vo.getName());
		session.setAttribute("admin", vo.getAdmin());
		
	// 사용하고있는 모든 JSP에서 세션에 등록된 모든 데이터 사용이 가능
    response.sendRedirect("../main/main.jsp");
	}
%>