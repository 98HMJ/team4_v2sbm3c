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
        PHOTO1SAVED                       VARCHAR2(1000) NULL,
        THUMB1                       VARCHAR2(1000) NULL,
        FILESIZE                      		NUMBER(10)		 DEFAULT 0		 NULL ,
        LIKECNT                       NUMBER(10)		 DEFAULT 0		 NOT NULL,
  FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
    ON DELETE CASCADE
);

COMMENT ON TABLE REPLY is '댓글';
COMMENT ON COLUMN REPLY.REPLYNO is '댓글번호';
COMMENT ON COLUMN REPLY.CONTENTS is '댓글 내용';
COMMENT ON COLUMN REPLY.RDATE is '등록일';
COMMENT ON COLUMN REPLY.PHOTO is '사진';
COMMENT ON COLUMN REPLY.COMMUNITYNO is '커뮤니티번호';
COMMENT ON COLUMN REPLY.MEMBERNO is '회원번호';
COMMENT ON COLUMN REPLY.PHOTO1SAVED is '실제 저장될 사진';
COMMENT ON COLUMN REPLY.THUMB1 is '메인 이미지 PREVIEW';
COMMENT ON COLUMN REPLY.FILESIZE is '메인 이미지 크기';
COMMENT ON COLUMN REPLY.LIKECNT is '좋아요 수';

DROP SEQUENCE reply_seq;

CREATE SEQUENCE reply_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

-- CREATE
INSERT INTO REPLY(replyno, contents, rdate, photo, communityno, memberno, photo1saved, thumb1, filesize, likecnt)
VALUES (reply_seq.nextval, '댓글 test1', SYSDATE, '사진1', 1, 1, 'test.jpg', 'test_t.jpg', '100', 0);

commit;

-- READ: 모든 댓글 목록
SELECT replyno, contents, rdate, photo, communityno, memberno, photo1saved, thumb1, filesize, likecnt
FROM REPLY
ORDER BY REPLYNO;

-- READ(조회) : 특정 댓글 목록 조회
SELECT replyno, contents, rdate, photo, communityno, memberno, photo1saved, thumb1, filesize, likecnt
FROM REPLY
WHERE replyno = 61
ORDER BY REPLYNO DESC;

-- READ(조회) : 특정 커뮤니티의 댓글 목록
SELECT replyno, contents, rdate, photo, communityno, memberno, photo1saved, thumb1, filesize, likecnt
FROM REPLY
WHERE communityno = 1
ORDER BY REPLYNO;

-- 목록 : reply + member nick 정보
SELECT m.nickname, r.replyno, r.contents, r.rdate, r.photo, r.communityno, r.memberno, r.photo1saved, r.thumb1, r.filesize, likecnt
FROM member m,  reply r
WHERE m.memberno = r.memberno and r.communityno = 13;

-- UPDATE : 텍스트 수정
UPDATE reply 
set contents = '업데이트 댓글 test2'
WHERE replyno = 2 and communityno = 1;

commit;

-- UPDATE : 파일 수정
UPDATE reply
SET photo='train.jpg', photo1saved='train.jpg', thumb1='train_t.jpg', filesize=5000
WHERE replyno = 2 and communityno = 1;

commit;

-- DELETE: 삭제
DELETE FROM reply
WHERE replyno = 2;

rollback;

-- 특정 커뮤니티 게시글의 댓글 수
SELECT COUNT(*) as cnt 
FROM reply
WHERE communityno = 1;

--       CNT
------------
--         1

-- 특정 커뮤니티 게시글의 댓글의 좋아요 수 증가
UPDATE reply
SET likecnt = likecnt + 1
WHERE replyno = 2;

--       CNT
------------
--         0

commit;
