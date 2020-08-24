package com.sist.dao;
/*
 * 	��� , ���� , ����ó��
 * 	����ó��(����Ŭ ����)
 *  
 * 	����Ŭ ���� => ArrayList => add() , get() , size()
 * 	=> ��� => for - each
 */
import java.sql.*; // Connection , PreparedStatement , ResultSet
import java.util.*; // ArrayList
public class MusicDAO {
	// ����Ŭ ����
	private Connection conn;
	// SQL������ ����Ŭ�� ����
	private PreparedStatement ps;
	// ����Ŭ �ּ�
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// 1. ���� ����Ŭ �������ִ� ����̹� => oracle.jdbc.driver.OracleDriver
	public MusicDAO() {
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
	
	// DAO (JDBC => �⺻ ����)
	// ��� => MusicTop200
	public ArrayList<MusicVO> musicAllData(){
		ArrayList<MusicVO> list=new ArrayList<MusicVO>();
		try {
			getConnetion(); // ����
			// SQL ���� ����
			String sql="SELECT mno,title,singer,album,poster "
					+"FROM genie_music";
			ps=conn.prepareStatement(sql);
			// ����� �ޱ� 
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				// vo ������ ���� sql ������ �����ؾ� �Ѵ� ��
				MusicVO vo=new MusicVO();
				vo.setMno(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setSinger(rs.getString(3));
				vo.setAlbum(rs.getString(4));
				vo.setPoster(rs.getString(5));
				
				list.add(vo);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			disConnetion();
		}
		return list;
		
		
	}
	// ��ȭ => ���
	public ArrayList<MovieVO> movieAllData(){
		ArrayList<MovieVO> list=new ArrayList<MovieVO>();
		try {
			getConnetion(); // ����
			// SQL ���� ����
			String sql="SELECT no,title,poster,regdate,genre,actor,director "
					+"FROM daum_movie "
					+"WHERE cateno=1 ORDER BY no";
			ps=conn.prepareStatement(sql);
			// ����� �ޱ� 
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				// vo ������ ���� sql ������ �����ؾ� �Ѵ� ��
				MovieVO vo=new MovieVO();
				vo.setNo(rs.getInt(1));
				vo.setTitle(rs.getString(2));
				vo.setPoster(rs.getString(3));
				vo.setRegdate(rs.getString(4));
				vo.setGenre(rs.getString(5));
				vo.setActor(rs.getString(6));
				vo.setDirector(rs.getString(7));	
				
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