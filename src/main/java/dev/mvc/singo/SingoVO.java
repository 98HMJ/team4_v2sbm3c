package dev.mvc.singo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SingoVO {
  // CREATE TABLE SINGO(
	// 	SINGONO                       		NUMBER(10)		 NULL ,
	// 	CONTENTS                      		VARCHAR2(3000)		 NOT NULL,
	// 	RDATE                         		DATE		 NOT NULL,
	// 	MEMBERNO                      		NUMBER(10)		 NULL ,
	// 	TRASHNO                       		NUMBER(10)		 NULL ,
  //   PHOTO                             VARCHAR2(1000) NULL ,
  // FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  // FOREIGN KEY (TRASHNO) REFERENCES TRASH (TRASHNO)
  // );
  
  /** 신고 번호 */
  private int singono;
  /** 신고 내용 */
  private String contents = "";
  /** 등록일 */
  private String rdate ="";
  /** 회원 번호 */
  private int memberno;
  /** 쓰레기 번호 */
  private int trashno;
  /** 이미지 */
  private String photo = "";
}
