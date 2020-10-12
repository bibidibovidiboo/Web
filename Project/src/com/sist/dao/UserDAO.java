package com.sist.dao;
import java.io.*;
import java.util.*;
import org.apache.ibatis.io.*;
import org.apache.ibatis.session.*;
public class UserDAO {
	// XML을 읽어서 데이터를 저장하는 객체 => 공통모듈 
	private static SqlSessionFactory ssf;
	static {
		try {
			Reader reader=Resources.getResourceAsReader("Config.xml");
			ssf=new SqlSessionFactoryBuilder().build(reader);
			// getConnection,disConnection 대체 
		}catch (Exception ex) {
			ex.printStackTrace();
		}
	}
    
	// 로그인 처리가 되면 session에 저장해야함  (id,name,admin)
	public static UserVO userLogin(String id,String pwd) {
		UserVO vo=new UserVO();
		// 연결
		SqlSession session=null;
		try {
			// 연결
			session=ssf.openSession(); 
			// id가 몇개 존재하는지 확인하기 
			int count=session.selectOne("userIdCheck",id);
			// id가 없다면 NOID 메세지 보내기 
			if(count==0) {
				vo.setMsg("NOID");
			}
			// id가 존재한다면
			else {
				// id가 존재하면 user정보 가져오기 
				vo=session.selectOne("userGetInfoData",id);
				// user정보의 pwd와 사용자가 입력한 pwd 비교하기
				if(pwd.equals(vo.getPwd())) {
					vo.setMsg("OK");
				}
				// 비밀번호가 틀리면
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