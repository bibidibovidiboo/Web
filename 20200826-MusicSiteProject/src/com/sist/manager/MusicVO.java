// 지니뮤직 VO
// Server 폴더는 buildpath가 적용 X => WebContent - WEB-INF - lib에 jsoup 넣어야함 ★
/*
DESC music => 내용복사
MNO    NOT NULL NUMBER        
CATENO          NUMBER        
TITLE  NOT NULL VARCHAR2(300) 
POSTER NOT NULL VARCHAR2(260) 
SINGER NOT NULL VARCHAR2(100) 
ALBUM  NOT NULL VARCHAR2(100) 
*/
package com.sist.manager;
public class MusicVO {
	private int mno;
	private int cateno;
	private String title;
	private String poster;
	private String singer;
	private String album;
	private int rank;
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getCateno() {
		return cateno;
	}
	public void setCateno(int cateno) {
		this.cateno = cateno;
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
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	
}
