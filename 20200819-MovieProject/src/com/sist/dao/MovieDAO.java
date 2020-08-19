package com.sist.dao;
import java.sql.*;
import java.util.*;

import com.sist.manager.MovieVO;
import com.sist.manager.NewsVO;
public class MovieDAO {
		// ����Ŭ ����
		private Connection conn;
		// SQL������ ����Ŭ�� ����
		private PreparedStatement ps;
		// ����Ŭ �ּ�
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		// ����̹� ��ġ
		public MovieDAO() {
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
		// 1. ���� => INSERT , UPDATE , DELETE => ������� ���� (void)
		public void movieInsert(MovieVO vo) {
			try {
				getConnetion();
				String sql="INSERT INTO daum_movie VALUES("
						+"(SELECT NVL(MAX(no)+1,1) FROM daum_movie),?,?,?,?,?,?,?,?,?,?,?)";
				ps=conn.prepareStatement(sql);
				// ?�� ���� ä���
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
				 
				// ���� ���
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
				// ?�� ���� ä���
				ps.setString(1, vo.getTitle());
				ps.setString(2, vo.getPoster());
				ps.setString(3, vo.getLink());
				ps.setString(4, vo.getContent());
				ps.setString(5, vo.getAuthor());
				 
				// ���� ���
				ps.executeUpdate();
			}catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
		
		public ArrayList<MovieVO> movieListData(int cno){
			ArrayList<MovieVO> list=new ArrayList<MovieVO>();
			try {
				// ����
				getConnetion();
				// SQL����
				String sql="SELECT poster,title,no FROM daum_movie "
						+"WHERE cateno=?";
				ps=conn.prepareStatement(sql);
				ps.setInt(1, cno);
				// ����
				
				// ���� �Ŀ� �����͸� �޴´�
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					MovieVO vo=new MovieVO();
					vo.setPoster(rs.getString(1));
					vo.setTitle(rs.getString(2));
					vo.setNo(rs.getInt(3));
					
					// ArrayList�� ÷��
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
				// ����
				getConnetion();
				// SQL����
				String sql="SELECT poster,title,link,content,author "
						+"FROM daum_news";
				ps=conn.prepareStatement(sql);
				// ����				
				// ���� �Ŀ� �����͸� �޴´�
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					NewsVO vo=new NewsVO();
					vo.setPoster(rs.getString(1));
					vo.setTitle(rs.getString(2));
					vo.setLink(rs.getString(3));
					vo.setContent(rs.getString(4));
					vo.setAuthor(rs.getString(5));
					
					// ArrayList�� ÷��
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
