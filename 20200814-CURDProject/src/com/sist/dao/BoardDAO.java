package com.sist.dao;
import java.util.*;
import java.sql.*;
public class BoardDAO {
	// ����Ŭ ����
	private Connection conn;
	// SQL������ ����Ŭ�� ����
	private PreparedStatement ps;
	// ����Ŭ �ּ�
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// ����̹� ��ġ
	public BoardDAO() {
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
		}catch (Exception ex) {}
	}
	// ����
	public void disConnetion() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch (Exception ex) {}	
	}
	// ���
	// 1. ��� (�Խ���) => SELECT
	public ArrayList<BoardVO> boardListData(){
		ArrayList<BoardVO> list=new ArrayList<BoardVO>();
		try {
			// ����
			getConnetion();
			// SQL ���� ����
			String sql="SELECT no,subject,name,regdate,hit "
					+"FROM freeboard "
					+"ORDER BY no DESC"; // �ֽ� ��ϵ� �Խù� ���� ���
					// ORDER BY => ���� (�ӵ��� �ʴ�) => INDEX
			ps=conn.prepareStatement(sql);
			// SQL ���� �Ŀ� ����� �ޱ�
			ResultSet rs=ps.executeQuery();
			// ������� ArrayList�� ÷��
			while(rs.next()) { // ����� ù��° �ٿ��� ������ �ٱ��� �о�´�
				BoardVO vo=new BoardVO();
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				
				list.add(vo);	
			}
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnetion();
		}
		return list;
	}
	// 2. ���뺸�� => SELECT (WHERE) ?no=1
	public BoardVO boardDetail(int no) {
		BoardVO vo=new BoardVO();
		try {
			//  ����
			getConnetion();
			// SQL ���� ���� => ��ȸ�� ����
			String sql="UPDATE freeboard SET "
					+"hit=hit+1 "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no); // ����ǥ�� ���� ä��� 
			// ����
			ps.executeUpdate();
			
			// ���� �� �����͸� ������ �´� 
			sql="SELECT no,name,subject,content,regdate,hit "
					+"FROM freeboard "
					+"WHERE no=?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, no);
			
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
	
	// 3. �۾��� => INSERT
	public void boardInsert(BoardVO vo) {
		try {
			getConnetion();
			// ����
			// SQL���� ���� => ����
			String sql="INSERT INTO freeboard(no,name,subject,content,pwd) "
					+"VALUES((SELECT NVL(MAX(no)+1,1) FROM freeboard),?,?,?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, vo.getName());
			ps.setString(2, vo.getSubject());
			ps.setString(3, vo.getContent());
			ps.setString(4, vo.getPwd());
			
			ps.executeUpdate(); // COMMIT ���� => INSERT , UPDATE , DELETE
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}finally {
			disConnetion();
		}
	}

	// 4. �ۼ��� => UPDATE
	// 5. �ۻ��� => DELETE
	// 6. ã�� => SELECT (LIKE)
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
