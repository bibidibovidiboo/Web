package com.sist.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class BoardDAO {
	// ����Ŭ ����
		private Connection conn;
		// SQL������ ����Ŭ�� ����
		private PreparedStatement ps;
		// ����Ŭ �ּ�
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		// 1. ���� ����Ŭ �������ִ� ����̹� => oracle.jdbc.driver.OracleDriver
		public BoardDAO() {
			// ������ => ��������� �ʱ�ȭ , ��Ʈ��ũ ���� ����
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				// ����̹��� �̿��ؼ� ���� => thin ����̹�
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		// 2. ���� �޼ҵ�
		public void getConnetion() {
			try {
				conn=DriverManager.getConnection(URL,"hr","happy");
			}catch (Exception ex) {}
		}
		// 3. �ݴ� �޼ҵ�
		public void disConnetion() {
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch (Exception ex) {}	
		}
		public ArrayList<BoardVO> boardAllData(){
			ArrayList<BoardVO> list=new ArrayList<BoardVO>();
			try {
				getConnetion(); // ����
				// SQL ���� ����
				String sql="SELECT no,name,subject,regdate,hit "
						+"FROM jsp_board";
				ps=conn.prepareStatement(sql);
				// ����� �ޱ� 
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					// vo ������ ���� sql ������ �����ؾ� �Ѵ� ��
					BoardVO vo=new BoardVO();
					vo.setNo(rs.getInt(1));
					vo.setName(rs.getString(2));
					vo.setSubject(rs.getString(3));
					vo.setRegdate(rs.getDate(4));
					vo.setHit(rs.getInt(5));
					
					list.add(vo);

				}
			}catch (Exception ex) {
				ex.printStackTrace();
			}finally {
				disConnetion();
			}
			return list;
			
			
		}
		
		
}
