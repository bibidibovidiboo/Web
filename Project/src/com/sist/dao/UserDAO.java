package com.sist.dao;
import java.io.*;
import java.util.*;
import org.apache.ibatis.io.*;
import org.apache.ibatis.session.*;
public class UserDAO {
	// XML�� �о �����͸� �����ϴ� ��ü => ������ 
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			// getConnection,disConnection ��ü 
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
    
	// �α��� ó���� �Ǹ� session�� �����ؾ���  (id,name,admin)
	public static UserVO userLogin(String id,String pwd) {
		UserVO vo=new UserVO();
		// ����
		SqlSession session=null;
		try {
			// ����
			session=ssf.openSession(); 
			// id�� � �����ϴ��� Ȯ���ϱ� 
			int count=session.selectOne("userIdCheck",id);
			// id�� ���ٸ� NOID �޼��� ������ 
			if(count==0) {
				vo.setMsg("NOID");
			}
			// id�� �����Ѵٸ�
			else {
				// id�� �����ϸ� user���� �������� 
				vo=session.selectOne("userGetInfoData",id);
				// user������ pwd�� ����ڰ� �Է��� pwd ���ϱ�
				if(pwd.equals(vo.getPwd())) {
					vo.setMsg("OK");
				}
				// ��й�ȣ�� Ʋ����
				else {
					vo.setMsg("NOPWD");
				}
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return vo;
	}
}