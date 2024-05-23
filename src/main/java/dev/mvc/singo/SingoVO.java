package dev.mvc.singo;

import org.springframework.web.multipart.MultipartFile;

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
  
  private MultipartFile file1MF = null;

  private MultipartFile file2MF = null;

  /** 실제 저장된 메인 이미지 */
  private String filesaved = "";

  /** 메인 이미지 크기 */
  private long size1 = 0;

  /* 원본 파일명 */
  private String files = "";

  /* 저장된 파일명 */
  private String thumb = "";

  /* 파일 사이즈 */
  private double filesize = 0;


  /** member의 닉네임 */
  private String nickname="";
  /** trash 이름 */
  private String name="";
}
