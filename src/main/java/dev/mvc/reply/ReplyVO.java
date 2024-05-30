package dev.mvc.reply;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/*
CREATE TABLE REPLY(
    REPLYNO                           NUMBER(10)     NOT NULL    PRIMARY KEY,
    CONTENTS                          VARCHAR2(2000)     NOT NULL,
    RDATE                             DATE     NOT NULL,
    PHOTO                             VARCHAR2(1000) NULL,
    COMMUNITYNO                       NUMBER(10)     NULL ,
    MEMBERNO                          NUMBER(10)     NULL ,
    PHOTO1SAVED                       VARCHAR2(1000) NULL,
    THUMB1                       VARCHAR2(1000) NULL,
    FILESIZE                          NUMBER(10)     DEFAULT 0     NULL ,
  FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);
*/

@Getter @Setter @ToString
public class ReplyVO {
  /** 댓글 번호 */
  private int replyno;
  
  /** 댓글 내용 */
  private String contents = "";
  
  /** 댓글 작성일 */
  private String rdate;
  
  /** 좋아요 수*/
  private int likecnt;
  
  /** 사진 업로드 관련 */
  private MultipartFile file1MF = null;
  
  /** 메인 이미지 크기 단위, 파일 크기 */
  private String size1_label = "";
  
  /** 실제 사진 */
  private String photo = "";
  
  /** 실제 저장될 사진  */
  private String photo1saved="";
  
  /** 섬네일 사진 */
  private String thumb1 = "";
  
  /** 파일 사이즈 */
  private long filesize;
  // ---------------------------------
  
  /** 댓글이 속한 커뮤니티 번호 */
  private int communityno;
  
  /** 댓글을 작성한 회원 번호 */
  private int memberno;

  private int rereply_cnt = 0;
  
}
