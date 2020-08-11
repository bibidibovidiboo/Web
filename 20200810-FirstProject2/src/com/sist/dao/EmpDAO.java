package com.sist.dao;
import java.util.*;
import java.sql.*;
public class EmpDAO {
	// ����Ŭ ����
	private Connection conn;
	// SQL������ ����Ŭ�� ����
	private PreparedStatement ps;
	// ����Ŭ �ּ�
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// ����̹� ��ġ
	public EmpDAO() {
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
	// SQL ���� ���� 
	public ArrayList<EmpVO> empAllData()
	{
		ArrayList<EmpVO> list=new ArrayList<EmpVO>();
		try {
			// ����Ŭ ����
			getConnetion();
			// SQL ���� ����
			String sql="SELECT empno,ename,job,mgr,hiredate,sal,comm,deptno "
					+"FROM emp "
					+"ORDER BY empno ASC"; // �̰� ������ ������� ��¾ȵ� 
			ps=conn.prepareStatement(sql); // executeQuery()
			// ����� �ޱ�
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EmpVO vo=new EmpVO();
				vo.setEmpno(rs.getInt(1)); // ������ �������� 
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setMgr(rs.getInt(4));
				vo.setHiredate(rs.getDate(5));
				vo.setSal(rs.getInt(6));
				vo.setComm(rs.getInt(7));
				vo.setDeptno(rs.getInt(8));
							
				list.add(vo);
			}
			rs.close();
			// ArrayList�� �� ä���
		}catch (Exception ex) {
			// Error�� �������� Ȯ��
			System.out.println(ex.getMessage());
		}finally {
			// ���� ����
			disConnetion();
		}
		return list;
	}
}