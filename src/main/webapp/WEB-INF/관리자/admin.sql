/**********************************/
/* Table Name: 관리자 */
/**********************************/
DROP TABLE ADMIN CASCADE CONSTRAINTS;
DROP TABLE ADMIN;

CREATE TABLE ADMIN(
		ADMINNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(42)		 NOT NULL,
		ID                            		VARCHAR2(30)		 NOT NULL,
		PASSWORD                        		VARCHAR2(120)		 NOT NULL,
    EMAIL                             VARCHAR2(30)      NULL,
		RDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE ADMIN is '관리자';
COMMENT ON COLUMN ADMIN.ADMINNO is '관리자 번호';
COMMENT ON COLUMN ADMIN.NAME is '관리자명';
COMMENT ON COLUMN ADMIN.ID is 'ID';
COMMENT ON COLUMN ADMIN.PASSWORD is '비밀번호';
COMMENT ON COLUMN ADMIN.EMAIL is '이메일';
COMMENT ON COLUMN ADMIN.RDATE is '가입일';

DROP SEQUENCE admin_seq;

CREATE SEQUENCE admin_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 