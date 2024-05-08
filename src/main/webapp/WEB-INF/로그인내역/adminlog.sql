/**********************************/
/* Table Name: 관리자 로그인 내역 */
/**********************************/
DROP TABLE ADMINLOG CASCADE CONSTRAINTS;
DROP TABLE ADMINLOG;

CREATE TABLE ADMINLOG(
		ADMINLOGNO                    		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		IP                            		VARCHAR2(20)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		ADMINNO                       		NUMBER(10)		 NULL ,
  FOREIGN KEY (ADMINNO) REFERENCES ADMIN (ADMINNO)
);

COMMENT ON TABLE ADMINLOG is '관리자 로그인 내역';
COMMENT ON COLUMN ADMINLOG.ADMINLOGNO is '관리자 로그인 내역';
COMMENT ON COLUMN ADMINLOG.IP is 'IP';
COMMENT ON COLUMN ADMINLOG.RDATE is '로그인 시간';
COMMENT ON COLUMN ADMINLOG.ADMINNO is '관리자 번호';

DROP SEQUENCE adminlog_seq;

CREATE SEQUENCE adminlog_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 