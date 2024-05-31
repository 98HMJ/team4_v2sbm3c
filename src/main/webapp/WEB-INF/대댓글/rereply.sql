/**********************************/
/* Table Name: 대댓글 */
/**********************************/
DROP TABLE REREPLY CASCADE CONSTRAINTS;
DROP TABLE REREPLY;
CREATE TABLE REREPLY(
		REREPLYNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CONTENTS                      		VARCHAR2(2000)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
        PHOTO                             VARCHAR2(1000) NULL,
        COMMUNITYNO                   		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
        REPLYNO                             NUMBER(10)		 NULL ,
        PHOTO1SAVED                       VARCHAR2(1000) NULL,
        THUMB1                       VARCHAR2(1000) NULL,
        FILESIZE                      		NUMBER(10)		 DEFAULT 0		 NULL ,
        LIKECNT                       NUMBER(10)		 DEFAULT 0		 NOT NULL,
  FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO) ON DELETE CASCADE,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO) ON DELETE CASCADE,
  FOREIGN KEY (REPLYNO) REFERENCES REPLY (REPLYNO) ON DELETE CASCADE
);

COMMENT ON TABLE REREPLY is '대댓글';
COMMENT ON COLUMN REREPLY.REREPLYNO is '대댓글번호';
COMMENT ON COLUMN REREPLY.CONTENTS is '대댓글 내용';
COMMENT ON COLUMN REREPLY.RDATE is '등록일';
COMMENT ON COLUMN REREPLY.PHOTO is '사진';
COMMENT ON COLUMN REREPLY.COMMUNITYNO is '커뮤니티번호';
COMMENT ON COLUMN REREPLY.MEMBERNO is '회원번호';
COMMENT ON COLUMN REREPLY.REPLYNO is '댓글번호';
COMMENT ON COLUMN REREPLY.PHOTO1SAVED is '실제 저장될 사진';
COMMENT ON COLUMN REREPLY.THUMB1 is '메인 이미지 PREVIEW';
COMMENT ON COLUMN REREPLY.FILESIZE is '메인 이미지 크기';
COMMENT ON COLUMN REREPLY.LIKECNT is '좋아요 수';

DROP SEQUENCE rereply_seq;

CREATE SEQUENCE rereply_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
  
commit;