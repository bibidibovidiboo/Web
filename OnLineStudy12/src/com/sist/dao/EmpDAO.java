package com.sist.dao;
import java.sql.*;
import java.util.*;
public class EmpDAO {
   private Connection conn;
   private PreparedStatement ps;
   private final String URL="jdbc:oracle:thin:@211.238.142.183:1521:XE";
   // ����̹� ���
   public EmpDAO()
   {
	   try
	   {
		   Class.forName("oracle.jdbc.driver.OracleDriver");// ��ҹ��� ���� 
	   }catch(Exception ex)
	   {
		   System.out.println(ex.getMessage());
	   }
   }
   // ����Ŭ ����
   public void getConnection()
   {
	   try
	   {
		   conn=DriverManager.getConnection(URL,"hr","happy");
	   }catch(Exception ex) {}
   }
   // ����Ŭ ���� ����
   public void disConnection()
   {
	   try
	   {
		   if(ps!=null)
			   ps.close();
		   if(conn!=null)
			   conn.close();
	   }catch(Exception ex) {}
   }
   // Mybatis => Config.xml => Connection
   // ������ ó�� => emp-mapper.xml�� ���� (SQL) => PreparedStatement
   /*
    *   Mybatis 
    *   ======= XML,Annotation
    */
   /*
    *     ���� ��� 
    *     List (interface) => �ڽ��� ������ �Ұ��� => �������̽��� �����ϰ� �ִ� Ŭ������ �̿��ؼ� �޸� �Ҵ�    
    *     ====          
    *      |  
    *    ArrayList(�񵿱� => �����ͺ��̽� �����)   List<EmpVO> list=new ArrayList<EmpVO>();
    *    Vector(����ȭ => ��Ʈ��ũ ����� ����)     List<EmpVO> list=new Vector<EmpVO>();
    *    LinkedList(C ���� ȣȯ)             List<EmpVO> list=new LinkedList<EmpVO>();
    *    => ������ ���� 
    *    => �ߺ��� �����͸� ���� 
    *    
    *    Set (interface)
    *    ===
    *     |
    *    TreeSet
    *    HashSet
    *    => ������ ����
    *    => �ߺ��� �����͸� ����� �� ���� (���󵵰� ����)
    *    
    *    Map (interface)
    *    ===
    *     |
    *    HashMap => Hashtable�� ������ ���� 
    *    Hashtable 
    *    => Ű�� ���� ������ ���� 
    *    => Ű�� �ߺ��� ���� ���� �ߺ��� ���� 
    *    => Ŭ������ �̸� �޸��Ҵ��� �ϰ� Ű�� �̿��ؼ� �Ҵ�� �ּҸ� ã�Ƽ� ��� (Spring)
    *    => ������ ���Ǵ� request,response,session,cookie ==> Map����� �̿� (Ű,��)
    *    
    *    ===============> Collection Framework : �����͸� ��Ƽ� ó���ϱ� ���� ���� (�ڷᱸ��)
    *                     = ���̺귯�� => ǥ��ȭ
    */
   public List<EmpVO> empListData()
   {
	   List<EmpVO> list=new ArrayList<EmpVO>();// ������ �ʿ� , �����͸� �ߺ� 
	   // ����ó�� => ������ ���Ḧ �����ϴ� ���α׷� (���� �߻��� => ȸ��,����)
	   try
	   {
		   // ������๮�� 
		   // ����
		   getConnection();
		   // SQL���� ���� 
		   String sql="SELECT empno,ename,job,TO_CHAR(hiredate,'YYYY-MM-DD'),sal,dname,loc "
				     +"FROM emp,dept "
				     +"WHERE emp.deptno=dept.deptno";
		   ps=conn.prepareStatement(sql);
		   // �����Ŀ� ����� �б�
		   ResultSet rs=ps.executeQuery();
		   while(rs.next())
		   {
			   EmpVO vo=new EmpVO();
			   vo.setEmpno(rs.getInt(1));
			   vo.setEname(rs.getString(2));
			   vo.setJob(rs.getString(3));
			   vo.setDbday(rs.getString(4));
			   vo.setSal(rs.getInt(5));
			   vo.setDname(rs.getString(6));
			   vo.setLoc(rs.getString(7));
			   
			   // ��ü => list�� ÷��
			   list.add(vo);
		   }
		   rs.close();
	   }catch(Exception ex)
	   {
		   //�����߻��� => ���� 
		   System.out.println(ex.getMessage());//���� Ȯ�� => ���α׷��� ������ ���� 
	   }
	   finally
	   {
		   // ����,�����ͺ��̽� �ݴ� ��쿡 ���(������ ���๮��)
		   disConnection();
	   }
	   return list;
   }
}
