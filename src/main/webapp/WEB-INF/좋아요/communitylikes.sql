/**********************************/
/* Table Name: 글 좋아요 */
/**********************************/
DROP TABLE COMMUNITYLIKES CASCADE CONSTRAINTS;
DROP TABLE COMMUNITYLIKES;
commit;
CREATE TABLE COMMUNITYLIKES(
		COMMUNITYLIKESNO              		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CNT                           		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		COMMUNITYNO                   		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
   ON DELETE CASCADE
);

COMMENT ON TABLE COMMUNITYLIKES is '글 좋아요';
COMMENT ON COLUMN COMMUNITYLIKES.COMMUNITYLIKESNO is '글 좋아요 번호';
COMMENT ON COLUMN COMMUNITYLIKES.CNT is '글 좋아요 수';
COMMENT ON COLUMN COMMUNITYLIKES.COMMUNITYNO is '커뮤니티번호';
COMMENT ON COLUMN COMMUNITYLIKES.MEMBERNO is '회원번호';

DROP SEQUENCE communitylikes_seq;

CREATE SEQUENCE communitylikes_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 

commit;

-- CREATE: 커뮤니티 게시글의 좋아요
INSERT INTO communitylikes(communitylikesno, cnt, communityno, memberno)
VALUES(communitylikes_seq.nextval, 1, 1, 1);

commit;

-- READ(목록) : 모든 좋아요 목록 
SELECT * FROM communitylikes;

-- READ(조회): 특정 커뮤니티 글의 좋아요 수
SELECT cnt
FROM communitylikes
WHERE communityno = 3;

-- UPDATE : 커뮤니티 게시글의 좋아요 수 증가
UPDATE communitylikes
SET cnt = cnt + 1
WHERE communitylikesno = 3;

-- UPDATE : 커뮤니티 게시글의 좋아요 수 감소
UPDATE communitylikes
SET cnt = cnt - 1
WHERE communityno = 3 AND cnt > 0;

-----------------------------------
-- DELETE: 커뮤니티 게시글의 좋아요 
-- 회원 삭제시 회원의 좋아요 취소?
-- ex)B회원의 개시글에 회원A가 좋아요 한 경우 취소 
------------------------------------

DELETE FROm communitylikes 
WHERE communitylikesno = 1 or communitylikesno = 2;

rollback;