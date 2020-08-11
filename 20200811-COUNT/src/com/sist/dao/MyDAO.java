// ���Ϲ��� - ������ ������ 
package com.sist.dao;
import java.sql.*;
import java.util.*;
public class MyDAO {
		// ����Ŭ ����
		private Connection conn;
		// SQL������ ����Ŭ�� ����
		private PreparedStatement ps;
		// ����Ŭ �ּ�
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		// ����̹� ��ġ
		public MyDAO() {
			// ������ => ��������� �ʱ�ȭ , ��Ʈ��ũ ���� ����
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		// ����
		public void getConnetion() {
			try {
				conn=DriverManager.getConnection(URL,"hr","happy");
			}catch (Exception ex) {
			}
		}
		// ����
		public void disConnetion() {
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch (Exception ex) {}	
		}
		// JDBC (���üҽ�) => DBCP => ORM (MyBatis,Hibernate,JPA)
		public String isLogin(String ename,int empno) {
			String result="";
			try {
				getConnetion();
				// SQL ���� ����
				String sql="SELECT COUNT(*) FROM emp "
						+"WHERE ename=?";
				ps=conn.prepareStatement(sql);
				// ?�� ���� ä���
				ps.setString(1, ename);
				ResultSet rs=ps.executeQuery();
				rs.next();
				int count=rs.getInt(1); // 0,1 �α���ó�� => count �� 
				rs.close();
				if(count==0) { // ����̸� �������� ����
					 result="NONAME";
				}else { // ����̸� ����
					sql="SELECT empno FROM emp "
						+"WHERE ename=?"; // ���⼭ =? �̰� ������ ���� �ȳѾ ���� ��
					ps=conn.prepareStatement(sql);
					ps.setString(1, ename);
					rs=ps.executeQuery();
					rs.next(); // ������ �ִ� ��ġ�� Ŀ���� ����
					int db_empno=rs.getInt(1); // �������� int�� getint�� ����
					rs.close();
					
					if(empno==db_empno) { // �α��� �Ǵ� ����
						result=ename;
					}else { // ����� Ʋ�� ���� 
						result="NOSABUN";
					}
					
				}
			}catch (Exception ex) {
				// ����ó��
				System.out.println(ex.getMessage());
			}finally {
				// �ݱ�
				disConnetion();
			}
			return result;
			
		}	
		
}
