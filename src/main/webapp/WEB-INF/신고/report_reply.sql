DROP TABLE REPORT_REPLY CASCADE CONSTRAINTS;
DROP TABLE REPORT_REPLY;
CREATE TABLE REPORT_REPLY(
		REPORTNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		CONTENTS                      		        VARCHAR2(2000)		 NOT NULL,
        RDATE                         		DATE		     NOT NULL,
        REPLYNO                   		NUMBER(10)		 NULL ,
		MEMBERNO                      		NUMBER(10)		 NULL ,
  FOREIGN KEY (REPLYNO) REFERENCES REPLY (REPLYNO),
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO)
    ON DELETE CASCADE
);


COMMENT ON TABLE REPORT_REPLY is '댓글 신고';
COMMENT ON COLUMN REPORT_REPLY.REPORTNO is '신고 번호';
COMMENT ON COLUMN REPORT_REPLY.CONTENTS is '신고 내용';
COMMENT ON COLUMN REPORT_REPLY.RDATE is '신고 시간';
COMMENT ON COLUMN REPORT_REPLY.REPLYNO is '댓글 번호';
COMMENT ON COLUMN REPORT_REPLY.MEMBERNO is '회원번호';

DROP SEQUENCE reply_report_seq;

CREATE SEQUENCE reply_report_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지

SELECT * FROM REPORT_REPLY;

-- 등록
INSERT INTO REPORT_REPLY(reportno, contents, rdate, replyno, memberno)
VALUES(reply_report_seq.nextval, '신고합니다.', sysdate, 2, 1);

commit;

-- 목록: 모든 목록 조회
SELECT reportno, contents, rdate, replyno, memberno
FROM report_reply;

-- 목록 : 멤버별 신고한 목록 
SELECT reportno, contents, rdate, replyno, memberno
FROM report_reply
WHERE memberno = 10;

-- 수정

-- 삭제
