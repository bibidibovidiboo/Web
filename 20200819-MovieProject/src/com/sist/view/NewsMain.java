package com.sist.view;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sist.dao.*;
import com.sist.manager.*;
@WebServlet("/NewsMain")
public class NewsMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		PrintWriter out=response.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=container>");
		out.println("<h1 class=text-center>영화뉴스</h1>");
		out.println("<div class=row>");

		MovieDAO dao=new MovieDAO();
		ArrayList<NewsVO> list=dao.newsListData();
		for(NewsVO vo:list)
		{
			out.println("<table class=\"table table-hover\">");
			out.println("<tr>");
			out.println("<td rowspan=3 width=30%>");
			String poster=vo.getPoster();
			poster=poster.substring(0,poster.lastIndexOf(")")); // 뒤에 주소 잘라서 넣으려고 
			out.println("<img src="+poster+" width=350 height=250>");
			out.println("</td>");
			out.println("<td width=70%><b><font color=orange>"+vo.getTitle()+"</font></b></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width=70%>"+vo.getContent()+"</td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td width=70% class=text-right>"+vo.getAuthor()+"</td>");
			out.println("</tr>");
			out.println("</table>");
			}
		out.println("</div>");
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");
	}
}
