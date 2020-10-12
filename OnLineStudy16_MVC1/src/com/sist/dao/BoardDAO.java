package com.sist.dao;
import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.*;
public class BoardDAO {
   // �Ľ�(XML) => SqlSessionFactory
	private static SqlSessionFactory ssf;
	// �����ϱ����� �ڵ����� �Ľ��� �Ѵ� => �ʱ�ȭ�� (static => static{} , instance => {})
	static
	{
		// ���� ó��  => Mybatis (������ ����(X) => ������ �߻��ÿ� ã�� ��ƴ�)
		try
		{
			// IO => ���ϰ�� , ���ϸ��� Ʋ����쿡 ó��(�����Ͽ���=>�ݵ�� ����ó���� �Ѵ�)
			// ���� �б�
			Reader reader=Resources.getResourceAsReader("Config.xml");
			// MyBatis,Spring => classpath(�ڵ����� �ν��ϴ� ��� : src) 
			// XML �Ľ� 
			ssf=new SqlSessionFactoryBuilder().build(reader);
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}
	/*
	 *   <select id="boardListData" resultType="BoardVO" parameterType="hashmap">
		   SELECT no,subject,name,regdate,hit,num
		   FROM (SELECT no,subject,name,regdate,hit,rownum as num
		   FROM (SELECT no,subject,name,regdate,hit 
		   FROM freeboard ORDER BY no DESC))
		   WHERE num BETWEEN #{start} AND #{end}
		  </select>
		  <select id="boardTotalPage" resultType="int">
		   SELECT CEIL(COUNT(*)/10.0) FROM freeboard
		  </select>
	 */
	//            resultType                parameterType
	public static List<BoardVO> boardListData(Map map)
	{
		List<BoardVO> list=new ArrayList<BoardVO>();
		SqlSession session=ssf.openSession();// Connection���
		list=session.selectList("boardListData",map);
		session.close();//Connection��ȯ
		return list;// null 
	}
	public static int boardTotalPage()
	{
		int total=0;
		SqlSession session=ssf.openSession();
		total=session.selectOne("boardTotalPage");
		session.close();
		return total;
	}
	/*
	 *   <update id="hitIncrement" parameterType="int">
		     UPDATE freeboard SET
		     hit=hit+1
		     WHERE no=#{no}
		   </update>
		   <select id="boardDetailData" resultType="BoardVO" parameterType="int">
		     <!-- public BoardVO boardDetailData(int no) -->
		     SELECT no,name,subject,content,regdate,hit
		     FROM freeboard
		     WHERE no=#{no}
		   </select>
	 */
	/*
	 *   CURD 
	 *     <select>
	 *       SQL => 1���� ��� (select����)
	 *     </select>
	 *     <insert>
	 *       1�� (insert)
	 *     </insert>
	 *     <update>
	 *     </update>
	 *     <delete>
	 *     </delete>
	 *     
	 *     �� => ������ �޼ҵ�� �������� ���ÿ� �о ó�� 
	 */
	public static BoardVO boardDetailData(int no)
	{
		SqlSession session=ssf.openSession();
		// ��ȸ�� ����
		session.update("hitIncrement", no);
		session.commit();// ���������� ���� 
		// ������ �б�
		BoardVO vo=session.selectOne("boardDetailData", no);
		// ��ȯ
		session.close();
		return vo;
	}
}


