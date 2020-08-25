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
		public ArrayList<BoardVO> boardAllData(int page){
			ArrayList<BoardVO> list=new ArrayList<BoardVO>();
			try {
				getConnetion(); // 연결
				// SQL 문장 전송
				int rowSize=10; // 몇개가 한페이지인지
				// BETWEEN start AND end
				int start=(page*rowSize)-(rowSize-1);
				// rownum=>1
				/*
				 * 	1page = 1
				 *  2page = 11
				 *  3page = 21
				 */
				int end=page*rowSize; // 자바는 0부터 시작인데 오라클은 1부터 시작
				String sql="SELECT no,subject,name,regdate,hit,num "
						+"FROM (SELECT no,subject,name,regdate,hit, rownum as num "
						+"FROM (SELECT no,subject,name,regdate,hit "
						+"FROM jsp_board ORDER BY no DESC)) "
						+"WHERE num BETWEEN ? AND ?";
				ps=conn.prepareStatement(sql);
				// sql ? 값 받기 
				ps.setInt(1, start);
				ps.setInt(2, end);
				// 결과값 받기 
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					// vo 순서는 위에 sql 순서와 동일해야 한다 ★
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
		
		// 내용보기 
		public BoardVO boardDetailData(int no) {
			BoardVO vo=new BoardVO();
			try {
				getConnetion();
				// 조회수 증가 ↓
				String sql="UPDATE jsp_board SET "
						+"hit=hit+1 "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				// 실행
				ps.executeUpdate();
				
				// 데이터 읽기 
				sql="SELECT no,name,subject,content,regdate,hit "
						+"FROM jsp_board "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				
				// 결과값 받기
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
		
		// 새글 등록
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
				
				// 실행
				ps.executeUpdate();
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			
			
		}
		
		// 수정하기 
		public BoardVO boardUpdateData(int no) {
			BoardVO vo=new BoardVO();
			try {
				getConnetion();
				// 수정하기는 조회수 증가 X 
				String sql="SELECT no,name,subject,content,regdate,hit "
						+"FROM jsp_board "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, no);
				
				// 결과값 받기
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
		// 수정 
		public boolean boardUpdate(BoardVO vo) {
			// 비밀번호가 맞아야 수정 
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
				
				// 비밀번호가 맞으면 수정 , 아니면 수정 X
				if(db_pwd.equals(vo.getPwd())) {
					bCheck=true;
					// 수정
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
