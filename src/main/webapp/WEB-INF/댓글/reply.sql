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

-- CREATE
INSERT INTO REPLY(replyno, contents, rdate, photo, communityno, memberno)
VALUES (reply_seq.nextval, '댓글 test1', SYSDATE, '사진1', 1, 1);

commit;

-- READ: 모든 댓글 목록
SELECT replyno, contents, rdate, photo, communityno, memberno
FROM REPLY
ORDER BY REPLYNO;

-- READ(조회) : 특정 커뮤니티의 댓글 목록
SELECT replyno, contents, rdate,photo, communityno, memberno
FROM REPLY
WHERE communityno = 1
ORDER BY REPLYNO;

-- UPDATE : 수정
UPDATE reply 
set contents = '업데이트 댓글 test1', photo = '업데이트 사진2'
WHERE replyno = 3;

-- DELETE: 삭제
DELETE FROM reply 
WHERE replyno = 3;

rollback;

-- 특정 커뮤니티 게시글의 댓글 수
SELECT COUNT(*) as cnt 
FROM reply
WHERE communityno = 3;

--       CNT
------------
--         1

commit;
