package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
/*
 *    public class CreateSqlSessionFactory
 *    {
 *          private static SqlSessionFactory ssf;
			static 
			{
				try
				{
					Reader reader=Resources.getResourceAsReader("Config.xml");
					ssf=new SqlSessionFactoryBuilder().build(reader);
				}catch(Exception ex)
				{
					ex.printStackTrace();
				}
			} 
			public static SqlSessionFactory getSsf()
			{
			    return ssf;
			}
 *    }
 *    
 *    
 *    ���� ==> �׼� �±� : �ڹٹ����� �±������� ���� 
 *    3���� ���
 *    1) <jsp:include page="÷���� JSP���ϸ�">
 *    2) <jsp:useBean id="dao" class="MemberDAO">
 *       ============================ �޸� �Ҵ�
 *         => �ڹ��ڵ� 
 *            MemberDAO dao=new MemberDAO()
 *                     ====
 *                       id  ==> id�� ��ü���� �ȴ�
 *    3) <jsp:setProperty name="��ü��" property="">
 *                        =========== ============
 *                        name: => id��Ī 
 *                        property : ������  ==> setXxx()�� ���� ä���ش�
 *        ��)
 *           JSP => Bean , MyBatis => DTO , Spring => VO
 *           Bean = DTO = VO�� ������ ���� 
 *             => ��ȭ �Ѱ��� ��ü���� => �������� �Ѱ��� ���� 
 *             => ����� �ΰ��� 
 *                �б� / ���� 
 *                getter / setter
 *           public class MemberVO
 *           {
 *               private int no;
 *               private String name;
 *               // ���� => �޸𸮿� ����
 *               public void setNo(int no)
 *               {
 *                  this.no=no;
 *               }
 *               // �б� => ����� �� �б�
 *               public int getNo()
 *               {
 *                   return no;
 *               }
 *               public void setName(String name)
 *               {
 *                   this.name=name;
 *               }
 *               public void getName()
 *               {
 *                  return name;
 *               }
 *           }
 *           
 *           VO => �޸𸮿� Ŭ������ ���� 
 *           MemberVO vo=new MemberVO();
 *           => <jsp:useBean id="vo" class="MemberVO">
 *              useBean : �޸� �Ҵ� 
 *           => ��Ŭ�� 
 *           => HTML�� �ڹ��ڵ��� �и� 
 *              <c:forEach> <c:if> <c:forTokenes> : JSTL
 *              <% %> => ���� 
 *           vo.setName("ȫ�浿"),vo.setNo(1)
 *           => <jsp:setProperty name="vo" property="no" value="1">
 *              vo.setNo(1)
 *                             ==========
 *              <jsp:setProperty name="vo" property="name" value="ȫ�浿">
 *              vo.setName("ȫ�浿")
 *              
 *              setProperty => setXxx()�� ���� ä���ش�
 *              
 *           => <jsp:setProperty name="vo" property="*">
 *              ��� setXxx()�� ���۵� ���� ä����
 */
public class BoardDAO{
	private static SqlSessionFactory ssf;
	/*
	 *   ���̹�Ƽ�� , ������ : ���� XML�� �б� ������ XML�� Ʋ����쿡�� 
	 *   ������ �ȵȴ�
	 *   => id�� ���� ��쿡�� ������ ���� 
	 *   => id => �տ� ���̺�� 
	 *   databoardTotalPage
	 *   freeboardTotalPage
	 */
	static 
	{
		try
		{
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	// <select id="freeBoardListData" resultType="com.sist.dao.BoardVO" parameterType="hashmap">
	public static List<BoardVO> freeBoardListData(Map map)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		SqlSession session=null;
		try
		{
			session=ssf.openSession();
			list=session.selectList("freeBoardListData",map);
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
	// <insert id="freeBoardInsert" parameterType="com.sist.dao.BoardVO">
	public static void freeBoardInsert(BoardVO vo)
	{
		SqlSession session=null;
		try
		{
			// ���� ��ü (Connection)=> SqlSession => getConnection
			// ������ => �и� , �ߺ����� => annotation
			session=ssf.openSession(true);//insert => commit (autoCommit)
			session.insert("freeBoardInsert", vo);
		}catch(Exception ex)
		{
			 ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close(); //connection�ݱ�(��ȯ)
		}
	}
	// �󼼺��� 
	// <update id="freeBoardHitIncrement" parameterType="int">
	// <select id="freeBoardDetailData" resultType="com.sist.dao.BoardVO" parameterType="int">
	public static BoardVO freeBoardDetailData(int no)
	{
		BoardVO vo=new BoardVO();
		SqlSession session=null;
		// ���� => ó�� 
		try
		{
			// ���� ��ü ��� (session=>Connection,PreparedStatement)
			session=ssf.openSession();
			session.update("freeBoardHitIncrement", no);
			// commit�� ����
			session.commit();
			/*
			 *   ���� �ټ� 
			 *     ������ => selectList
			 *     ���� => selectOne
			 */
			vo=session.selectOne("freeBoardDetailData", no);
			// ȭ�� ��� 
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			// �ݱ�
			if(session!=null)
				session.close();
		}
		
		return vo;
	}
	// ������ �����͸� �о� �´� : board-mapper.xml�� �̹� ������� SQL������ �ִ� ���=������ ����
	// <select id="freeBoardDetailData" resultType="com.sist.dao.BoardVO" parameterType="int">
	public static BoardVO freeBoardUpdateData(int no)
	{
		BoardVO vo=new BoardVO();
		SqlSession session=null;
		try
		{
			// ����
			session=ssf.openSession();// commit(X)
			vo=session.selectOne("freeBoardDetailData", no);
		}catch(Exception ex)
		{
			// ���� ó��
			ex.printStackTrace();
		}
		finally
		{
			// �ݱ�
			if(session!=null) // connection�� ����Ǿ��ٸ�
                session.close();
		}
		return vo;
	}
	// <select id="freeBoardGetPassword" resultType="String" parameterType="int">
	// <update id="freeBoardUpdate" parameterType="com.sist.dao.BoardVO">
	public static boolean freeBoardUpdate(BoardVO vo)
	{
		boolean bCheck=false;
		// ��й�ȣ (O) : true , (X):false
		// => detail.jsp  => update.jsp
		SqlSession session=null;
		try
		{
			// ������ Connection��ü�� ��� �´� 
			session=ssf.openSession();
			// SQL���� ���� ��û 
			// 1. ��й�ȣ�� ������ �´� 
			String db_pwd=session.selectOne("freeBoardGetPassword", vo.getNo());// no => <input type=hidden no>
		    if(db_pwd.equals(vo.getPwd()))
		    {
		    	// ��й�ȣ�� ��ġ
		    	bCheck=true;
		    	// ���� ���� 
		    	session.update("freeBoardUpdate", vo);
		    	// commit
		    	session.commit();
		    }
		    else
		    {
		    	// ��й�ȣ ����ġ
		    	bCheck=false;
		    }
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			if(session!=null)
				session.close(); // Connection�� ����Ǿ� �ִٸ� 
		}
		return bCheck;
	}
}

