package com.sist.dao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
@WebServlet("/MusicServlet")
public class MusicServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR"); // html �����ڴٴ� ����
		PrintWriter out=response.getWriter();
		// ������ �б�
		MusicDAO dao=new MusicDAO();
		ArrayList<MusicVO> list=dao.musicAllData();
		// s.getOutputStream()
		// ���������� �޸𸮿� ��µ� HTML�� �о� ����
		// html�� �Ȱ��� ���
		// ���̺� �� �κ�
		out.println("<html>");
		out.println("<body>");
		out.println("<center>");
		out.println("<h1>���� Top200</h1>");
		out.println("<table width=1200 border=1 bordercolor=black>");
		out.println("<tr>");
		out.println("<th>����</th>");
		out.println("<th></th>");
		out.println("<th>���</th>");
		out.println("<th>������</th>");
		out.println("<th>�ٹ�</th>");
		out.println("</tr>");
		
		// ���̺� �ȿ� ���� (for������)
		// for�� �Ⱦ��� �ϳ��ϳ� �� �Է��ؾ���
		for(MusicVO vo:list) {
			out.println("<tr>");
			out.println("<td>"+vo.getMno()+"</td>");
			out.println("<td><img src="+vo.getPoster()+" width=30 height=30></td>");
			out.println("<td><a href=MusicDetail?mno="+vo.getMno()+">"+vo.getTitle()+"</td>"); // a�±� �̿��ؼ� MusicDetail�� �̵�
			out.println("<td>"+vo.getSinger()+"</td>");
			out.println("<td>"+vo.getAlbum()+"</td>");
			out.println("</tr>");
		}		
		out.println("</table>");
		out.println("</center>");
		out.println("</body>");
		out.println("</html>");
	}

}