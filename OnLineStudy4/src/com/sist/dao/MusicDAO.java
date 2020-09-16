package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
/*
 *	+) 필기 
 */
public class MusicDAO {
	private static SqlSessionFactory ssf;
	// ssf => XML을 읽어오는 역할 => 오라클연결 (JDBC) , XML(파싱) , HTML(크롤링)
	static {
		try {
			Reader reader=Resources.getResourceAsReader("config.xml");
			// XML 파싱
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	public static List<MusicVO> musicAllData(){
		return ssf.openSession().selectList("musicAllData"); // <select id="musicAllData">
	}
}
