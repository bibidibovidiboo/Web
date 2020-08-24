package com.sist.dao;

import java.util.Date;

/*
 * 
 * NO       NOT NULL NUMBER        
CATENO            NUMBER        
TITLE    NOT NULL VARCHAR2(200) 
POSTER   NOT NULL VARCHAR2(300) 
REGDATE           VARCHAR2(200) 
GENRE    NOT NULL VARCHAR2(100) 
GRADE    NOT NULL VARCHAR2(100) 
ACTOR             VARCHAR2(100) 
SCORE             VARCHAR2(20)  
DIRECTOR NOT NULL VARCHAR2(100)        
KEY               VARCHAR2(50) 
 */
public class MovieVO {
	private int no;
	private String title;
	private String poster;
	private String regdate;
	private String genre;
	private String actor;
	private String director;

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	
}
