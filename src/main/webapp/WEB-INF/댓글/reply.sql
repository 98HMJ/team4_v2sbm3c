/**********************************/
/* Table Name: 댓글 */
/**********************************/
DROP TABLE REPLY CASCADE CONSTRAINTS;
DROP TABLE REPLY;
CREATE TABLE REPLY(
		REPLYNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CONTENTS                      		VARCHAR2(2000)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
    PHOTO                             VARCHAR2(1000) NULL,
		COMMUNITYNO                   		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE REPLY is '댓글';
COMMENT ON COLUMN REPLY.REPLYNO is '댓글번호';
COMMENT ON COLUMN REPLY.CONTENTS is '댓글 내용';
COMMENT ON COLUMN REPLY.RDATE is '등록일';
COMMENT ON COLUMN REPLY.PHOTO is '사진';
COMMENT ON COLUMN REPLY.COMMUNITYNO is '커뮤니티번호';
COMMENT ON COLUMN REPLY.MEMBERNO is '회원번호';

DROP SEQUENCE reply_seq;

CREATE SEQUENCE reply_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

INSERT INTO REPLY(replyno, contents, rdate, communityno, memberno)
VALUES (reply_seq.nextval, '댓글 test1', SYSDATE, 3, 2);

commit;

SELECT replyno, contents, rdate, communityno, memberno
FROM REPLY
WHERE replyno = 3
ORDER BY REPLYNO;

SELECT replyno, contents, rdate, communityno, memberno
FROM REPLY
ORDER BY REPLYNO;

UPDATE reply 
set contents = '업데이트 댓글 test1'
WHERE replyno = 3;

SELECT replyno, contents, rdate, communityno, memberno
FROM REPLY
ORDER BY REPLYNO;

DELETE FROM reply WHERE replyno = 3;

rollback;