package dev.mvc.like.reply;

//CREATE TABLE REPLYLIKES(
//    REPLYLIKESNO                      NUMBER(10)     NOT NULL    PRIMARY KEY,
//    CNT                               NUMBER(10)     DEFAULT 0     NOT NULL,
//    REPLYNO                           NUMBER(10)     NULL ,
//    MEMBERNO                          NUMBER(10)     NULL ,
//  FOREIGN KEY (REPLYNO) REFERENCES REPLY (REPLYNO),
//  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
//);
public class ReplyLikesVO {
  /** 댓글 좋아요 번호 */
  private int replylikesno;
  /** 댓글 좋아요 수*/
  private int cnt;
  /** 댓글 번호 */
  private int replyno;
  /** 멤버 번호 */
  private int memberno;
}
