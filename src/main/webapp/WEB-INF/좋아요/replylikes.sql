/**********************************/
/* Table Name: 댓글 좋아요 */
/**********************************/
DROP TABLE REPLYLIKES CASCADE CONSTRAINTS;
DROP TABLE REPLY_LIKES;
commit;
CREATE TABLE REPLYLIKES(
		REPLYLIKESNO                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CNT                           		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		REPLYNO                       		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (REPLYNO) REFERENCES REPLY (REPLYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
    ON DELETE CASCADE
);

COMMENT ON TABLE REPLYLIKES is '댓글 좋아요';
COMMENT ON COLUMN REPLYLIKES.REPLYLIKESNO is '댓글좋아요번호';
COMMENT ON COLUMN REPLYLIKES.CNT is '좋아요수';
COMMENT ON COLUMN REPLYLIKES.REPLYNO is '댓글번호';
COMMENT ON COLUMN REPLYLIKES.MEMBERNO is '회원번호';

DROP SEQUENCE replylikes_seq;

CREATE SEQUENCE replylikes_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지
  
-- CREATE: 댓글의 좋아요
INSERT INTO replylikes(replylikesno, cnt, replyno, memberno)
VALUES(replylikes_seq.nextval, 1, 3, 2);

commit;

-- READ(목록) : 모든 좋아요 목록 
SELECT * FROM replylikes;

-- READ(조회): 댓글의 좋아요 수(cnt)
SELECT cnt
FROM replylikes
WHERE replyno = 3;

-- UPDATE : 댓글의 좋아요 수 증가
UPDATE replylikes
SET cnt = cnt + 1
WHERE replyno = 3;

-- UPDATE : 댓글의 좋아요 수 감소
UPDATE replylikes
SET cnt = cnt - 1
WHERE replyno = 3 AND memberno = 2 AND cnt > 0;

-----------------------------------
-- DELETE: 댓글의 좋아요 
-- 회원 삭제시 회원의 모든 좋아요 취소?
-- ex)B회원의 개시글에 회원A가 좋아요 한 경우 취소 
------------------------------------
rollback;