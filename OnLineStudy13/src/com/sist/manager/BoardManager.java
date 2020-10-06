package com.sist.manager;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.*;

public class BoardManager {
	public void boardListData(HttpServletRequest request) {
		// page�� �޴´�
		String page = request.getParameter("page");
		if (page == null)
			page = "1"; // JSP���� page�� ����(��ü)
		int curpage = Integer.parseInt(page);
		// ������ ������
		int rowSize = 10;
		int start = (curpage * rowSize) - (rowSize - 1);
		int end = curpage * rowSize;
		/*
		 * <select id="boardListData" resultType="BoardVO" parameterType="hashmap">
		 * SELECT no,subject,name,regdate,hit,num FROM (SELECT
		 * no,subject,name,regdate,hit,rownum as num FROM (SELECT
		 * no,subject,name,regdate,hit FROM freeboard ORDER BY no DESC)) WHERE num
		 * BETWEEN #{start} AND #{end} </select>
		 */
		// ����� �б�
		Map map = new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<BoardVO> list = BoardDAO.boardListData(map);
		request.setAttribute("list", list);// JSP���� request�� �޾Ƽ� JSTL,EL
		// M(Model) => �ڹ� (���(��û) ó��) , V(View) => JSP(HTML) => ��¸� �Ѵ�
		// �ڹ� <==> JSP(HTML) => MV���� ==> MVC // �ڹٿ� HTML�и� (�����͸� ���� ��� =>
		// request,session)
		// ������ ���� => session�� ����
		// �Ѱ��� JSP������ ��� => request�� ����
		// Front-End (React=>Redux, Vue => Vuex) => Flux
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String today = sdf.format(date);
		request.setAttribute("today", today);
		/*
		 * request.setAttribute() => JSP����� ������ request�� ��Ƽ� ���� => 1���� ����ϴ� ���� �ƴ϶� =>
		 * �ʿ��� ��� �����͸� ���� �� �ִ�
		 */
		int totalpage = BoardDAO.boardTotalPage();

		request.setAttribute("curpage", curpage);
		request.setAttribute("totalpage", totalpage);
	}

	// JSP���� ���� ����� ��û������ �޾Ƽ� ó�� => ������� (request�� ÷���ؼ� ����)
	public void boardDetailData(HttpServletRequest request) {
		// request => JSP���� ����
		// ��û�� �Խù��� ã�´� ==> no��
		// detail.jsp?no=10
		String no = request.getParameter("no");
		// ������ �б� ���� (�����ͺ��̽�)
		BoardVO vo = BoardDAO.boardDetailData(Integer.parseInt(no));
		// ������� request�� ��Ƽ� => JSP�� ����
		request.setAttribute("vo", vo);

	}

	// �Խù� �߰�
	public void boardInsert(HttpServletRequest request, HttpServletResponse response) {
		try {
			// �ѱ� ó��
			request.setCharacterEncoding("UTF-8");
			// ����� ��û�� �ޱ�
			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String pwd = request.getParameter("pwd");

			BoardVO vo = new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			// BoardDAO ==> ó�� (����Ŭ insert) => insert (SQL)
			BoardDAO.boardInsert(vo);
			// �̵� => list.jsp
			response.sendRedirect("list.jsp");
		} catch (Exception ex) {
		}
	}

	// ��� JSP���� ��û�� ���� ==> ó�����ִ� ���
	// ��ü���� ���α׷� : ���� (��ɺ� �и� ===> ����)
	// �ʼ� (����Ʈ) : �Խ��� (CURD), ȸ�� , ��������
	public void boardUpdateData(HttpServletRequest request) {
		// detail.jsp : update.jsp?no=${vo.no }
		String no = request.getParameter("no");
		BoardVO vo = BoardDAO.boardUpdateData(Integer.parseInt(no));
		// vo ==> update.jsp�� ����
		request.setAttribute("vo", vo);
	}

	// ���� �����ϱ�
	public void boardUpdate(HttpServletRequest request) {
		try {
			// �ѱ� ��ȯ => �����Ͽ���ó��
			request.setCharacterEncoding("UTF-8");
			// ����ڰ� ������ ������ �ޱ�
			String no = request.getParameter("no");
			String name = request.getParameter("name");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			String pwd = request.getParameter("pwd");
			// BoardVO�� ��� => BoardDAO�� ���� ==> ����Ŭ���� ����
			/*
			 * BoardManager : ����� ��û�� �޾Ƽ� ó�� BoardDAO : ����Ŭ ���� => ����Ŭ���� ��� ������ ���� BoardVO :
			 * �����͸� ��� �����ϴ� ���� ~.jsp : ����� ��¸� �ϴ� ����
			 */
			BoardVO vo = new BoardVO();
			vo.setName(name);
			vo.setSubject(subject);
			vo.setContent(content);
			vo.setPwd(pwd);
			vo.setNo(Integer.parseInt(no));
			// vo=>DAO�� ����

			boolean bCheck = BoardDAO.boardUpdate(vo);

			// ����� => update_ok.jsp�� ����
			request.setAttribute("bCheck", bCheck);// ó��(X) => javascript�� ������ �� ����
			// request�� ���ؼ� ���� �� �ִ� ������ => Object
			// Spring : RestController
			request.setAttribute("no", vo.getNo());
		} catch (Exception ex) {
		}
	}

	// ���� �ϱ�
	public void boardDelete(HttpServletRequest request) {
		// no,pwd�� �޴´�
		String no = request.getParameter("no");
		String pwd = request.getParameter("pwd");
		// no,pwd => BoardDAO ���� => �����ͺ��̽� ó�� (board-mapper.xml(SQL) ==> BoardDAO���� �о
		// ó��)
		boolean bCheck = BoardDAO.boardDelete(Integer.parseInt(no), pwd);
		// BoardDAO���� ������� �޾Ƽ�
		// delete_ok.jsp�� ���� ==> ȭ���̵�
		request.setAttribute("bCheck", bCheck);// �ڹ��ڵ� �ȿ����� JavaScript�� �ڵ� �� �� ����
		/*
		 * JavaScript => JSP,Servlet
		 * 
		 * JSP ==> ������ ���� (�ڹٴ� ���� �� ����) => �޼ҵ� (request)
		 * 
		 * JSP <======> Java �޼ҵ� ȣ�� =============================================�� URL��
		 * �̿��ؼ� ������ ���� JSP <=======> Servlet GET/POST ==> URL Aaa?no=10
		 */
	}
}