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
		public ArrayList<BoardVO> boardAllData(int page){
			ArrayList<BoardVO> list=new ArrayList<BoardVO>();
			try {
				getConnetion(); // ����
				// SQL ���� ����
				int rowSize=10; // ��� ������������
				// BETWEEN start AND end
				int start=(page*rowSize)-(rowSize-1);
				// rownum=>1
				/*
				 * 	1page = 1
				 *  2page = 11
				 *  3page = 21
				 */
				int end=page*rowSize; // �ڹٴ� 0���� �����ε� ����Ŭ�� 1���� ����
				String sql="SELECT no,subject,name,regdate,hit,num "
						+"FROM (SELECT no,subject,name,regdate,hit, rownum as num "
						+"FROM (SELECT no,subject,name,regdate,hit "
						+"FROM jsp_board ORDER BY no DESC)) "
						+"WHERE num BETWEEN ? AND ?";
				ps=conn.prepareStatement(sql);
				// sql ? �� �ޱ� 
				ps.setInt(1, start);
				ps.setInt(2, end);
				// ����� �ޱ� 
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					// vo ������ ���� sql ������ �����ؾ� �Ѵ� ��
					BoardVO vo=new BoardVO();
					vo.setNo(rs.getInt(1));
					vo.setSubject(rs.getString(2));
					vo.setName(rs.getString(3));
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
		// ��������
		public int boardTotalPage() {
			int total=0;
			try {
				getConnetion();
				String sql="SELECT CEIL(COUNT(*)/10.0) FROM jsp_board"; // �ø��Լ� (������)
				ps=conn.prepareStatement(sql);
				ResultSet rs=ps.executeQuery();
				rs.next();
				total=rs.getInt(1);
				rs.close();
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			return total;
		}
		
		// ���뺸�� 
		public BoardVO boardDetailData(int no) {
			BoardVO vo=new BoardVO();
			try {
				getConnetion();
				// ��ȸ�� ���� ��
				String sql="UPDATE jsp_board SET "
						+"hit=hit+1 "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				// ����
				ps.executeUpdate();
				
				// ������ �б� 
				sql="SELECT no,name,subject,content,regdate,hit "
						+"FROM jsp_board "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				
				// ����� �ޱ�
				ResultSet rs=ps.executeQuery();
				rs.next();
				
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegdate(rs.getDate(5));
				vo.setHit(rs.getInt(6));
				
				rs.close();
				
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			return vo;
		}
		
		// ���� ���
		public void boardInsert(BoardVO vo) {
			try {
				getConnetion();
				String sql="INSERT INTO jsp_board VALUES("
						+"jb_no_seq.nextval,?,?,?,?,SYSDATE,0)";
				ps=conn.prepareStatement(sql);
				ps.setString(1, vo.getName());
				ps.setString(2, vo.getSubject());
				ps.setString(3, vo.getContent());
				ps.setString(4, vo.getPwd());
				
				// ����
				ps.executeUpdate();
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			
			
		}
		
		// �����ϱ� 
		public BoardVO boardUpdateData(int no) {
			BoardVO vo=new BoardVO();
			try {
				getConnetion();
				// �����ϱ�� ��ȸ�� ���� X 
				String sql="SELECT no,name,subject,content,regdate,hit "
						+"FROM jsp_board "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				
				// ����� �ޱ�
				ResultSet rs=ps.executeQuery();
				rs.next();
				
				vo.setNo(rs.getInt(1));
				vo.setName(rs.getString(2));
				vo.setSubject(rs.getString(3));
				vo.setContent(rs.getString(4));
				vo.setRegdate(rs.getDate(5));
				vo.setHit(rs.getInt(6));
				
				rs.close();
				
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			return vo;
		}
		// ���� 
		public boolean boardUpdate(BoardVO vo) {
			// ��й�ȣ�� �¾ƾ� ���� 
			boolean bCheck=false;
			try {
				getConnetion();
				String sql="SELECT pwd FROM jsp_board "
						+ "WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, vo.getNo());
				ResultSet rs=ps.executeQuery();
				rs.next();
				String db_pwd=rs.getString(1);
				rs.close();
				
				// ��й�ȣ�� ������ ���� , �ƴϸ� ���� X
				if(db_pwd.equals(vo.getPwd())) {
					bCheck=true;
					// ����
					sql="UPDATE jsp_board SET "
						+"name=?,subject=?,content=? "
						+"WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setString(1, vo.getName());
					ps.setString(2, vo.getSubject());
					ps.setString(3, vo.getContent());
					ps.setInt(4, vo.getNo());
					
					ps.executeUpdate();
					
				}else {
					bCheck=false;
				}
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			return bCheck;
		}
	
		
}
