package com.sist.dao;
//XML�Ľ��Ŀ� => ����� ����� �޴� ��ġ=>DAO���� �޼ҵ� ȣ�� 
import java.io.*;//XML���� �б� => Reader (�ѱ�����)
import java.util.*;//List => ���� (ArrayList)
/*
*    IO (Input,Output) : ������ ����� 
*    ==============================
*      1. �޸� ����� 
*      2. ���� ����� 
*      3. ��Ʈ��ũ ����� 
*    ==============================
*    IO�� ���� (1byte,2byte)
*    1byte=> ����Ʈ ��Ʈ��(���� ���ε�,�ٿ�ε�) => ~InputStream,~OutputStream
*    2byte=> ���� ��Ʈ�� (�ѱ����Ե� ������ �б�) => ~Reader , ~Writer
*/

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class BoardDAO {
// XML�� �Ľ�(������ �б�) => ���� ������ ���� ==> SqlSesionFactory
private static SqlSessionFactory ssf;
// �ʱ�ȭ => XML�� �б�(�ڵ����� ����)
static 
{
	   try
	   {
		   // 1. XML���� �б�
		   Reader reader=Resources.getResourceAsReader("Config.xml");
		   // Config�� ��ϵǾ� �ִ� ��� XML���� �д´� 
		   // ���ϸ� ��ҹ��ڸ� �����Ѵ�
		   // 2. XML�� �Ľ� : �ʿ��� ������ �о� ���� (���̹�Ƽ�� ���̺귯���� �о� ����) => ������ �ϰ� ����Ѵ�
		   ssf=new SqlSessionFactoryBuilder().build(reader);
		   // �Ľ� => SAX(�б�����) => �±׸� �Ѱ��� �о�ͼ� �ʿ䵥���͸� �����ϴ� ���
		   /*
		    *    JAXP 
		    *      DOM :����,�б�,����,�߰� (�޸𸮿��� ����)
		    *           => �ӵ��� �ʴ� 
		    *      SAX : �б� => �Ϲ������� ��� ���̺귯���� SAX����� ����Ѵ�
		    *                   MyBatis,Spring...
		    *           => �ӵ��� ������ 
		    *    JAXB : ������ (������ �ڹٿ� ���� ä���ش�)
		    *      binding
		    */
	   }catch(Exception ex)
	   {
		   // ����ó�� 
		   System.out.println(ex.getMessage());
	   }   
}
// ��� ��� ==> id="boardListData"
//            ������ : resultType            //�Ű����� : parameterType
// select ==> selectList() : row�� ������ �϶� ,  selectOne() : row�� �Ѱ��϶� 
// ����Ŭ==> ���� => row(record)
// <select id="boardListData" resultType="DataBoardVO" parameterType="hashmap">
public static List<BoardVO> boardListData(Map map)
{
	   List<BoardVO> list=new ArrayList<BoardVO>();
	   // Connection�� ����� ������ �ݵ�� ��ȯ ==> SqlSession
	   SqlSession session=null;
	   // SqlSession => Connection�� ������ ���� ���� 
	   // ������ ����ó���� ���� => �ʹݿ� ����ó���ϱ� ���ؼ� => ����ó���� �ϴ� ���� ���� => null
	   try
	   {
		   // ���� : ������� : try���� => finally����
		   //      ������  : �߰��� catch���� => finally����
		   // 1. �̸� ���� Connection��ü�� ��� �´� 
		   session=ssf.openSession();
		   // 2. XML�� �ִ� SQL������ �����Ŀ� ������� �޶�
		   list=session.selectList("freeboardListData",map);
	   }catch(Exception ex)
	   {
		   ex.printStackTrace();//�����ϴ� ������ �����ش� 
	   }
	   finally
	   {
		   // ��ȯ => Connection=>close()
		   if(session!=null)
			   session.close(); 
		   /*
		    *   close()
		    *   {
		    *     if(ps!=null) ps.close();
		    *     if(conn!=null) conn.close(); ==> disConnection()
		    *   }
		    */
	   }
	   return list;
  }
}