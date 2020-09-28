package com.sist.dao;
// 디벨로퍼로 만든 테이블 쿼리문 맨 밑에 있음 ★
/*
   NO      NOT NULL NUMBER         
   NAME    NOT NULL VARCHAR2(34)   
   SUBJECT NOT NULL VARCHAR2(1000) 
   CONTENT NOT NULL CLOB           
   PWD     NOT NULL VARCHAR2(10)   
   REGDATE          DATE           
   HIT              NUMBER  

   vo => 변수만 설정
   
     읽기 : 변수값 읽어오기 => getter
     쓰기 : 변수값을 메모리에 저장 => setter
 */
import java.util.*;//date
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
/*
CREATE TABLE movie_board3(
no NUMBER,
name VARCHAR2(34) CONSTRAINT mb3_name_nn NOT NULL,
subject VARCHAR2(1000) CONSTRAINT mb3_subject_nn NOT NULL,
content CLOB CONSTRAINT mb3_content_nn NOT NULL,
pwd VARCHAR2(10) CONSTRAINT mb3_pwd_nn NOT NULL,
regdate DATE DEFAULT SYSDATE,
hit NUMBER DEFAULT 0,
CONSTRAINT mb4_no_pk PRIMARY KEY(no)
);

CREATE SEQUENCE mb3_no_seq
START WITH 1
INCREMENT BY 1
NOCYCLE
NOCACHE;

INSERT INTO movie_board3 VALUES (
mb3_no_seq.nextval,
'홍길동','8장','8장','1234',SYSDATE,0
);
INSERT INTO movie_board3 VALUES (
mb3_no_seq.nextval,
'홍길동','8장','8장','1234',SYSDATE,0
);
INSERT INTO movie_board3 VALUES (
mb3_no_seq.nextval,
'홍길동','8장','8장','1234',SYSDATE,0
);
INSERT INTO movie_board3 VALUES (
mb3_no_seq.nextval,
'홍길동','8장','8장','1234',SYSDATE,0
);
INSERT INTO movie_board3 VALUES (
mb3_no_seq.nextval,
'홍길동','8장','8장','1234',SYSDATE,0
);
COMMIT;

*/