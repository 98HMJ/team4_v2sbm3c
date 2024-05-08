/**********************************/
/* Table Name: 커뮤니티 분류 */
/**********************************/
DROP TABLE COMMUNITYCATE CASCADE CONSTRAINTS;
DROP TABLE COMMUNITYCATE;
CREATE TABLE COMMUNITYCATE(
		COMMUNITYCATENO               		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(30)		 NOT NULL
);

COMMENT ON TABLE COMMUNITYCATE is '커뮤니티 분류';
COMMENT ON COLUMN COMMUNITYCATE.COMMUNITYCATENO is '커뮤니티카테번호';
COMMENT ON COLUMN COMMUNITYCATE.NAME is '카테고리이름';

DROP SEQUENCE communitycate_seq;

CREATE SEQUENCE communitycate_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지