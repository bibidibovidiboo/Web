// 지니뮤직 - 페이지 나누기 
package com.sist.dao;
import java.sql.*;
import java.util.*;
public class MyDAO {
		// 오라클 연결
		private Connection conn;
		// SQL문장을 오라클로 전송
		private PreparedStatement ps;
		// 오라클 주소
		private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
		// 드라이버 설치
		public MyDAO() {
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
			}catch (Exception ex) {
			}
		}
		// 해제
		public void disConnetion() {
			try {
				if(ps!=null) ps.close();
				if(conn!=null) conn.close();
			}catch (Exception ex) {}	
		}
		// JDBC (원시소스) => DBCP => ORM (MyBatis,Hibernate,JPA)
		public String isLogin(String ename,int empno) {
			String result="";
			try {
				getConnetion();
				// SQL 문장 전송
				String sql="SELECT COUNT(*) FROM emp "
						+"WHERE ename=?";
				ps=conn.prepareStatement(sql);
				// ?에 값을 채운다
				ps.setString(1, ename);
				ResultSet rs=ps.executeQuery();
				rs.next();
				int count=rs.getInt(1); // 0,1 로그인처리 => count ★ 
				rs.close();
				if(count==0) { // 사원이름 존재하지 않음
					 result="NONAME";
				}else { // 사원이름 존재
					sql="SELECT empno FROM emp "
						+"WHERE ename=?"; // 여기서 =? 이거 없으면 값이 안넘어감 조심 ★
					ps=conn.prepareStatement(sql);
					ps.setString(1, ename);
					rs=ps.executeQuery();
					rs.next(); // 데이터 있는 위치에 커서를 변경
					int db_empno=rs.getInt(1); // 데이터형 int라 getint로 받음
					rs.close();
					
					if(empno==db_empno) { // 로그인 되는 상태
						result=ename;
					}else { // 사번이 틀린 상태 
						result="NOSABUN";
					}
					
				}
			}catch (Exception ex) {
				// 에러처리
				System.out.println(ex.getMessage());
			}finally {
				// 닫기
				disConnetion();
			}
			return result;
			
		}	
		
}
