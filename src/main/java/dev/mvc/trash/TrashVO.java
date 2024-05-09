package dev.mvc.trash;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE TRASH(
//    TRASHNO                           NUMBER(10)     NOT NULL    PRIMARY KEY,
//    NAME                              VARCHAR2(100)    NOT NULL,
//    ISRECYCLE                         CHAR(1)    DEFAULT 'N'     NOT NULL,
//    TAG                               VARCHAR2(100)    NULL ,
//    PROCESS                           VARCHAR2(3000)     NOT NULL,
//    TIP                               VARCHAR2(3000)     NULL ,
//    PHOTO                             VARCHAR2(1000)     NULL ,
//    TRASHCATENO                       NUMBER(10)     NULL ,
//  FOREIGN KEY (TRASHCATENO) REFERENCES TRASHCATE (TRASHCATENO)
//);

@Getter @Setter @ToString
public class TrashVO {
  /** 쓰레기 번호 */
  private int trashno;
  /** 쓰레기 이름 */
  private String name;
  /** 재사용 가능 여부 */
  private String isrecycle;
  /** 쓰레기 태그 */
  private String tag;
  /** 버리는 방법 */
  private String process;
  /** 쓰레기 버리기 팁 */
  private String tip;
  /** 쓰레기 이미지 파일 */
  private String photo;
  /** 쓰레기 카테고리 번호 */
  private int trashcateno;

}
