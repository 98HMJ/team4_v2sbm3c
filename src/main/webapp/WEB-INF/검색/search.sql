/**********************************/
/* Table Name: 검색 */
/**********************************/
DROP TABLE SEARCH;

CREATE TABLE SEARCH(
		SEARCHNO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		SEARCH_WORD                   		VARCHAR2(100)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE SEARCH is '검색';
COMMENT ON COLUMN SEARCH.SEARCHNO is '검색번호';
COMMENT ON COLUMN SEARCH.SEARCH_WORD is '검색어';
COMMENT ON COLUMN SEARCH.RDATE is '검색시간';

DROP SEQUENCE search_seq;

CREATE SEQUENCE search_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 
  
  
-- 등록
INSERT INTO SEARCH(searchno, search_word, rdate) 
VALUES (search_seq.nextval,'종이', sysdate);

-- 조회
SELECT searchno, search_word, rdate
FROM search
ORDER BY searchno ASC;

-- 삭제
DELETE FROM search
WHERE searchno = 1;
