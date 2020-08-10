package com.sist.dao;
import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/MusicDetail")
public class MusicDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// MusicDetail?mno=10
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR"); // html을 보내겠다 설정
		// 값을 받는다
		String mno=request.getParameter("mno");
		MusicDAO dao=new MusicDAO();
		MusicVO vo=dao.musicDetailData(Integer.parseInt(mno));
		
		PrintWriter out=response.getWriter(); // out(메모리)에 쓰면 사용자에게 가져와라(브라우저 입력-출력)
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>&lt;"+vo.getTitle()+"&gt; 상세보기</hl>"); // < => &lt , > => &gt
		
		out.println("<table width=700>");
		out.println("<tr>");
		out.println("<td>");
		// 동영상 <iframe> => 유튜브
		// 일반동영상 => <video>
		out.print("<iframe src=http://youtube.com/embed/"+vo.getKey()+" width=700 height=400></iframe>");
		out.println("</td>");
		out.println("</tr>");
		out.println("</table>");
		
		out.println("<table width=700>");
		out.println("<tr>");
		out.println("<td width=45% rowspan=4>"); // 4줄 추가
		out.println("<img src="+vo.getPoster()+" width=100%>");
		out.println("</td>");
		
		out.println("<td>");	
		out.println(vo.getTitle());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>");
		out.println(vo.getSinger());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>");
		out.println(vo.getAlbum());
		out.println("</td>");
		out.println("</tr>");
		
		out.println("<tr>");
		out.println("<td>");
		out.println("<a href=MusicServlet>목록</a>");
		out.println("</td>");
		out.println("</tr>");
		
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
		
			
	}

}
