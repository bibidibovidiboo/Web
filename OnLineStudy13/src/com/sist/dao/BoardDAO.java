package com.sist.dao;
import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class BoardDAO {
   // XML���� �б�
   private static SqlSessionFactory ssf;
   // �ڵ� �ʱ�ȭ => ���
   static 
   {
	   try
	   {
		   // ���� �б�
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   // XML �Ľ� => �ʿ��� �����͸� �����Ѵ� 
		   ssf=new SqlSessionFactoryBuilder().build(reader);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   // ��� �б� 
   // <select id="boardListData" resultType="BoardVO" parameterType="hashmap">
   public static List<BoardVO> boardListData(Map map)
   {
	   List<BoardVO> list=new ArrayList<BoardVO>();//list=> ���� ä���
	   // Connection => Sqlession
	   SqlSession session=null;
	   try
	   {
		   // ���� ��ü 
		   session=ssf.openSession();
		   // SQL���� ���� ��û
		   list=session.selectList("boardListData",map);
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
   /*
    *     <update id="hitIncrement" parameterType="int">
		    UPDATE freeboard SET
		    hit=hit+1
		    WHERE no=#{no}
		  </update>
		  <select id="boardDetailData" resultType="BoardVO" parameterType="int">
		    SELECT no,name,subject,content,regdate,hit 
		    FROM freeboard
		    WHERE no=#{no}
		  </select>
    */
   //          resultType              parameterType
   public static BoardVO boardDetailData(int no)
   {
	   /*
	    *   resultType => ������    ==> 
	    *   List<BoardVO> ==========> selectList
	    *   BoardVO       ==========> selectOne
	    *   parameterType => �Ű�����
	    *   => ��� : subquery , PL/SQL , Index
	    *   => MVC 
	    *   => JavaScript (Ajax)
	    */
	   BoardVO vo=new BoardVO();
	   // Connection => SqlSession
	   /*
	    *   �ڹ� (JDBC) => autoCommit
	    *   UPDATE,DELETE,INSERT ==> commit
	    *   =====================
	    *   
	    *   session=ssf.openSession(true) => INSERT,UPDATE,DELETE
	    *   
	    *   session=ssf.openSession(); => UPDATE,SELECT
	    *   �������
	    *   session.commit()
	    */
	   SqlSession session=null;
	   try
	   {
		   // ���� �õ�
		   session=ssf.openSession();
		   // ��ȸ�� ���� 
		   session.update("hitIncrement", no);
		   session.commit();
		   
		   // ���� ������ 
		   vo=session.selectOne("boardDetailData", no);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // Connection ����Ŀ� ��ȯ  ==> DBCP
		   /*
		    *    JDBC 
		    *    1. ��û�ϸ� ���� , ó���Ŀ� ���� => UNPOOLED => ����ð��� ���� �Ҹ��Ѵ�
		    *                                            =================
		    *    DBCP 
		    *    1. �̸� ����Ŭ���� => ��ü (POOL�� ����) => ó���� ������ �ݵ�� POOL�ȿ� ��ȯ(����)
		    *    2. Connection�� ����  ==> POOLED
		    */
		   if(session!=null)
			    session.close();
	   }
	   return vo;
   }
   // �������� ���ϱ� 
   /*
    *     <select id="boardTotalPage" resultType="int">
		    SELECT CEIL(COUNT(*)/10.0) FROM freeboard
		  </select>
    */
   public static int boardTotalPage()
   {
	   int total=0;
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();
		   // ������ �о� ���� 
		   total=session.selectOne("boardTotalPage");// ��ҹ��� ���� 
	   }catch(Exception ex)
	   {
		   // ���� ó��
		   ex.printStackTrace();
	   }
	   finally
	   {
		   // ��ȯ => ����
		   if(session!=null)
			   session.close();
	   }
	   
	   return total;
   }
   // ������ �߰�
   /*
    *   <insert id="boardInsert" parameterType="BoardVO">
		   INSERT INTO freeboard(no,name,email,subject,content,pwd)
		   VALUES(
		     (SELECT NVL(MAX(no)+1,1) FROM freeboard),
		     #{name},
		     ' ',
		     #{subject},
		     #{content},
		     #{pwd}
		   )
		  </insert>
    */
   public static void boardInsert(BoardVO vo)
   {
	   SqlSession session=null;
	   try
	   {
		   session=ssf.openSession(true);
		   session.insert("boardInsert",vo);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close();
	   }
   }
   // ������ ������ �б� 
   public static BoardVO boardUpdateData(int no)
   {
	   BoardVO vo=new BoardVO();
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();
		   vo=session.selectOne("boardDetailData", no);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();// ���� 
	   }
	   finally
	   {
		   // ��ȯ 
		   if(session!=null)
			   session.close();
	   }
	   return vo;
   }
   // ����
   public static boolean boardUpdate(BoardVO vo) {
	   boolean bCheck=false;
	   SqlSession session=null;
	   try {
		   // ���� 
		   session=ssf.openSession();
		   // ��й�ȣ �˻�
		   String db_pwd=session.selectOne("boardGetPassword",vo.getNo());
		   if(db_pwd.equals(vo.getPwd())) // ���ο��� Ȯ�� => ����
		   {
			   bCheck=true;
			   // ���� ������ �Ѵ�
			   session.update("boardupdate",vo);
			   session.commit(); // �̰� ���� ���� �ȵ�
		   }else {
			   
		   }
	   }catch (Exception ex) {
		   ex.printStackTrace();
	   }finally {
		   if(session!=null)
			   session.close();
	   }
	   return bCheck;
   }
   // �����ϱ� 
   public static boolean boardDelete(int no,String pwd) {
	   boolean bCheck=false;
	   SqlSession session=null;
	   try {
		   // ���� 
		   session=ssf.openSession();
		   // ��й�ȣ �˻�
		   String db_pwd=session.selectOne("boardGetPassword", no);
		   if(db_pwd.equals(pwd)) // ����
		   {
			   bCheck=true;
			   // ���� ������ ���� 
			   session.delete("boardDelete",no);
			   session.commit();
		   }else // ��й�ȣ�� Ʋ����
		   {
			   bCheck=false;
		   }
	   }catch (Exception ex) {
		   ex.printStackTrace();
	   }finally {
		   if(session!=null)
			   session.close();
	   }
	   return bCheck;
   }
}

