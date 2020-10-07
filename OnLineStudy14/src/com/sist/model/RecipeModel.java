package com.sist.model;
import com.sist.dao.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
public class RecipeModel {
    public void recipeListData(HttpServletRequest request)
    {
    	// request => ������� ��û ���� , �ʿ��� �����͸� ÷���ؼ� ��� (setAttribute()) ==> JSP�� ���� 
    	// ����ڰ� ��û�� �������� �޴´� 
    	String page=request.getParameter("page");
    	if(page==null)
    		page="1";
    	
    	// ������ �б� 
    	int curpage=Integer.parseInt(page); // ���� ���� �ִ� ������
    	Map map=new HashMap();
    	int rowSize=20;
    	int start=(rowSize*curpage)-(rowSize-1);
    	int end=rowSize*curpage;
    	
    	//map�� ��� ���� ==> mybatis�� �о ó��
    	map.put("start",start);
    	map.put("end", end);
    	List<RecipeVO> list=RecipeDAO.recipeListData(map);
    	for(RecipeVO vo:list)
    	{
    		String str=vo.getTitle();
    		if(str.length()>20)
    		{
    			str=str.substring(0,20);
    			str+="...";
    		}
    		vo.setTitle(str);
    	}
    	// �������� 
    	int totalpage=RecipeDAO.recipeTotalPage();
    	
    	// JSP�� ���� ������� ���� 
    	request.setAttribute("list", list);
    	request.setAttribute("curpage", curpage);
    	request.setAttribute("totalpage", totalpage);
    	
    }
    // �������� 
    public void chefListData(HttpServletRequest request)
    {
    	// ����� ��û���� �ޱ� => page
    	String page=request.getParameter("page");
    	if(page==null)
    		page="1";
    	// ���� ������ 
    	int curpage=Integer.parseInt(page);
    	// ���� ������ ����� ������ �б�
    	Map map=new HashMap();
    	int rowSize=50;
    	int start=(rowSize*curpage)-(rowSize-1);
    	int end=rowSize*curpage;
    	
    	map.put("start", start);
    	map.put("end",end);
    	List<ChefVO> list=RecipeDAO.chefListData(map);// ������ġ , ������ ��ġ
    	// �������� 
    	
    	int totalpage=RecipeDAO.chefTotalPage();
    	// curpage,totalpage,list => JSP
    	
    	request.setAttribute("list", list);
    	request.setAttribute("curpage", curpage);// ���� ������
    	request.setAttribute("totalpage", totalpage);// ��������
    	
    	List<ChefVO> cList=RecipeDAO.chefRecipeCount();
    	request.setAttribute("cList", cList);
    	
    	
    }
    // chef => ������ ��� ��� 
    public void chefRecipeData(HttpServletRequest request)
    {
    	// ����� ��û�� => chef���� �о� �´� 
    	String chef_name=request.getParameter("chef_name");
    	List<RecipeVO> list=RecipeDAO.chefRecipeData(chef_name);
    	// JSP�� ���� => list�� �ִ� ���븸 ��� 
    	request.setAttribute("list", list);
    }
}

