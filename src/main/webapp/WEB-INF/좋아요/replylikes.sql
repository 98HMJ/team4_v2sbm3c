/**********************************/
/* Table Name: 댓글 좋아요 */
/**********************************/
DROP TABLE REPLYLIKES CASCADE CONSTRAINTS;
DROP TABLE REPLYLIKES;


CREATE TABLE REPLYLIKES(
		REPLYLIKESNO                  		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CNT                           		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		REPLYNO                       		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (REPLYNO) REFERENCES REPLY (REPLYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
);

COMMENT ON TABLE REPLYLIKES is '댓글 좋아요';
COMMENT ON COLUMN REPLYLIKES.REPLYLIKESNO is '댓글좋아요번호';
COMMENT ON COLUMN REPLYLIKES.CNT is '좋아요수';
COMMENT ON COLUMN REPLYLIKES.REPLYNO is '댓글번호';
COMMENT ON COLUMN REPLYLIKES.MEMBERNO is '회원번호';

DROP SEQUENCE replylikes_seq;

CREATE SEQUENCE replylikes_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 