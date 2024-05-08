/**********************************/
/* Table Name: 쓰레기 분류 */
/**********************************/
DROP TABLE TRASHCATE CASCADE CONSTRAINTS;
DROP TABLE TRASHCATE;

CREATE TABLE TRASHCATE(
		TRASHCATENO                   		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(30)		 DEFAULT '일반쓰레기'		 NOT NULL
);

COMMENT ON TABLE TRASHCATE is '쓰레기 분류';
COMMENT ON COLUMN TRASHCATE.TRASHCATENO is '쓰레기 분류 번호';
COMMENT ON COLUMN TRASHCATE.NAME is '분류';

DROP SEQUENCE trashcate_seq;

CREATE SEQUENCE trashcate_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지  
  
--삽입--  
INSERT INTO TRASHCATE(TRASHCATENO,NAME) VALUES (trashcate_seq.nextval,'기타');