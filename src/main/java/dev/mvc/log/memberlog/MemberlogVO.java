package dev.mvc.log.memberlog;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberlogVO {
  // CREATE TABLE MEMBERLOG(
	// 	MEMBERLOGNO                   		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
	// 	IP                            		VARCHAR2(20)		 NOT NULL,
	// 	RDATE                         		DATE		 NOT NULL,
	// 	MEMBERNO                      		NUMBER(10)		 NULL ,
  // FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
  // );
  /** 회원 로그 번호 */
  private int memberlogno;
  /** 로그인 ip */
  private String ip = "";
  /** 로그인 시간 */
  private String rdate = "";
  /** 회원 번호 */
  private int memberno;
}
