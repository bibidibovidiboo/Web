package com.sist.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.sist.manager.MelonVO;

public class MelonDAO {
	// ���� 
				private Connection conn;
				// SQL ����
				private PreparedStatement ps;
				// ����Ŭ �ּ� 
				private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
				// 1. ���� ����Ŭ �������ִ� ����̹� oracle.jdbc.driver.OracleDriver
				public MelonDAO()
				{
					try
					{
						Class.forName("oracle.jdbc.driver.OracleDriver");
						// ����̹��� �̿��ؼ� ���� => thin����̹� 
					}catch(Exception ex) {}
				}
				// 2. ���� �޼ҵ�
				public void getConnection()
				{
					try
					{
						conn=DriverManager.getConnection(URL,"hr","happy");
						// conn hr/happy
					}catch(Exception ex) {}
				}
				// 3. �ݴ� �޼ҵ� 
				public void disConnection()
				{
					try
					{
						if(ps!=null) ps.close();
						if(conn!=null) conn.close();
						// exit 
					}catch(Exception ex) {}
				}
				// ���
				public void musicInsert(MelonVO vo)
				{
					try
					{
						// ����
						getConnection();
						String sql="INSERT INTO music2 VALUES("
								+"music2_mno_seq.nextval,?,?,?,?,?)";
						ps=conn.prepareStatement(sql);
						// ?�� ���� ä���
						ps.setInt(1, vo.getCateno());
						ps.setString(2, vo.getTitle());
						ps.setString(3, vo.getPoster());
						ps.setString(4, vo.getSinger());
						ps.setString(5, vo.getAlbum());
						// ������
						ps.executeUpdate(); // INSERT ������ ���� => COMMIT
						
						
					}catch(Exception ex)
					{
						System.out.println(ex.getMessage());
					}
					finally
					{
						disConnection();
					}
				}
				
				// �帣 
				public ArrayList<String> musicGenreAllData(){
					ArrayList<String> list=new ArrayList<String>();
					try {
						getConnection();
						String sql="SELECT genre FROM music2_genre "
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

				//  ������ ������
				public ArrayList<MelonVO> musicAllDate(int cateno,int page){
					ArrayList<MelonVO> list=new ArrayList<MelonVO>();
					try {
						getConnection();
						String sql="SELECT mno,title,poster,singer,album,RANK() OVER(ORDER BY mno ASC),num "
								+"FROM (SELECT mno,title,poster,singer,album,rownum as num "
								+"FROM (SELECT mno,title,poster,singer,album "
								+"FROM music2 WHERE cateno=? ORDER BY mno))"
								+"WHERE num BETWEEN ? AND ?"; // ����¡���
						int rowSize=10;
						int start=(rowSize*page)-(rowSize-1);
						// rownum ==> ���۹�ȣ (1)
						int end=rowSize*page;
						
						ps=conn.prepareStatement(sql);
						ps.setInt(1, cateno);
						ps.setInt(2, start);
						ps.setInt(3, end);
						
						// ����
						ResultSet rs=ps.executeQuery();
						while(rs.next()) {
							MelonVO vo=new MelonVO();
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
				
				// �帣
				public String musicGetGenre(int cateno)
				{
					String genre="";
					try
					{
						getConnection();
						String sql="SELECT genre FROM music2_genre "
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
