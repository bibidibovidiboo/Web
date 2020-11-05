package com.sist.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.sist.controller.RequestMapping;
import com.sist.dao.MemberDAO;
import com.sist.dao.ShopDAO;
import com.sist.vo.MemberVO;
import com.sist.vo.ShopVO;
/**
 * @author 이보미
 *
 */
public class MainModel {
  @RequestMapping("main/main.do")
  public String main_page(HttpServletRequest request)
  {
	  HttpSession session=request.getSession();
	  String id=(String)session.getAttribute("id");
	  
	  List<MemberVO> list1 = new ArrayList<MemberVO>();
	  List<MemberVO> list2 = new ArrayList<MemberVO>();
	  if(id==null)
	  {
		  // 로그인 안했을 때
	  }
	  else 
	  {
		  list1 = MemberDAO.memberRcList1(id);
		  list2 = MemberDAO.memberRcList2(id);
		  
		  // 쿠키처리 
		  Cookie[] cookies=request.getCookies();
		  List<ShopVO> cList=new ArrayList<ShopVO>();
		  if(cookies!=null)
		  {
		  	for(int i=cookies.length-1;i>=0;i--)
		  	{
		  		cookies[i].setPath("/");
		  		if(cookies[i].getName().startsWith(id))
		  		{
		  			String shop_no=cookies[i].getValue();
		  			ShopVO vo=ShopDAO.shopDetailData(Integer.parseInt(shop_no));
		  			cList.add(vo);
		  		}
		  	}
		  }
		  request.setAttribute("cList", cList);		  
	  }
	  
	  // 랜덤처리 
	  Collections.shuffle(list1);
	  Collections.shuffle(list2);
	  
	  request.setAttribute("list1", list1);
	  request.setAttribute("list2", list2);
	  request.setAttribute("main_jsp", "../main/home.jsp");
	  return "../main/main.jsp";
  }
}