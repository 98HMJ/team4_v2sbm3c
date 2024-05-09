package dev.mvc.like.community;

import lombok.Getter;
import lombok.Setter;

//CREATE TABLE COMMUNITYLIKES(
//    COMMUNITYLIKESNO                  NUMBER(10)     NOT NULL    PRIMARY KEY,
//    CNT                               NUMBER(10)     DEFAULT 0     NOT NULL,
//    COMMUNITYNO                       NUMBER(10)     NULL ,
//    MEMBERNO                          NUMBER(10)     NULL ,
//  FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO),
//  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
//);

@Getter @Setter
public class CommunityLikesVO {
  private int communitylikesno;
  private int cnt;
  private int communityno;
  private int memberno;
}
