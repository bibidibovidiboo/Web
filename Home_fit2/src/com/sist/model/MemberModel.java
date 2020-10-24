package com.sist.model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;
import com.sist.vo.MemberVO;
/* 
 * @author 이보미
 * 
 */
public class MemberModel {
	   @RequestMapping("member/login.do")
	   public String member_login(HttpServletRequest request)
	   {
		   HttpSession session=request.getSession();
		   session.invalidate();
		   return "../member/login.jsp";
	   }
	}
