package dev.mvc.report.community;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//CREATE TABLE REPORT_COMMUNITY(
//    REPORTNO                          NUMBER(10)     NOT NULL    PRIMARY KEY,
//    CONTENTS                          VARCHAR2(2000)   NOT NULL,
//        RDATE                             DATE         NOT NULL,
//        COMMUNITYNO                       NUMBER(10)     NULL ,
//    MEMBERNO                          NUMBER(10)     NULL ,
//  FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO),
//  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
//    ON DELETE CASCADE
//);

@Getter @Setter @ToString
public class ReportCommunityVO {
  /** 신고 번호*/
  int reportno;
  
  /** 신고 내용*/
  String contents = ""; 
  
  /** 신고 시간*/
  String rdate;
  
  /** 커뮤니티 번호*/
  int communityno;
  
  /** 회원 번호*/
  int memberno;
  
}
