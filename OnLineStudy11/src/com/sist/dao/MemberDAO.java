package com.sist.dao;
import java.io.*;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class MemberDAO {
   // XML�� �о �����͸� �����ϴ� ��ü
   private static SqlSessionFactory ssf;
   static 
   {
	   try
	   {
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   ssf=new SqlSessionFactoryBuilder().build(reader);
		   // getConnection,disConnection
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
   }
   
   // ��� ó��
   // 1. loginó�� => session => 1.id,2.name,3.admin
   // <select id="memberIdCheck" resultType="int" parameterType="String">
   // <select id="memberGetInfoData" resultType="com.sist.dao.MemberVO" parameterType="String">
   /*
    *     �Խ��� / ���  => PL/SQL
    *     Spring���� ������Ʈ 
    *     JSP : Spring�� ���� 
    */
   public static MemberVO memberLogin(String id,String pwd)
   {
	   MemberVO vo=new MemberVO();
	   // ���� 
	   SqlSession session=null;
	   try
	   {
		   // ����
		   session=ssf.openSession();
		   // ����Ŭ�� SQL���� ���� 
		   int count=session.selectOne("memberIdCheck",id);
		   if(count==0) // ID�� ���� ����
		   {
			   vo.setMsg("NOID");
		   }
		   else // ID�� �����ϴ� ����
		   {
			   // ��й�ȣ �˻�
			   vo=session.selectOne("memberGetInfoData", id);
			   if(pwd.equals(vo.getPwd()))// �α���
			   {
				   vo.setMsg("OK");
			   }
			   else // ��й�ȣ�� Ʋ�� ����
			   {
				   vo.setMsg("NOPWD");
			   }
		   }
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	   finally
	   {
		   if(session!=null)
			   session.close(); // ���� => ��ȯ (POOL) => false(�ٽ� ���)
		   /*
		    *   jdbc/oracle
		    *   ==========================
		    *    new Connection() false ==> true (close=> false)
		    *   ==========================
		    *    new Connection() false
		    *   ==========================
		    *    new Connection() false
		    *   ==========================
		    *   
		    *   _jspInit() : ȯ�� ���� (web.xml)
		    *   _jspService() : ����� ��û�� ���� ����
		    *   _jspDestory() : �޸� ���� => GC()
		    */
	   }
	   return vo;
   }
   
}