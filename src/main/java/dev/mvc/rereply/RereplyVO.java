package dev.mvc.rereply;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

// CREATE TABLE REREPLY(
// 		REREPLYNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
// 		CONTENTS                      		VARCHAR2(2000)		 NOT NULL,
// 		RDATE                         		DATE		 NOT NULL,
//         PHOTO                             VARCHAR2(1000) NULL,
// 		MEMBERNO                      		NUMBER(10)		 NULL ,
//         REPLYNO                             NUMBER(10)		 NULL ,
//         PHOTO1SAVED                       VARCHAR2(1000) NULL,
//         THUMB1                       VARCHAR2(1000) NULL,
//         FILESIZE                      		NUMBER(10)		 DEFAULT 0		 NULL ,
//         LIKECNT                       NUMBER(10)		 DEFAULT 0		 NOT NULL,
//   FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO) ON DELETE CASCADE,
//   FOREIGN KEY (REPLYNO) REFERENCES REPLY (REPLYNO) ON DELETE CASCADE
// );

@Getter
@Setter
public class RereplyVO {
    /** 대댓글 번호 */
    private int rereplyno;

    /** 대댓글 내용 */
    private String contents;

    /** 대댓글 등록일 */
    private String rdate;

     /** 실제 사진 */
    private String photo= "";

    /** 회원 번호 */
    private int memberno;

    /** 댓글 번호 */
    private int replyno;

    /** 실제 저장될 사진  */
    private String photo1saved = "";

    /** 섬네일 사진 */
    private String thumb1= "";

    /** 사진 업로드 관련 */
    private MultipartFile file1MF = null;

    /** 파일 사이즈 */
    private long filesize;

    /** 좋아요 수*/
    private int likecnt = 0;
}
