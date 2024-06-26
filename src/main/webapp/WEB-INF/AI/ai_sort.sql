/**********************************/
/* Table Name: AI분류 */
/**********************************/
DROP TABLE AI_SORT CASCADE CONSTRAINTS;
DROP TABLE AI_SORT;

CREATE TABLE AI_SORT(
		sortno                        		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		name                          		VARCHAR2(3000)		 NOT NULL
);

COMMENT ON TABLE AI_SORT is 'AI분류';
COMMENT ON COLUMN AI_SORT.sortno is '분류번호';
COMMENT ON COLUMN AI_SORT.name is '분류명';

DROP SEQUENCE aisort_seq;

CREATE SEQUENCE aisort_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 
  
  --삽입--  
INSERT INTO AI_SORT(sortno, name) 
VALUES (aisort_seq.nextval, '유리');

INSERT INTO AI_SORT(sortno, name) 
VALUES (aisort_seq.nextval, '일반쓰레기');

INSERT INTO AI_SORT(sortno, name) 
VALUES (aisort_seq.nextval, '재활용');

DELETE FROM AI_SORT;

