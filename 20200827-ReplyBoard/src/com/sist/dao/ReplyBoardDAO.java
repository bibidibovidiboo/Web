package com.sist.dao;
import java.util.*;
import java.sql.*;
public class ReplyBoardDAO {
	        // 연결 
			private Connection conn;
			// SQL 전송
			private PreparedStatement ps;
			// 오라클 주소 
			private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
			// 1. 실제 오라클 연결해주는 드라이버 oracle.jdbc.driver.OracleDriver
			public ReplyBoardDAO()
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
			// 2001 => 19년전 코딩 ==> MVC/MyBatis
			// 1. 목록 출력 => 페이지 나누기 
			public ArrayList<ReplyBoardVO> boardListData(int page)
			{
				ArrayList<ReplyBoardVO> list=
						new ArrayList<ReplyBoardVO>();
				try
				{
					getConnection();
					String sql="SELECT no,subject,name,regdate,hit,group_tab,num "
							  +"FROM (SELECT no,subject,name,regdate,hit,group_tab,rownum as num "
							  +"FROM (SELECT no,subject,name,regdate,hit,group_tab "
							  +"FROM replyBoard ORDER BY group_id DESC,group_step ASC)) "
							  +"WHERE num BETWEEN ? AND ?";
							  /* 
							   *                         group_id  group_step
							   *        KKKKKK             3
							   *           =>              3
							   *        AAAAAA             2           0
							   *          ->BBBBB          2           1
							   *           ->CCCCCC        2           2
							   *          ->KKKKKK         2           3
							   *        DDDDDD             1           0
							   */
					ps=conn.prepareStatement(sql);
					int rowSize=10;
					int start=(rowSize*page)-(rowSize-1);
					int end=rowSize*page;
					
					// ? 에 값 채우기 
					ps.setInt(1, start);
					ps.setInt(2, end);
					
					// 실행후에 결과 값 읽기
					ResultSet rs=ps.executeQuery();
					while(rs.next())
					{
						ReplyBoardVO vo=new ReplyBoardVO();
						vo.setNo(rs.getInt(1));
						vo.setSubject(rs.getString(2));
						vo.setName(rs.getString(3));
						vo.setRegdate(rs.getDate(4));
						vo.setHit(rs.getInt(5));
						vo.setGroup_tab(rs.getInt(6));
						
						list.add(vo);
					}
					rs.close();
					
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{
					disConnection();
				}
				return list;
			}
			// 1-1 전체 갯수
			public int boardRowCount()
			{
				int count=0;
				try
				{
					getConnection();
					String sql="SELECT COUNT(*) FROM replyBoard";
					ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					rs.next();
					count=rs.getInt(1);
					rs.close();
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{
					disConnection();
				}
				return count;
			}
			// 2. 상세보기
			public ReplyBoardVO boardDetail(int no,int type) {
				ReplyBoardVO vo=new ReplyBoardVO();
				try {
					getConnection();
					// 조회수 
					String sql="";
					if(type==1) {
						sql="UPDATE replyBoard SET "
							+ "hit=hit+1 "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					ps.executeUpdate();
					}

					// 증가된 조회수 읽기 => sql 문장 한번더 날림
					sql="SELECT no,name,subject,content,regdate,hit "
							+ "FROM replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, no);
					// 결과값 
					ResultSet rs=ps.executeQuery();
					// 커서위치 변경 => 데이터 출력 위치에 커서변경
					rs.next();
					vo.setNo(rs.getInt(1));
					vo.setName(rs.getString(2));
					vo.setSubject(rs.getString(3));
					vo.setContent(rs.getString(4));
					vo.setRegdate(rs.getDate(5));
					vo.setHit(rs.getInt(6));
					
					rs.close();
				}catch(Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}
				return vo;
			}
			// 3. 새글 등록 => INSERT
			public void boardInsert(ReplyBoardVO vo)
			{
				try
				{
					getConnection();
					String sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id) " 
					         + "VALUES(rb_no_seq.nextval,?,?,?,?,"
							 +"(SELECT NVL(MAX(group_id)+1,1) FROM replyBoard))"; 
					ps=conn.prepareStatement(sql);
					ps.setString(1, vo.getName());
					ps.setString(2, vo.getSubject());
					ps.setString(3, vo.getContent());
					ps.setString(4, vo.getPwd());
					
					ps.executeUpdate();
				}catch(Exception ex)
				{
					System.out.println(ex.getMessage());
				}
				finally
				{
					disConnection();
				}
			}
			/*
			 *                no   gi  gs  gt  root depth
			 *   AAAAAAA      1     1  0   0    0    0
			 *     =>BBBBBB   2     1  1   1
			 *     
			 */
			// 4. 답변하기 => SQL => 합쳐서 처리 (서브쿼리)
			public void boardReplyInsert(int root,ReplyBoardVO vo) {
				try {
					getConnection();
					String sql="SELECT group_id,group_step,group_tab "
							+ "FROM replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, root);
					ResultSet rs=ps.executeQuery();
					rs.next();
					int gi=rs.getInt(1); // gi
					int gs=rs.getInt(2); // gs+1
					int gt=rs.getInt(3); // gt+1
					rs.close();
					// 문제 발생
					sql="UPDATE replyBoard SET "
							+ "group_step=group_step+1 "
							+ "WHERE group_id=? AND group_step>?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, gi);
					ps.setInt(2, gs);
					ps.executeUpdate();
					
					/*
					 *                     gi    gs   gt
					 *     AAAAA            1    0    0
					 *      =>HHHHH         1    1    1
					 *      =>OOOOO         1    2    1
					 *      =>KKKKK         1    3    1
					 *      =>DDDDD         1    4    1 
					 *      =>BBBBB         1    5    1
					 *       =>PPPP         1    6    2
					 *       =>CCCCCC       1    7    2      
					 */
					
					// INSERT
					sql="INSERT INTO replyBoard(no,name,subject,content,pwd,group_id,group_step,group_tab,root) " 
					         + "VALUES(rb_no_seq.nextval,?,?,?,?,"
							 +"?,?,?,?)"; 
					ps=conn.prepareStatement(sql);
					ps.setString(1, vo.getName());
					ps.setString(2, vo.getSubject());
					ps.setString(3, vo.getContent());
					ps.setString(4, vo.getPwd());
					ps.setInt(5, gi);
					ps.setInt(6, gs+1);
					ps.setInt(7, gt+1);
					ps.setInt(8, root);
					ps.executeUpdate();
					// root => depth+1
					
					sql="UPDATE replyBoard SET "
							+ "depth=depth+1 "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, root);
					ps.executeUpdate();
					
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}
			}
			// 5. 수정하기 => UPDATE
			public boolean boardUpdate(ReplyBoardVO vo) {
				boolean bCheck=false;
				try {
					getConnection();
					String sql="SELECT pwd FROM replyBoard "
							+ "WHERE no=?";
					ps=conn.prepareStatement(sql);
					ps.setInt(1, vo.getNo());
					ResultSet rs=ps.executeQuery();
					rs.next();
					String db_pwd=rs.getString(1); // 본인여부확인 
					rs.close();
					
					if(db_pwd.equals(vo.getPwd())) {
						bCheck=true;
						
						sql="UPDATE replyBoard SET "
								+ "name=?,subject=?,content=? "
								+ "WHERE no=?";
						ps=conn.prepareStatement(sql);
						ps.setString(1, vo.getName());
						ps.setString(2, vo.getSubject());
						ps.setString(3, vo.getContent());
						ps.setInt(4, vo.getNo());
						ps.executeUpdate();
					}
					
				}catch (Exception ex) {
					System.out.println(ex.getMessage());
				}finally {
					disConnection();
				}return bCheck;
			}
			// 6. 삭제하기 => SQL => 합쳐서 처리 (서브쿼리)
			// 7. 찾기 => LIKE , REGEXP_LIKE
			public ArrayList<ReplyBoardVO> boardFindData(String fs,String ss){
				ArrayList<ReplyBoardVO> list=new ArrayList<ReplyBoardVO>();
				try {
					getConnection();
					String sql="SELECT no,subject,name,regdate,hit "
							+ "FROM replyBoard "
							+ "WHERE "+fs+" LIKE '%"+ss+"%'";
					// ps.setString(1,fs) ==> 'name'
					// WHERE 'name' LIKE
					ps=conn.prepareStatement(sql);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						ReplyBoardVO vo=new ReplyBoardVO();
						vo.setNo(rs.getInt(1));
						vo.setName(rs.getString(3));
						vo.setSubject(rs.getString(2));
						vo.setRegdate(rs.getDate(4));
						vo.setHit(rs.getInt(5));
						
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
	
			
}
