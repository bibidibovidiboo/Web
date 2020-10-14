package com.sist.dao;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class CreateSqlSessionFactory {
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml");
			// ÆÄ½Ì
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

	}
	// getter - setter¿¡ getter
	public static SqlSessionFactory getSsf() {
		return ssf;
	}
}