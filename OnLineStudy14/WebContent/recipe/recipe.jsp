<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.mode.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- RecipeModel을 메모리 할당  --%>
<jsp:useBean id="model" class="com.sist.mode.RecipeModel"/>
<%
      model.recipeListData(request); // 처리후에 결과값을 넘겨 받는다 
      /*
           ${}  ==> request.getAttribue(),session.getAtrribute() 
           === 일반 변수는 사용할 수 없다 
      */
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>만개의 레시피</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.row {
   margin: 0px auto; /* 가운데 정렬 */
}
h1 {
     text-align: center;
}
</style>
</head>
<body>
   <div class="container-fluid">
     <div class="row">
       <h1>만개의 레시피</h1>
         <c:forEach var="vo" items="${list }">
		   <div class="col-md-3">
		    <div class="thumbnail">
		        <img src="${vo.poster }" alt="Lights" style="width:100%">
		        <div class="caption">
		          <p>${vo.title }</p>
		          <p>${vo.chef }</p>
		        </div>
		    </div>
		   </div>
		 </c:forEach>
    </div>
    <div class="text-right">
    	<a href="recipe.jsp?page=${curpage>1?curpage-1:curpage }" class="btn btn-sm btn-info">이전</a>
    		${curpage } page / ${totalpage } pages
    	<a href="recipe.jsp?page=${curpage<totalpage?curpage+1:curpage }" class="btn btn-sm btn-success">다음</a>
    </div>
   </div>
</body>
</html>