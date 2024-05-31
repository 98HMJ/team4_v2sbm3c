/**********************************/
/* Table Name: 즐겨찾기 */
/**********************************/
DROP TABLE BOOKMARK CASCADE CONSTRAINTS;
DROP TABLE BOOKMARK;

CREATE TABLE BOOKMARK (
    BOOKMARKNO    NUMBER(10)  NOT NULL PRIMARY KEY,
    RDATE         DATE        NOT NULL,
    COMMUNITYNO   NUMBER(10),
    TRASHNO       NUMBER(10),
    MEMBERNO      NUMBER(10)   NOT NULL,
    CONSTRAINT FK_BOOKMARK_COMMUNITY FOREIGN KEY (COMMUNITYNO) REFERENCES COMMUNITY (COMMUNITYNO) ON DELETE CASCADE,
    CONSTRAINT FK_BOOKMARK_TRASH FOREIGN KEY (TRASHNO) REFERENCES TRASH (TRASHNO) ON DELETE CASCADE,
    CONSTRAINT FK_BOOKMARK_MEMBER FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO) ON DELETE CASCADE,
    CONSTRAINT CHK_BOOKMARK_COMMUNITY_TRASH CHECK (
        (COMMUNITYNO IS NOT NULL AND TRASHNO IS NULL) OR 
        (COMMUNITYNO IS NULL AND TRASHNO IS NOT NULL)
    )
);

COMMENT ON TABLE BOOKMARK is '즐겨찾기';
COMMENT ON COLUMN BOOKMARK.BOOKMARKNO is '북마크번호';
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

-- 삽입 연산은 communityno, trashno 둘 중 1개만 할 수 있음
INSERT INTO bookmark(bookmarkno, rdate, communityno, memberno)
VALUES (bookmark_seq.nextval, sysdate, 13, 1);

INSERT INTO bookmark(bookmarkno, rdate, trashno, memberno)
VALUES (bookmark_seq.nextval, sysdate, 17, 1);

commit;

-- 전체 북마크 목록 조회
SELECT bookmarkno, rdate, communityno,trashno, memberno
FROM bookmark;

-- 목록: 커뮤니티 북마크 
SELECT b.bookmarkno, cc.name, b.rdate, b.communityno, b.memberno
FROM community c, bookmark b, communitycate cc
WHERE b.communityno = c.communityno and b.communityno = 13 and cc.communitycateno = c.communitycateno;

-- 목록: 쓰레기 북마크 
SELECT b.bookmarkno, tc.name, b.rdate, b.trashno, b.memberno
FROM trash t, bookmark b, trashcate tc
WHERE b.trashno = t.trashno and b.trashno = 17 and tc.trashcateno = t.trashcateno;

DELETE FROM bookmark
WHERE trashno = 17
AND bookmarkno = (SELECT b.bookmarkno
                  FROM trash t, bookmark b, trashcate tc
                  WHERE b.trashno = t.trashno 
                  AND b.trashno = 17 
                  AND ROWNUM = 1);