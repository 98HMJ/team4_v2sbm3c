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
  private int nephronno;
  private String roadaddress;
  private String detailaddress;
}
