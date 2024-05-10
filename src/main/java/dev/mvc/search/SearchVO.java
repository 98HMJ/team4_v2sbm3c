package dev.mvc.search;

//CREATE TABLE SEARCH(
//    SEARCHNO                          NUMBER(10)     NOT NULL    PRIMARY KEY,
//    SEARCH_WORD                       VARCHAR2(100)    NOT NULL,
//    RDATE                             DATE     NOT NULL
//);

public class SearchVO {
  /** 검색어 번호 */
  private int searchno;
  
  /** 검색어 */
  private String search_word;
  
  /** 검색 시간 */
  private String rdate;
}
