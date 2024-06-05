/**********************************/
/* Table Name: 즐겨찾기 */
/**********************************/
DROP TABLE BOOKMARK CASCADE CONSTRAINTS;
DROP TABLE BOOKMARK;

CREATE TABLE BOOKMARK (
    BOOKMARKNO    NUMBER(10)        NOT NULL PRIMARY KEY,
    RDATE         DATE              NOT NULL,
    url           VARCHAR2(2000)    NOT NULL,
    COMMUNITYNO   NUMBER(10),
    TRASHNO       NUMBER(10),
    MEMBERNO      NUMBER(10)        NOT NULL,
    CONSTRAINT FK_BOOKMARK_COMMUNITY FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO) ON DELETE CASCADE,
    CONSTRAINT FK_BOOKMARK_TRASH FOREIGN KEY (TRASHNO) REFERENCES TRASH (TRASHNO) ON DELETE CASCADE,
    CONSTRAINT FK_BOOKMARK_MEMBER FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO) ON DELETE CASCADE
);

COMMENT ON TABLE BOOKMARK is '즐겨찾기';
COMMENT ON COLUMN BOOKMARK.BOOKMARKNO is '북마크번호';
COMMENT ON COLUMN BOOKMARK.URL is '북마크 URL';
COMMENT ON COLUMN BOOKMARK.RDATE is '북마크 한 시각';
COMMENT ON COLUMN BOOKMARK.COMMUNITYNO is '커뮤니티번호';
COMMENT ON COLUMN BOOKMARK.TRASHNO is '쓰레기번호';
COMMENT ON COLUMN BOOKMARK.MEMBERNO is '회원번호';

DROP SEQUENCE bookmark_seq;

CREATE SEQUENCE bookmark_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;

commit;
-- 삽입 연산은 communityno, trashno 둘 중 1개만 할 수 있음
INSERT INTO bookmark(bookmarkno, rdate, url, communityno, trashno, memberno)
VALUES (bookmark_seq.nextval, sysdate, 'communityno=12', 39, null, 10);

INSERT INTO bookmark(bookmarkno, rdate, url, communityno, trashno, memberno)
VALUES (bookmark_seq.nextval, sysdate, 'trashno=12', null, 35, 10);

commit;

-- 조회(확인) : 커뮤니티 글에 북마크가 등록이 되어있는지 확인
SELECT COUNT(bookmarkno) as cnt
FROM bookmark
WHERE communityno = 39 and memberno = 10;

-- 조회(확인) : 쓰레기 글에 북마크가 등록이 되어있는지 확인
SELECT COUNT(bookmarkno) as cnt
FROM bookmark
WHERE trashno = 35 and memberno = 10;

-- 멤버의 모든 북마크 목록
SELECT b.bookmarkno, 
       COALESCE(tc.name, cc.name) AS category_name, 
       b.rdate, 
       b.url, 
       COALESCE(b.trashno, b.communityno) AS ref_no, 
       b.memberno,
       CASE 
           WHEN b.trashno IS NOT NULL THEN 'trash' 
           ELSE 'community' 
       END AS board
FROM bookmark b
LEFT JOIN trash t ON b.trashno = t.trashno
LEFT JOIN trashcate tc ON t.trashcateno = tc.trashcateno
LEFT JOIN community c ON b.communityno = c.communityno
LEFT JOIN communitycate cc ON c.communitycateno = cc.communitycateno
WHERE b.memberno = 10;
    
-- 목록: 커뮤니티 북마크
SELECT b.bookmarkno, cc.name, b.rdate, b.url, b.communityno, b.memberno
FROM community c, bookmark b, communitycate cc
WHERE b.communityno = c.communityno and cc.communitycateno = c.communitycateno
    and  b.memberno = 10;

-- 조회: 커뮤니티의 특정 카테고리의 북마크 
SELECT b.bookmarkno, cc.name, b.rdate, b.url, b.communityno, b.memberno
FROM community c, bookmark b, communitycate cc, member m
WHERE b.communityno = c.communityno and cc.communitycateno = c.communitycateno
    and b.memberno = m.memberno and b.memberno = 10 and cc.communitycateno = 3;


-- 목록: 쓰레기 북마크 
SELECT b.bookmarkno, tc.name, b.rdate, b.url, b.trashno, b.memberno
FROM trash t, bookmark b, trashcate tc
WHERE b.trashno = t.trashno and tc.trashcateno = t.trashcateno
    and b.memberno = 10;

-- 조회: 쓰레기의 특정 카테고리의 북마크
SELECT b.bookmarkno, tc.name, b.rdate, b.url, b.trashno, b.memberno
FROM trash t, bookmark b, trashcate tc, member m
WHERE b.trashno = t.trashno and tc.trashcateno = t.trashcateno
    and b.memberno = m.memberno and b.memberno = 10 and tc.trashcateno = 3;
     
DELETE FROM bookmark;
commit;

-- 북마크(커뮤니티 글) 1개 삭제
DELETE FROM bookmark
WHERE communityno = 39 and memberno = 10;

rollback;

commit;

-- 북마크(쓰레기) 1개 삭제
DELETE FROM bookmark
WHERE trashno = 17
AND bookmarkno = (SELECT b.bookmarkno
                  FROM trash t, bookmark b, trashcate tc
                  WHERE b.trashno = t.trashno 
                  AND b.trashno = 17
                  AND ROWNUM = 1);
                  