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
		
		public ArrayList<MovieVO> movieListData(int cno){
			ArrayList<MovieVO> list=new ArrayList<MovieVO>();
			try {
				// 연결
				getConnetion();
				// SQL문장
				String sql="SELECT poster,title,no FROM daum_movie "
						+"WHERE cateno=?";
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
			
}
