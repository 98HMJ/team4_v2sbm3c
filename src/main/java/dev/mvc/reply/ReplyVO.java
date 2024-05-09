package dev.mvc.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
 * CREATE TABLE REPLY(
    REPLYNO                           NUMBER(10)     NOT NULL    PRIMARY KEY,
    CONTENTS                          VARCHAR2(2000)     NOT NULL,
    RDATE                             DATE     NOT NULL,
    COMMUNITYNO                       NUMBER(10)     NULL ,
    MEMBERNO                          NUMBER(10)     NULL ,
  FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);
*/

@Getter @Setter @ToString
public class ReplyVO {
  /** 댓글 번호 */
  private int replyno;
  
  /** 댓글 내용 */
  private String contents;
  
  /** 댓글 작성일 */
  private String rdate;
  
  /** 댓글이 속한 커뮤니티 번호 */
  private int communityno;
  
  /** 댓글을 작성한 회원 번호 */
  private int memberno;
  
}
