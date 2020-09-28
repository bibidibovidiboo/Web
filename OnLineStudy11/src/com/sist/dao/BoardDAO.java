package com.sist.dao;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
public class BoardDAO {
	public static SqlSessionFactory ssf;
	
	static
	{
		
		try {
			Reader reader = Resources.getResourceAsReader("Config.xml");
			
			ssf= new SqlSessionFactoryBuilder().build(reader);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static List<BoardVO> freeBoardData(Map map)
	{
		List<BoardVO> list = new ArrayList<BoardVO>();
		SqlSession session = null;
		
		try {
			session=ssf.openSession();
			
			list = session.selectList("freeboardListData",map);
			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		
		return list;
	}
//  <select id="freeboardTotalPage" resultType="int" >
	public static int freeboardTotalPage() {
		int total=0;
		SqlSession session=null;
		try {
			session=ssf.openSession();
			
			session.selectOne("freeboardTotalPage");
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
		
		return total;
	}
}
