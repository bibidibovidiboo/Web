package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
/*
 *	+) �ʱ� 
 */
public class MusicDAO {
	private static SqlSessionFactory ssf;
	// ssf => XML�� �о���� ���� => ����Ŭ���� (JDBC) , XML(�Ľ�) , HTML(ũ�Ѹ�)
	static {
		try {
			Reader reader=Resources.getResourceAsReader("config.xml");
			// XML �Ľ�
			ssf=new SqlSessionFactoryBuilder().build(reader);
			
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	public static List<MusicVO> musicAllData(){
		return ssf.openSession().selectList("musicAllData"); // <select id="musicAllData">
	}
}
