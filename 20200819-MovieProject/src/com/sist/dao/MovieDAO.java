package com.sist.dao;
import java.sql.*;
import java.util.*;

import com.sist.manager.MovieVO;
import com.sist.manager.NewsVO;
public class MovieDAO {
		// 오라클 연결
		private Connection conn;
		// SQL문장을 오라클로 전송
		private PreparedStatement ps;
		// 오라클 주소
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		// 드라이버 설치
		public MovieDAO() {
			// 생성자 => 멤버변수의 초기화 , 네트워크 서버 연결
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		// 연결
		public void getConnetion() {
			try {
				conn=DriverManager.getConnection(URL,"hr","happy");
			}catch (Exception ex) {}
		}
		// 해제
		public void disConnetion() {
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch (Exception ex) {}	
		}
		// 기능
		// 1. 저장 => INSERT , UPDATE , DELETE => 결과값이 없다 (void)
		public void movieInsert(MovieVO vo) {
			try {
				getConnetion();
				String sql="INSERT INTO daum_movie VALUES("
						+"(SELECT NVL(MAX(no)+1,1) FROM daum_movie),?,?,?,?,?,?,?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				// ?에 값을 채운다
				ps.setInt(1, vo.getCateno());
				ps.setString(2, vo.getTitle());
				ps.setString(3, vo.getPoster());
				ps.setString(4, vo.getRegdate());
				ps.setString(5, vo.getGenre());
				ps.setString(6, vo.getGrade());
				ps.setString(7, vo.getActor());
				ps.setString(8, vo.getScore());
				ps.setString(9, vo.getDirector());
				ps.setString(10, vo.getStory());
				ps.setString(11, vo.getKey());
				 
				// 실행 명령
				ps.executeUpdate();
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
			/*finally {
				disConnetion();
			}
			*/
		}
		public void newsInsert(NewsVO vo) {
			try {
				getConnetion();
				String sql="INSERT INTO daum_news VALUES("
						+"?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				// ?에 값을 채운다
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getPoster());
				ps.setString(3, vo.getLink());
				ps.setString(4, vo.getContent());
				ps.setString(5, vo.getAuthor());
				 
				// 실행 명령
				ps.executeUpdate();
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		// ArrayList
		// 영화
		public ArrayList<MovieVO> movieListData(int cno){
			ArrayList<MovieVO> list=new ArrayList<MovieVO>();
			try {
				// 연결
				getConnetion();
				// SQL문장
				String sql="SELECT poster,title,no FROM daum_movie "
						+"WHERE cateno=? ORDER BY no";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, cno);
				// 전송
				
				// 실행 후에 데이터를 받는다
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					MovieVO vo=new MovieVO();
					vo.setPoster(rs.getString(1));
					vo.setTitle(rs.getString(2));
					vo.setNo(rs.getInt(3));
					
					// ArrayList에 첨부
					list.add(vo);
				}
				rs.close();
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			return list;
		}
		
		// 영화뉴스
		public ArrayList<NewsVO> newsListData(){
			ArrayList<NewsVO> list=new ArrayList<NewsVO>();
			try {
				// 연결
				getConnetion();
				// SQL문장
				String sql="SELECT poster,title,link,content,author "
						+"FROM daum_news";
				ps=conn.prepareStatement(sql);
				// 전송				
				// 실행 후에 데이터를 받는다
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					NewsVO vo=new NewsVO();
					vo.setPoster(rs.getString(1));
					vo.setTitle(rs.getString(2));
					vo.setLink(rs.getString(3));
					vo.setContent(rs.getString(4));
					vo.setAuthor(rs.getString(5));
					
					// ArrayList에 첨부
					list.add(vo);
				}
				rs.close();
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			return list;
		}
		
		// 영화 상세보기
		public MovieVO movieDetailData(int no) {
			MovieVO vo=new MovieVO();
			try {
				// 연결
				getConnetion();
				// SQL 전송
				String sql="SELECT * FROM daum_movie "
						+"WHERE no=?";
				ps=conn.prepareStatement(sql);
				// 실행요청 하기 전에 ?에 값을 채운다
				ps.setInt(1, no);
				// 결과값 받기
				ResultSet rs=ps.executeQuery(); // 실행
				rs.next(); // 커서이동 (데이터가 출력된 위치)
				
				vo.setNo(rs.getInt(1));
				vo.setCateno(rs.getInt(2));
				vo.setTitle(rs.getString(3));
				vo.setPoster(rs.getString(4));
				vo.setRegdate(rs.getString(5));
				vo.setGenre(rs.getString(6));
				vo.setGrade(rs.getString(7));
				vo.setActor(rs.getString(8));
				vo.setScore(rs.getString(9));
				vo.setDirector(rs.getString(10));
				vo.setStory(rs.getString(11));
				vo.setKey(rs.getString(12));
				rs.close();
				
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			return vo;
		}
		
		// 댓글 관련 => INSERT , UPDATE , DELETE 
		public ArrayList<ReplyVO> movieReplyData(int mno){
			ArrayList<ReplyVO> list=new ArrayList<ReplyVO>();
			try {
				getConnetion();
				// 시간은 TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS' => 이렇게 변환해서 써줘야함
				String sql="SELECT no,mno,id,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') "
						+"FROM daum_reply "
						+"WHERE mno=? "
						+"ORDER BY no DESC"; // 최신순으로 출력
				ps=conn.prepareStatement(sql);
				ps.setInt(1, mno);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					ReplyVO vo=new ReplyVO();
					vo.setMno(rs.getInt(1));
					vo.setMno(rs.getInt(2));
					vo.setId(rs.getString(3));
					vo.setMsg(rs.getString(4));
					vo.setDbday(rs.getString(5));
					list.add(vo);
				}
				rs.close();
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}finally {
				disConnetion();
			}
			return list;
		}
				
			
}
