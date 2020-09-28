<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*,java.util.*"%>
 <%
 
 	String strPage= request.getParameter("page");
 	if(strPage==null)
 		strPage="1";
 	int curpage=Integer.parseInt(strPage);
 	
 	Map map = new HashMap();
    int rowSize=10;
    int start=(curpage*rowSize)-(rowSize-1);
    int end=curpage*rowSize;
    
 	map.put("start",start);
 	map.put("end",end);
 	
 	List<BoardVO> list = BoardDAO.freeBoardData(map);
 	
 	System.out.println("이름"+list.size());
 	int total = BoardDAO.freeboardTotalPage();
 	
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <div class="row">
     <h1 class="text-center">게시판</h1>
     <table class="table">
       <tr>
         <td>
           <a href="#" class="btn btn-sm btn-success">새글</a>
           <%-- 메뉴 , 로그인 , footer 고정 => main.jsp --%>
         </td>
       </tr>
     </table>
     <table class="table table-striped">
       <tr class="warning">
         <th class="text-center" width=10%>번호</th>
         <th class="text-center" width=45%>제목</th>
         <th class="text-center" width=15%>이름</th>
         <th class="text-center" width=20%>작성일</th>
         <th class="text-center" width=10%>조회수</th>
       </tr>
       <%
           /*
                           컬럼이 없는 값을 받는 경우 => VO에서 변수 설정 
               private String dbday; =>dbday값을 채운다 
               name,TO_CHAR(regdate,'YYYY-MM-DD') as dbday => mapper 
           */
           for(BoardVO vo:list)
           {
        	   	/*
        	   		../main/main.jsp?mode=3&no=1
        	   						========  detail.jsp에서 사용
        	   					화면 변경시 사용(main.jsp)
        	   		include가 되면 ==> 모든 JSP에서 사용자가 보내준 데이터를 사용이 가능
        	   		=============
        	   		   request가 공유
        	   	*/
       %>
               <tr>
		         <td class="text-center" width=10%><%=vo.getNo() %></td>
		         <td width=45%>
		         <a href="../main/main.jsp?mode=3&no=<%=vo.getNo()%>"><%=vo.getSubject() %></a>
		         <%-- include된 모든 JSP는 main.jsp로 보낸 모든 데이터값을 사용할 수 있다 
		         	main.jsp   => mode=3 => 화면 변경
		         	detail.jsp => no=1   => 1번에 해당되는 데이터를 받아서 출력
		         
		         --%>
		         </td>
		         <td class="text-center" width=15%><%=vo.getName() %></td>
		         <td class="text-center" width=20%><%=vo.getRegdate() %></td>
		         <td class="text-center" width=10%><%=vo.getHit() %></td>
		       </tr>
       <%
           }
       %>
     </table>
     <table class="table">	
       <tr>
         <td class="text-left">
         <!-- 
         	데이터 전송하는 방식은 GET POST 방식이 동일하다
         					둘다  변수명=값&변수명=값&변수명=값
         
          -->
          <form method="post" action="../main/main.jsp" name=frm>
	          Search:
	          <select name="fd" class="input-sm">
	            <option value="name">이름</option>
	            <option value="subject">제목</option>
	            <option value="content">내용</option>
	          </select>
	          <%-- 검색어 입력 --%>
	          <input type=text name=ss class="input-sm">
	          <input type=hidden name=mode value=4>
	          <%-- 검색버튼 --%>
	          <input type=button value=검색 class="btn btn-sm btn-danger"
	            onclick="send()"
	          >
          </form>
         </td>
       </tr>
     </table>
   </div>
</body>
</html>

