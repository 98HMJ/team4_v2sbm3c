/**********************************/
/* Table Name: 신고 및 요청 */
/**********************************/
DROP TABLE SINGO CASCADE CONSTRAINTS;
DROP TABLE SINGO;

CREATE TABLE SINGO(
		SINGONO                       		NUMBER(10)		 NULL ,
		CONTENTS                      		VARCHAR2(3000)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		TRASHNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (TRASHNO) REFERENCES TRASH (TRASHNO)
);

COMMENT ON TABLE SINGO is '신고 및 요청';
COMMENT ON COLUMN SINGO.SINGONO is '문의사항번호';
COMMENT ON COLUMN SINGO.CONTENTS is '문의 내용';
COMMENT ON COLUMN SINGO.RDATE is '등록일';
COMMENT ON COLUMN SINGO.MEMBERNO is '회원번호';
COMMENT ON COLUMN SINGO.TRASHNO is '쓰레기 종류 번호';

DROP SEQUENCE singo_seq;

CREATE SEQUENCE singo_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 