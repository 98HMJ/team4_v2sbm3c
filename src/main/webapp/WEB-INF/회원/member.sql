/**********************************/
/* Table Name: 회원 */
/**********************************/
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE MEMBER;

CREATE TABLE MEMBER(
		MEMBERNO                      		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(20)		 NOT NULL,
		SEX                           		CHAR(1)		 NOT NULL,
		AGE                           		NUMBER(3)		 NOT NULL,
		ID                            		VARCHAR2(30)		 NOT NULL,
		PASSWORD                      		VARCHAR2(120)		 NOT NULL,
		NICKNAME                     		VARCHAR2(30)		 NOT NULL,
		TEL                           		VARCHAR2(20)		 NOT NULL,
		ADDRESS1                      		VARCHAR2(120)		 NOT NULL,
		ADDRESS2                      		VARCHAR2(120)		 NOT NULL,
		EMAIL                         		VARCHAR2(30)		 NOT NULL,
		RDATE                         		DATE		 NOT NULL
);

COMMENT ON TABLE MEMBER is '회원';
COMMENT ON COLUMN MEMBER.MEMBERNO is '회원번호';
COMMENT ON COLUMN MEMBER.NAME is '이름';
COMMENT ON COLUMN MEMBER.SEX is '성별';
COMMENT ON COLUMN MEMBER.AGE is '나이';
COMMENT ON COLUMN MEMBER.ID is 'id';
COMMENT ON COLUMN MEMBER.PASSWORD is '비밀번호';
COMMENT ON COLUMN MEMBER.NICKNAME is '닉네임';
COMMENT ON COLUMN MEMBER.TEL is '전화번호';
COMMENT ON COLUMN MEMBER.ADDRESS1 is '주소';
COMMENT ON COLUMN MEMBER.ADDRESS2 is '상세주소';
COMMENT ON COLUMN MEMBER.EMAIL is 'E-메일';
COMMENT ON COLUMN MEMBER.RDATE is '가입일';

DROP SEQUENCE member_seq;

CREATE SEQUENCE member_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지