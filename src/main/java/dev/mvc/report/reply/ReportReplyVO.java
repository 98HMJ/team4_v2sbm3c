package dev.mvc.report.reply;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
  CREATE TABLE REPORT_REPLY(
      REPORTNO                          NUMBER(10)     NOT NULL    PRIMARY KEY,
      CONTENTS                                  VARCHAR2(2000)     NOT NULL,
          RDATE                             DATE         NOT NULL,
          REPLYNO                       NUMBER(10)     NULL ,
      MEMBERNO                          NUMBER(10)     NULL ,
    FOREIGN KEY (REPLYNO) REFERENCES REPLY (REPLYNO),
    FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
      ON DELETE CASCADE
  );
*/

@Getter @Setter @ToString
public class ReportReplyVO {
  
  /** 신고 번호 */
  int reportno;
  
  /** 신고 내용 */
  String contents = "";
  
  /** 신고 시간 */
  String rdate = "";
  
  /** 댓글 번호 */
  int replyno;
  
  /** 멤버 번호 */
  int memberno;
}
