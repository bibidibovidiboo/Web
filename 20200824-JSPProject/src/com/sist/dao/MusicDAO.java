package com.sist.dao;
/*
 * 	제어문 , 변수 , 예외처리
 * 	예외처리(오라클 연결)
 *  
 * 	오라클 연결 => ArrayList => add() , get() , size()
 * 	=> 출력 => for - each
 */
import java.sql.*; // Connection , PreparedStatement , ResultSet
import java.util.*; // ArrayList
public class MusicDAO {
	// 오라클 연결
	private Connection conn;
	// SQL문장을 오라클로 전송
	private PreparedStatement ps;
	// 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// 1. 실제 오라클 연결해주는 드라이버 => oracle.jdbc.driver.OracleDriver
	public MusicDAO() {
		// 생성자 => 멤버변수의 초기화 , 네트워크 서버 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 드라이버를 이용해서 연결 => thin 드라이버
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	// 2. 연결 메소드
	public void getConnetion() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception ex) {}
	}
	// 3. 닫는 메소드
	public void disConnetion() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch (Exception ex) {}	
	}
	
	// DAO (JDBC => 기본 셋팅)
	// 기능 => MusicTop200
	public ArrayList<MusicVO> musicAllData(){
		ArrayList<MusicVO> list=new ArrayList<MusicVO>();
		try {
			getConnetion(); // 연결
			// SQL 문장 전송
			String sql="SELECT mno,title,singer,album,poster "
					+"FROM genie_music";
			ps=conn.prepareStatement(sql);
			// 결과값 받기 
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				// vo 순서는 위에 sql 순서와 동일해야 한다 ★
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
	// 영화 => 출력
	public ArrayList<MovieVO> movieAllData(int page){
		ArrayList<MovieVO> list=new ArrayList<MovieVO>();
		try {
			getConnetion(); // 연결
			// SQL 문장 전송
			int rowSize=10; // 몇개가 한페이지인지
			
			int start=(page*rowSize)-(rowSize-1);
			int end=page*rowSize; 
			
			String sql="SELECT no,title,poster,regdate,genre,actor,director,num "
					+"FROM (SELECT no,title,poster,regdate,genre,actor,director, rownum as num "
					+"FROM (SELECT no,title,poster,regdate,genre,actor,director "
					+"FROM daum_movie ORDER BY no)) "
					+"WHERE num BETWEEN ? AND ?";
			ps=conn.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, end);
			// 결과값 받기 
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				// vo 순서는 위에 sql 순서와 동일해야 한다 ★
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
	// 총페이지
	public int boardTotalPage() {
		int total=0;
		try {
			getConnetion();
			String sql="SELECT CEIL(COUNT(*)/10.0) FROM jsp_board"; // 올림함수 (페이지)
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
}