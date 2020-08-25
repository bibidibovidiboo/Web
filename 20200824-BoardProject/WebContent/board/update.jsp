<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.sist.dao.*"%>
 <%
 	// 사용자가 보낸 게시물 번호 받기
 	String no=request.getParameter("no");
 	BoardDAO dao=new BoardDAO();
 	BoardVO vo=dao.boardUpdateData(Integer.parseInt(no));
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="table.css"> 
</head>
<body>
	<%--
		submit => action에 등록된 파일로 입력한 데이터 전송
		= <input> <textarea> <select>
	 --%>
	<center>
		<h1>수정하기</h1>
		<form method=post action=update_ok.jsp>
		<table class="table_content" width=500>
			<tr>
				<th width=15% align="right">이름</th>
				<td width=85%>
					<input type=text name=name size=15 required value=<%=vo.getName() %>>
					<input type=hidden name=no value=<%=no %>>
				</th>
			</tr>
			<tr>
				<th width=15% align="right">제목</th>
				<td width=85%>
					<input type=text name=subject size=45 required value="<%=vo.getSubject()%>">
				</th>
			</tr>
			<tr>
				<th width=15% align="right">내용</th>
				<td width=85%>
					<textarea rows="7" name="content" cols="55" required><%=vo.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<th width=15% align="right">비밀번호</th>
				<td width=85%>
					<input type=password name=pwd size=10 required>
				</th>
			</tr>
			<tr>
				<td colspan="2" align=center>
					<input type=submit value="수정">
					<input type=button value="취소" onclick="javascript:history.back()">
				</td>
			</tr>			
		</table>
		</form>
	</center>

</body>
</html>