package com.sist.dao;
import java.util.*;
import java.sql.*;
public class EmpDAO {
	// 오라클 연결
	private Connection conn;
	// SQL문장을 오라클로 전송
	private PreparedStatement ps;
	// 오라클 주소
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	// 드라이버 설치
	public EmpDAO() {
		// 생성자 => 멤버변수의 초기화 , 네트워크 서버 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}
	// 연결
	public void getConnection() {
		try {
			conn=DriverManager.getConnection(URL,"hr","happy");
		}catch (Exception ex) {}
	}
	// 해제
	public void disConnection() {
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		}catch (Exception ex) {}	
	}
	// 기능
	 public ArrayList<EmpVO> empListData()
	  {
	      ArrayList<EmpVO> list=
	              new ArrayList<EmpVO>();
	      try
	      {
	          getConnection();
	          String sql="SELECT empno,ename,job,hiredate,sal "
	                    +"FROM emp";
	          // 전송
	          ps=conn.prepareStatement(sql);
	          // 실행 결과 값 받기
	          ResultSet rs=ps.executeQuery();
	          while(rs.next())
	          {
	              EmpVO vo=new EmpVO();
	              vo.setEmpno(rs.getInt(1));
	              vo.setEname(rs.getString(2));
	              vo.setJob(rs.getString(3));
	              vo.setHiredate(rs.getDate(4));
	              vo.setSal(rs.getInt(5));
	              
	              list.add(vo);
	          }
	          rs.close();
	      }catch(Exception ex) {
	          System.out.println(ex.getMessage());
	      }
	      finally
	      {
	          disConnection();
	      }
	      return list;
	  }
	 // 영화목록 가지고 오기
	 public ArrayList<String> movieGetPoster(){
		 ArrayList<String> list = new ArrayList<String>();
		 try {
			 // 연결
			 getConnection();
			 // SQL 문장 전송
			String sql="SELECT poster,rownum FROM daum_movie "
					+ "WHERE rownum<=10";
			// 오라클로 sql문장을 보내주는 상태
			ps=conn.prepareStatement(sql);
			// 실행 요청 => 데이터를 메모리에 저장 
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				list.add(rs.getNString(1));
				
			}
			rs.close();
		 }catch (Exception ex) {
			 System.out.println(ex.getMessage());
		 }finally {
			disConnection();
		}
		return list;
		 
	 }
	 public ArrayList<MovieVO> movieListData(){
		 ArrayList<MovieVO> list = new ArrayList<MovieVO>();
		 try {
			 // 연결
			 getConnection();
			 String sql="SELECT no,title,poster,director,actor,genre "
			 		+ "FROM daum_movie ORDER BY no ASC";
			 ps=conn.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()) {
				 MovieVO vo=new MovieVO();
				 vo.setMno(rs.getInt(1));
				 vo.setTitle(rs.getString(2));
				 vo.setPoster(rs.getString(3));
				 vo.setDirector(rs.getString(4));
				 vo.setActor(rs.getString(5));
				 vo.setGenre(rs.getString(6));
				 
				 list.add(vo);
			 }
			 rs.close();
		 }catch (Exception ex) {
			 System.out.println(ex.getMessage());
		 }finally {
			disConnection();
		 }return list;
		 
	 }
	 
	}