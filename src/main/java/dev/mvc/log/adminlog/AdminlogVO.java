package dev.mvc.log.adminlog;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AdminlogVO {
  // CREATE TABLE ADMINLOG(
	// 	ADMINLOGNO                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
	// 	IP                            		VARCHAR2(20)		 NOT NULL,
	// 	RDATE                         		DATE		 NOT NULL,
	// 	ADMINNO                       		NUMBER(10)		 NULL ,
  // FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
  // );

  /** 관리자 로그 번호 */
  private int adminlogno;
  /** 로그인 ip */
  private String ip = "";
  /** 로그인 시간 */
  private String rdate = "";
  /** 관리자 번호 */
  private int adminno;
}
