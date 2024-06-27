/**********************************/
/* Table Name: AI_기록 */
/**********************************/
DROP TABLE AI_HISTORY CASCADE CONSTRAINTS;
DROP TABLE AI_HISTORY;

CREATE TABLE AI_HISTORY(
		historyno                     		NUMBER(10)		  NOT NULL		 PRIMARY KEY,
    explaination                     	VARCHAR2(3000)  NOT NULL,
		sortno                        		NUMBER(10)		  NOT NULL,
    rdate                             VARCHAR2(15)            NOT NULL,
		memberno                      		NUMBER(10)		  NOT NULL ,
  FOREIGN KEY (sortno) REFERENCES AI_SORT (sortno),
  FOREIGN KEY (memberno) REFERENCES MEMBER (memberno)
);

COMMENT ON TABLE AI_HISTORY is 'AI_기록';
COMMENT ON COLUMN AI_HISTORY.historyno is '기록 번호';
COMMENT ON COLUMN AI_HISTORY.explaination is '설명';
COMMENT ON COLUMN AI_HISTORY.rdate is '기록일';
COMMENT ON COLUMN AI_HISTORY.sortno is '분류번호';
COMMENT ON COLUMN AI_HISTORY.MEMBERNO is '회원번호';

DROP SEQUENCE aiHistory_seq;

CREATE SEQUENCE aiHistory_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 
  
  --삽입--  
INSERT INTO ai_history(historyno, explaination, sortno, rdate, memberno) 
VALUES (aiHistory_seq.nextval, '이 쓰레기는 유리 입니다.', 2, sysdate, 1);

-- 페이징
SELECT historyno, explaination, sortno, rdate, memberno, r
FROM (
      SELECT historyno, explaination, sortno, rdate, memberno, rownum as r
      FROM (
            SELECT historyno, explaination, sortno, rdate, memberno
            FROM ai_history     
            ORDER BY rdate DESC
      )
)
WHERE r <= 500;

DELETE FROM ai_history;

commit;