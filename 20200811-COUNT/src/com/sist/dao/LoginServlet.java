// doget - 화면 보여주는 곳 
// dopost - 요청 내용 처리하는 곳
package com.sist.dao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR"); 
		// 브라우저에 HTML을 전송 => 한글을 포함하고 있다
		// HTML 제작
		PrintWriter out=response.getWriter();
		// response => 응답 (브라우저전송) , request => 사용자가 보낸 데이터 요청
		out.println("<html>");
		/*
		 *  <html>
		 *  	<head>
		 *  	=> <style>
		 *  	=> <script> =>  이벤트
		 *  	</head>
		 *  	<body>
		 *  	</body>
		 *  </html>
		 *  
		 *  태그 => 지정된 태그만 사용 가능
		 *  태그는 대소문자를 구분하지 않는다 (약속:태그는 소문자로 사용)
		 * 
		 */
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>Login</h1>");
		out.println("<form method=post action=LoginServlet>"); // doPost 호출 , default => doget
		out.println("<table width=250>");
		// 아이디
		out.println("<tr>");
		out.println("<td width=15% aling=right>이름</td>");
		out.println("<td width=85%>");
		out.println("<input type=text name=ename size=17>");
		out.println("</td>");
		out.println("</tr>");
		// 비밀번호
		out.println("<tr>");
		out.println("<td width=15% aling=right>사번</td>");
		out.println("<td width=85%>");
		out.println("<input type=password name=empno size=17>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td align=center colspan=2>"); // td태그 두개를 묶기 => colspan 가로 , lowspan 세로 
		out.println("<input type=submit value=로그인>");
		
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</form>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청처리
		response.setContentType("text/html;charset=EUC_KR");
		PrintWriter out=response.getWriter(); // out이 나오면 브라우저에 출력 ★
		String ename=request.getParameter("ename");
		String empno=request.getParameter("empno");
		MyDAO dao=new MyDAO();
		String result=dao.isLogin(ename.toUpperCase(), Integer.parseInt(empno)); // empno는 int라서 parseInt 해줘야함
		
		// ↓ 확인용으로 콘솔에 출력해봄
		// System.out.println("이름:"+ename.toUpperCase()); // 대문자로 변환
		// System.out.println("사번:"+empno);
		
		if(result.equals("NONAME")) {
			out.println("<script>");
			out.println("alert(\"이름이 존재하지 않습니다\")");
			out.println("history.back()");
			// alert => 팝업창 
			out.println("</script>");
		}else if(result.equals("NOSABUN")) {
			out.println("<script>");
			out.println("alert(\"사번이 틀립니다\")");
			out.println("history.back()"); // 이전으로 이동
			out.println("</script>");
		}else {
//			out.println("<script>");
//			out.println("alert(\""+result+"님 메인으로 이동합니다\")");
//			out.println("</script>");
			response.sendRedirect("MusicServlet"); // 로그인시 화면 이동
		}
		
	}

}
