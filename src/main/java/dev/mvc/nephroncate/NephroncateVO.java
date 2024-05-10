package dev.mvc.nephroncate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE NEPHRONCATE(
//    NEPHRONCATENO                     NUMBER(10)     NOT NULL    PRIMARY KEY,
//    NEPHRONNO                         NUMBER(10)     NULL ,
//    TRASHCATENO                       NUMBER(10)     NULL ,
//  FOREIGN KEY (NEPHRONNO) REFERENCES NEPHRONPOINT (NEPHRONNO),
//  FOREIGN KEY (TRASHCATENO) REFERENCES TRASHCATE (TRASHCATENO)
//);

@Getter @Setter @ToString
public class NephroncateVO {
  private int nephroncateno;
  private int nephronno;
  private int trashcateno;
}
