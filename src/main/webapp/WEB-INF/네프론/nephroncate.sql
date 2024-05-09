/**********************************/
/* Table Name: 네프론 */
/**********************************/
DROP TABLE NEPHRONCATE CASCADE CONSTRAINTS;
DROP TABLE NEPHRONCATE;

CREATE TABLE NEPHRONCATE(
		NEPHRONCATENO                 		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NEPHRONNO                     		NUMBER(10)		 NULL ,
		TRASHCATENO                   		NUMBER(10)		 NULL ,
  FOREIGN KEY (NEPHRONNO) REFERENCES NEPHRONPOINT (NEPHRONNO),
  FOREIGN KEY (TRASHCATENO) REFERENCES TRASHCATE (TRASHCATENO)
);

COMMENT ON TABLE NEPHRONCATE is '네프론';
COMMENT ON COLUMN NEPHRONCATE.NEPHRONCATENO is 'NEPHRON번호';
COMMENT ON COLUMN NEPHRONCATE.NEPHRONNO is '네프론주소번호';
COMMENT ON COLUMN NEPHRONCATE.TRASHCATENO is '쓰레기 분류 번호';

DROP SEQUENCE nephroncate_seq;

CREATE SEQUENCE nephroncate_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 
  
  --삽입--  
INSERT INTO nephroncate(nephroncateno,nephronno, trashcateno) 
VALUES (nephroncate_seq.nextval,1,1);

-- 전체 목록
SELECT nephroncateno,nephronno, trashcateno
FROM nephroncate
ORDER BY nephroncateno ASC;

-- 삭제
DELETE FROM nephroncateno
WHERE nephroncateno = 25;

-- 수정

UPDATE nephroncate
SET nephronno = 1
WHERE nephroncateno = 12;
