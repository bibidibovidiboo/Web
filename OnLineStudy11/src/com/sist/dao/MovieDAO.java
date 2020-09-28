package com.sist.dao;

import java.io.Reader;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MovieDAO {
	private static SqlSessionFactory ssf;
	//자동 초기화 => 클래스를 읽을때 자동으로 처리
	static
	{
		try
		{
			
			//XML읽기 = > 주속
			Reader reader=Resources.getResourceAsReader("Config.xml");
			//Config => movie-mapper.xml,databoard-mapper.xml을 포함
			//Config.xml에 ~mapper.=xml 반드시 등록
			//파싱 : XML에 등록되어 있는 데이터중에 필요한 데이터를 읽어가는 과정이 = SqlSessionFactory
			ssf=new SqlSessionFactoryBuilder().build(reader);
			// 프로그래머가 XML을 파싱하지 않고 => 라이브러리에 파싱 메소드가 있다
			// build ==> 파싱하는 메소드 (sax) => 태크를 한개씩 읽어서 데이터만 추출
			// mybatis가 제공하는 태그,속성을 반드시 사용 ==> dtd (태그,속성 정의)
			// Spring => XML에 필요한 데이터를 저장 => Spring 라이브러리가 읽어가서 웹을 동작
			/*
			 *  XML을 이용하는 목적
			 * 	=> 자바 소스를 제공하지 않는다 => 동작에 필요한 데이터를 XML에 올려주며 읽어간다.
			 *  (.class)파일만 보내준다. 소스공개하지않음
			 *  => 모든 라이브러리는 소스를 공개하지 않는다 (실행할 수 있는 파일만 제공)
			 *  => 배포 (war) -> jar(.class)
			 */
		}catch (Exception e) {
			// TODO: handle exception
		}finally {
			
		}
	}
	// SQL문장을 읽어서 실행 => 요청한 데이터를 넘겨준다(SQL => Movie-mapper.xml).
	//<select id="movieListData" resultType="MovieVO" parameterType="hashmap">
	public static List<MovieVO> movieListData(Map map)
	{
		//map => start번호, end번호
		List<MovieVO> list = new ArrayList<MovieVO>();
		// list에 값을 채운다 ==> home.jsp에 채워진 list값을 출력
		// 오라클 연결
		SqlSession session = null;
		// 실행 => 결과값 모아서 처리
		try {
			//연결
			session=ssf.openSession(); // COMMIT을 사용여부
			/*
			 *  INSERT,UPDATE,DELETE : 오라클 저장된 데이터가 변경 => 다시 저장 요청 : COMMIT
			 *  ssf.openSession() => Commit 이 없음
			 *  ssf.openSession(true) => Commit 수행
			 */
			//SQL문장을 실행후에 데이터를 읽어서 LIST에 저장
			list=session.selectList("movieListData",map);
		}catch (Exception e) {
			// XML => 에러가 났을 경우에 어디서 발생했는지 확인
			e.printStackTrace();
		}finally {
			// 미리 생성된 Connection을 다시 사용하기 위해 반환 => DBCP (POOL 방식)
			// DataBase Connection Pool(Connection을  관리하는 영역)
		}
		return list;
	}
	//총페이지
	//	<select id="movieTotalPage" resultType="int">
	public static int movieTotalPage()
	{
		int total=0;
		//연결 
		SqlSession session=null;
		try {
			//연결
			session=ssf.openSession();
			// sql 문장 실행한 결과값 받기
			total=session.selectOne("movieTotalPage");
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		return total;
	}
	//	<select id="movieCateData" resultType="MovieVO" parameterType="int">
	public static List<MovieVO> movieCategoryData(int cateno)
	{
		List<MovieVO> list = new ArrayList<MovieVO>();
		// 연결
		SqlSession session = null;
		try {
			session=ssf.openSession();
			//SQL 문장 실행한후에 결과값을 받는다
			list=session.selectList("movieCateData",cateno);
		}catch (Exception e) {
			//에러 처리
			e.printStackTrace();
		}finally {
			if(session!=null)
				session.close();
		}
		
		
		return list;
	}
	//  상세보기 : XML에 코딩 => Mybatis에 처리 요청
	//	<select id="movieDetailData" resultType="MovieVO" parameterType="int">
	// 처리는 dao(자바) ==> 처리된 결과 브라우저에 전송 => jsp가 받아서 화면에 출력
	
	public static MovieVO movieDetailData(int no)
	{
		MovieVO vo =new MovieVO();
		
		SqlSession session =null;
		try {
			
			// 연결 시도
			session=ssf.openSession();
			vo=session.selectOne("movieDetailData",no);
			// SQL 문장을 실행 
		}catch (Exception e) {
			//에러 처리
			e.printStackTrace();
		}finally {
			//닫기 ==> 재사용이 가능하게 반환
			if(session!=null)
				session.close();
		}
		
		return vo;
	}
}
