<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.temp.*"%>

<%
	// 한글 변환
	request.setCharacterEncoding("UTF-8");


%>
<%--
	//한개의 클래스 묶어서 관리
	MemberBean bean = new MemberBean();
	
	bean.setName(name);
	bean.setSex(sex);
	bean.setAge(Integer.parseInt(age));
	bean.setAddr(addr);
	bean.setTel(tel);
	// DAO => 묶은 값을 전송
 --%>
<jsp:useBean id="bean" class="com.sist.temp.MemberBean">
	<%-- MemberBean bean = new MemberBean(); 
		<jsp:userBean> => 메모리 할당(new)
	--%>
	<jsp:setProperty name="bean" property="*"/>
	<%--
		getparameter 에서 읽어온 값을 자동으로 Bean에 set으로 넣어준다.
		setProperty name="bean"
			bean.setName(request.getParameter("name"));
			bean.setSex(request.getParameter("sex"));
			bean.setAge(Integer.parseInt(request.getParameter("Age")));
			bean.setAddr(request.getParameter("Addr"));
			bean.setTel(request.getParameter("Tel"));
		정수가 들어왔을때 자동으로 parse를 해준다.
		
		==> 한개의 값을 받는 경우
			============== 1.페이지,no값 => request를이용해서 받는것이 좋다
			
		==> 여러개의 값을 받는 경우(사용자가 보내주는 데이터가 많은경우)
			============== 2. vo를 받을때는 setProperty
	 --%>
</jsp:useBean>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>