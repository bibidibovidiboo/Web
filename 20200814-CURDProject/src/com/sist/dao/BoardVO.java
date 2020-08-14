package com.sist.dao;
import java.util.*;
/*
 	no NUMBER,
	name VARCHAR2(34),
	subject VARCHAR2(2000),
	content CLOB,
	pwd VARCHAR2(10),
	regdate DATE DEFAULT SYSDATE,
	hit NUMBER DEFAULT 0
	
	문자 ==> 자바 (String)
	= CHAR , VARCHAR2 , CLOB
	숫자 ==> 자바 (int , double)
	= NUMBER => 1,2,3 => int
	= NUMBER(7,2) => double
	날짜 ==> java.util.Date
	= DATE , TIMESTAMP
	기타 ==> InputStream
	= BLOB , BFILE
	
 */
public class BoardVO {
	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private Date regdate;
	private int hit;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
}