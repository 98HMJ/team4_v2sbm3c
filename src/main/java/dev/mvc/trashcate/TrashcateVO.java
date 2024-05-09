package dev.mvc.trashcate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE TRASHCATE(
//    TRASHCATENO                       NUMBER(10)     NOT NULL    PRIMARY KEY,
//    NAME                              VARCHAR2(30)     DEFAULT '일반쓰레기'     NOT NULL
//);

@Getter @Setter @ToString
public class TrashcateVO {
  /** 쓰레기 카테고리 번호 */
  private int trashcateno;
  /** 쓰레기 카테고리 이름 */
  private String name;
}
