<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
   List<String> list=new ArrayList<String>();
   list.add("홍길동");
   list.add("심청이");
   list.add("박문수");
   List<Integer> list2=new ArrayList<Integer>();
   list2.add(30);
   list2.add(20);
   list2.add(25);
%>
<c:set var="names" value="<%=list %>"/>
<c:set var="ages" value="<%=list2 %>"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <c:forEach var="name" items="${names }" varStatus="s">
   <!-- varStatus => index번호 -->
      ${s.index}:${name }(${ages[s.index] })
   </c:forEach>
</body>
</html>