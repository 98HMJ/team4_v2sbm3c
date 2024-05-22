package dev.mvc.nephron;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE NEPHRONPOINT(
//    NEPHRONNO                         NUMBER(10)     NOT NULL    PRIMARY KEY,
//    ROADADDRESS                       VARCHAR2(120)    NOT NULL,
//    DETAILADDRESS                     VARCHAR2(120)    NOT NULL
//);

@Getter @Setter @ToString
public class NephronVO {
  /** 네프론 번호 */
  private int nephronno;
  /** 도로명 주소 */
  private String roadaddress;
  /** 상세 주소 */
  private String detailaddress;
  /** 재활용 품목 표시용 합계치 */
  private int total;
}
