package com.sist.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
public class BoardDAO {
	// 오라클 연결
		private Connection conn;
		// SQL문장을 오라클로 전송
		private PreparedStatement ps;
		// 오라클 주소
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		// 1. 실제 오라클 연결해주는 드라이버 => oracle.jdbc.driver.OracleDriver
		public BoardDAO() {
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
		public ArrayList<BoardVO> boardAllData(){
			ArrayList<BoardVO> list=new ArrayList<BoardVO>();
			try {
				getConnetion(); // 연결
				// SQL 문장 전송
				String sql="SELECT no,name,subject,regdate,hit "
						+"FROM jsp_board";
				ps=conn.prepareStatement(sql);
				// 결과값 받기 
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					// vo 순서는 위에 sql 순서와 동일해야 한다 ★
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
