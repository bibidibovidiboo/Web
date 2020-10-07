package com.sist.dao;
import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class RecipeDAO {
   // XML�� �Ľ� 
   private static SqlSessionFactory ssf;
   static 
   {
	   try
	   {
		   // XML�� �б� ���� 
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   ssf=new SqlSessionFactoryBuilder().build(reader);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   // ��� ó��  ==> jstl + el  ==> �����÷���Ʈ ==> ����
   // 1. ������ ���
   public static List<RecipeVO> recipeListData(Map map)
   {
	   List<RecipeVO> list=new ArrayList<RecipeVO>();
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();
		   // ������ ó��
		   list=session.selectList("recipeListData",map);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return list;
   }
   // �������� ���ϱ� 
   public static int recipeTotalPage()
   {
	   int total=0;
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();
		   total=session.selectOne("recipeTotalPage");
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return total;
   }
   // 2. chef ���
   public static List<ChefVO> chefListData(Map map)
   {
	   List<ChefVO> list=new ArrayList<ChefVO>();
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();
		   // ������ ó��
		   list=session.selectList("chefListData",map);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return list;
   }
   // �������� ���ϱ� 
   public static int chefTotalPage()
   {
	   int total=0;
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();
		   total=session.selectOne("chefTotalPage");
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return total;
   }
   // 3. chef => ������ 
   /*
    *   <select id="chefRecipeData" resultType="RecipeVO" parameterType="string">
		    SELECT no,title,poster,chef 
		    FROM recipe
		    WHERE chef=#{chef}
		  </select>
    */
   public static List<RecipeVO> chefRecipeData(String chef)
   {
	   List<RecipeVO> list=new ArrayList<RecipeVO>();
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();
		   // ������ ó��
		   list=session.selectList("chefRecipeData",chef);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return list;
   }

   // ���� �׷���
   public static List<ChefVO> chefRecipeCount()
   {
	   List<ChefVO> list=new ArrayList<ChefVO>();
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();
		   // ������ ó��
		   list=session.selectList("chefRecipeCount");
		   for(ChefVO vo:list)
		   {
			   String s=vo.getMem_cont1().replace(",", "");
			   vo.setRecipeCount(Integer.parseInt(s));
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
	   return list;
   }
   // 4. �˻�
}