/**********************************/
/* Table Name: 회원 로그인 내역 */
/**********************************/
DROP TABLE MEMBERLOG CASCADE CONSTRAINTS;
DROP TABLE MEMBERLOG;

CREATE TABLE MEMBERLOG(
		MEMBERLOGNO                   		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		IP                            		VARCHAR2(20)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE MEMBERLOG is '회원 로그인 내역';
COMMENT ON COLUMN MEMBERLOG.MEMBERLOGNO is '회원 로그인 내역 번호';
COMMENT ON COLUMN MEMBERLOG.IP is '접속 IP';
COMMENT ON COLUMN MEMBERLOG.RDATE is '로그인 시간';
COMMENT ON COLUMN MEMBERLOG.MEMBERNO is '회원번호';

DROP SEQUENCE memberlog_seq;

CREATE SEQUENCE memberlog_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 