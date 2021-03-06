package com.sist.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.sist.manager.MusicVO;
public class MusicDAO {
	        // 연결 
			private Connection conn;
			// SQL 전송
			private PreparedStatement ps;
			// 오라클 주소 
			private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
			// 1. 실제 오라클 연결해주는 드라이버 oracle.jdbc.driver.OracleDriver
			public MusicDAO()
			{
				try
				{
					Class.forName("oracle.jdbc.driver.OracleDriver");
					// 드라이버를 이용해서 연결 => thin드라이버 
				}catch(Exception ex) {}
			}
			// 2. 연결 메소드
			public void getConnection()
			{
				try
				{
					conn=DriverManager.getConnection(URL,"hr","happy");
					// conn hr/happy
				}catch(Exception ex) {}
			}
			// 3. 닫는 메소드 
			public void disConnection()
			{
				try
				{
					if(ps!=null) ps.close();
					if(conn!=null) conn.close();
					// exit 
				}catch(Exception ex) {}
			}
			// 기능
			public void musicInsert(MusicVO vo)
			{
				try
				{
					// 연결
					getConnection();
					String sql="INSERT INTO music VALUES("
							+"music_mno_seq.nextval,?,?,?,?,?)";
					ps=conn.prepareStatement(sql);
					// ?에 값을 채운다
					ps.setInt(1, vo.getCateno());
					ps.setString(2, vo.getTitle());
					ps.setString(3, vo.getPoster());
					ps.setString(4, vo.getSinger());
					ps.setString(5, vo.getAlbum());
					// 실행명령
					ps.executeUpdate(); // INSERT 문장을 실행 => COMMIT
					
					
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{
					disConnection();
				}
			}
			
			// 장르 
			public ArrayList<String> musicGenreAllData(){
				ArrayList<String> list=new ArrayList<String>();
				try {
					getConnection();
					String sql="SELECT genre FROM music_genre "
								+"ORDER BY no";
					ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						String genre=rs.getString(1);
						list.add(genre);
					}
					rs.close();
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}
				return list;
			}
			/*
			 *  subquery
			 *  
			 *  SELECT ename,(SELECT~) => (컬럼대신) 스칼라 서브쿼리
			 *  FROM (SELECT~~) => 인라인뷰
			 *  WHERE sal=(SELECT) => 단일행 서브쿼리 , 다중행 서브쿼리 
			 *  ==> 다중 컬럼 서브쿼리
			 *  INSERT INTO table+name VALUES((SELECT NVL(MAX(no)+1,1),?,?,?)
			 *  JOIN => SELECT만 사용이 가능 
			 *  SUBQUERY => DML 전체 
			 * 
			 */
			//  페이지 나누기
			public ArrayList<MusicVO> musicAllDate(int cateno,int page){
				ArrayList<MusicVO> list=new ArrayList<MusicVO>();
				try {
					getConnection();
					String sql="SELECT mno,title,poster,singer,album,RANK() OVER(ORDER BY mno ASC),num "
							+"FROM (SELECT mno,title,poster,singer,album,rownum as num "
							+"FROM (SELECT mno,title,poster,singer,album "
							+"FROM music WHERE cateno=? ORDER BY mno))"
							+"WHERE num BETWEEN ? AND ?"; // 페이징기법
					int rowSize=30;
					int start=(rowSize*page)-(rowSize-1);
					// rownum ==> 시작번호 (1)
					int end=rowSize*page;
					
					ps=conn.prepareStatement(sql);
					ps.setInt(1, cateno);
					ps.setInt(2, start);
					ps.setInt(3, end);
					
					// 실행
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						MusicVO vo=new MusicVO();
						vo.setMno(rs.getInt(1));
						vo.setTitle(rs.getString(2));
						vo.setPoster(rs.getString(3));
						vo.setSinger(rs.getString(4));
						vo.setAlbum(rs.getString(5));
						vo.setRank(rs.getInt(6));
						
						list.add(vo);
					}
					rs.close();
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}
				return list;
			}
			
			// 장르
			public String musicGetGenre(int cateno)
			{
				String genre="";
				try
				{
					getConnection();
					String sql="SELECT genre FROM music_genre "
							  +"WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, cateno);
					ResultSet rs=ps.executeQuery();
					rs.next();
					genre=rs.getString(1);
					rs.close();
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{
					disConnection();
				}
				return genre;
			}
}


