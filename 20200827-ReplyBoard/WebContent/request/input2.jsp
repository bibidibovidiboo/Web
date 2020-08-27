<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<h1>POST로 데이터 보내기</h1>
		<%--
			<from method=post> , ajax 
			
			url 뒤를 보여주면 get / 감추면 post
		 --%>
		 <form table=post action="output2.jsp">
			 <table border=2 width=400>
			 	<tr>
			 		<td width=30% align=right>이름</td>
			 		<td width=70% align=left>
			 			<input type=text name=name size=15><%-- name=admin --%>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td width=30% align=right>성별</td>
			 		<td width=70% align=left>
			 			<input type=radio value="남자" name=sex checked>남자
			 			<input type=radio value="여자" name=sex>여자
			 			<%-- name을 같이 줘야 하나만 체크할 수 있다 --%>
			 		</td>
			 	</tr>
			 	<tr>
			 		<%--
			 			<select name="fd">
							<option name=name>이름</option>
							<option name=subject>제목</option>
							<option name=content>내용</option>
						</select>		 		
			 		 --%>
			 		<td width=30% align=right>지역</td>
			 		<td width=70% align=left>
			 			<select name="loc">
							<option>서울</option>
							<option>부산</option>
							<option>인천</option>
							<option>경기</option>
							<option>강원</option>
						</select>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td width=30% align=right>취미</td>
			 		<td width=70% align=left>
			 			<input type=checkbox value="등산" name=hobby>등산
			 			<input type=checkbox value="낚시" name=hobby>낚시
			 			<input type=checkbox value="게임" name=hobby>게임
			 			<input type=checkbox value="독서" name=hobby>독서
			 			<input type=checkbox value="여행" name=hobby>여행
			 	
			 	</tr>
			 	<tr>
			 		<td width=30% align=right>소개</td>
			 		<td width=70% align=left>
			 			<textarea rows=7 cols=30 name=content></textarea>
			 		</td>
			 	</tr>
			 	<tr>
			 		<td colspan=2 align=center>
						<button>전송</button>
						<%--
							<input type=submit>
							<button>
							<input type=image>
						 --%>
			 		</td>
			 	</tr>				 			 	
		 	</table>
		</form>
	</center>
</body>
</html>