<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>

<%
	//사용자의 요청 값을 받는다
	// String page(X) ==> page는 내장 객체 == > (page=this)
	String strPage  = request.getParameter("page");
	String mno=request.getParameter("mno");
	if(mno==null)
	{
		mno="0";
	}
	int index= Integer.parseInt(mno);
	//Default Page => page값을 지정
	if(strPage==null)
	{
		strPage="1";
	}
	
	//현재 페이지 지정
	int curpage=Integer.parseInt(strPage);
	// 현재  페이지에 해당되는 데이터를 DAO요청
	Map map = new HashMap(); // map => 저장 공간 => Key,실제 값
	int rowSize=12;
	int start=(rowSize*curpage)-(rowSize-1);
	int end = rowSize*curpage;
	
	map.put("start",start);
	map.put("end",end);
	List<MovieVO> list = new ArrayList<MovieVO>();
	if(index==0)
	{
		list=MovieDAO.movieListData(map); //전체 영화
	}
	else
	{
		list=MovieDAO.movieCategoryData(index);
	}
	// 제목에 있는 글자 수 제어
	// 총페이지 읽기
	int totalpage=MovieDAO.movieTotalPage();
	
	// Cookie 읽기
	List<String> cList=new ArrayList<String>();
	Cookie[] cookies=request.getCookies();
	/*
		new Cookie(키,값)
		m1 m2...
		==> 키를 읽어 올 때 => cookie.getName()
		==> 값을 읽어 올 때 => cookie.getValue()
		
	*/
	for(int i=0;i<cookies.length;i++){
		if(cookies[i].getName().startsWith("m")){
			cList.add(cookies[i].getValue());
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <%--
         btn-sm  : 중간
         btn-lg  : 큰버튼
         btn-xs  : 작은 버튼
    --%>
   <div class="row">
	 <a href="../main/main.jsp" class="btn btn-lg btn-active">전체 영화</a>
     <a href="../main/main.jsp?mno=1" class="btn btn-lg btn-primary">현재 상영 영화</a>
     <a href="../main/main.jsp?mno=2" class="btn btn-lg btn-success">개봉 예정 영화</a>
     <a href="../main/main.jsp?mno=3" class="btn btn-lg btn-info">박스오피스(주간)</a>
     <a href="../main/main.jsp?mno=4" class="btn btn-lg btn-danger">박스오피스(월간)</a>
     <a href="../main/main.jsp?mno=5" class="btn btn-lg btn-warning">박스오피스(연간)</a>
   </div>
   <div class="row">
	<%
		for(MovieVO vo:list)
		{
	%>
			  <!--  포스터 출력   -->
			  <div class="col-md-3">
			    <div class="thumbnail">
			      <a href="../movie/cookie.jsp?no=<%=vo.getNo()%>">
			      <!--  여기서 쿠키를 저장하고 이동해야함  -->
			        <img src="<%=vo.getPoster() %>" alt="Lights" style="width:100%">
			        <div class="caption">
			          <p style="font-size:8pt"><%=vo.getTitle() %></p>
			        </div>
			      </a>
			    </div>
			  </div>	
	<%
		}
	%>
   </div>
   <%
   		if(index==0)
   		{
   %>
   <div class="row">
   	<%-- 페이지 이동 --%>
   	<div class="text-center">
   		<a href="../main/main.jsp?mode=7&page=<%=curpage>1?curpage-1:curpage %>" class="btn btn-sm btn-success">이전</a>
   		<%= curpage %> page / <%=totalpage %> pages
   		<a href="../main/main.jsp?mode=7&page=<%=curpage<totalpage?curpage+1:curpage %>" class="btn btn-sm btn-primary">다음</a>  	
   	</div>
   </div>
 
   <%
  		}
   %>
   <!-- 쿠키전송 p.205~206 참조 -->
   <div class="row">
   	<h3>최근 방문한 영화 &nbsp;<a href="../movie/delete.jsp" class="btn btn-sm btn-primary">쿠키삭제</a></h3>
   	<%
   		if(cList==null || cList.size()<1)
   		{
   	%>
   			<font color=red><h1 class="text-center">방문 기록이 없습니다</h1></font>
   	<% 		
   		}
   		else{
   			for(String s:cList){
   	%>
   			<div class="col-md-2">
			    <div class="thumbnail">
			        <img src="<%=s %>" alt="Lights" style="width:100%">			     
			    </div>
			  </div>	
   	
   	<% 		
   			}
   		}
   	%>
   </div>
</body>
</html>
