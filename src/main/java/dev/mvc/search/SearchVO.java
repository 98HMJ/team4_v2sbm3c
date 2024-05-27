package dev.mvc.search;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE SEARCH(
//    SEARCHNO                          NUMBER(10)     NOT NULL    PRIMARY KEY,
//    SEARCH_WORD                       VARCHAR2(100)    NOT NULL,
//    RDATE                             DATE     NOT NULL
//);

@Getter @Setter @ToString
public class SearchVO {
  /** 검색어 번호 */
  private int searchno;
  
  /** 검색어 */
  private String search_word;
  
  /** 검색 시간 */
  private String rdate;
  
  /** 검색 횟수 */
  private int search_cnt;
}
