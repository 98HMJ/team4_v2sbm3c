package dev.mvc.admin;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter

public class AdminVO {
//   CREATE TABLE ADMIN(
// 		ADMINNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
// 		NAME                          		VARCHAR2(42)		 NOT NULL,
// 		ID                            		VARCHAR2(30)		 NOT NULL,
// 		PASSWORD                        		VARCHAR2(20)		 NOT NULL,
//    EMAIL                              VARCHAR2(30)     NULL,
// 		RDATE                         		DATE		 NOT NULL
// );
  /** 관리자 번호 */
  private int adminno;
  /** 관리자 명 */
  private String name = "";
  /** 관리자 ID */
  private String id = "";
  /** 관리자 password */
  private String password = "";
  /** 이메일 */
  private String email = "";
  /** 등록일 */
  private String rdate = "";
}
