/**********************************/
/* Table Name: 커뮤니티 */
/**********************************/
DROP TABLE COMMUNITY CASCADE CONSTRAINTS;
DROP TABLE COMMUNITY;

CREATE TABLE COMMUNITY(
		COMMUNITYNO                   		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		TITLE                         		VARCHAR2(30)		 NOT NULL,
		CONTENTS                      		VARCHAR2(2000)		 DEFAULT '-'		 NOT NULL,
		RDATE                         		DATE		 NOT NULL,
		FILES                         		VARCHAR2(100)		 NULL ,
		FILESAVED                     		VARCHAR2(100)		 NULL ,
		THUMB                         		VARCHAR2(100)		 NULL ,
		FILESIZE                      		NUMBER(10)		 DEFAULT 0		 NULL ,
		MAP                           		VARCHAR2(1000)		 NULL ,
		YOUTUBE                       		VARCHAR2(1000)		 NULL ,
		MP4                           		VARCHAR2(1000)		 NULL ,
		CNT                           		NUMBER(10)		 DEFAULT 0		 NOT NULL,
		MEMBERNO                      		NUMBER(10)		 NULL ,
		COMMUNITYCATENO               		NUMBER(10)		 NULL ,
  FOREIGN KEY (MEMBERNO) REFERENCES MEMBER (MEMBERNO),
  FOREIGN KEY (COMMUNITYCATENO) REFERENCES COMMUNITYCATE (COMMUNITYCATENO)
);

COMMENT ON TABLE COMMUNITY is '커뮤니티';
COMMENT ON COLUMN COMMUNITY.COMMUNITYNO is '커뮤니티번호';
COMMENT ON COLUMN COMMUNITY.TITLE is '글 제목';
COMMENT ON COLUMN COMMUNITY.CONTENTS is '글 내용';
COMMENT ON COLUMN COMMUNITY.RDATE is '등록일';
COMMENT ON COLUMN COMMUNITY.FILES is '원본 파일명';
COMMENT ON COLUMN COMMUNITY.FILESAVED is '저장된 파일명';
COMMENT ON COLUMN COMMUNITY.THUMB is '썸네일 이미지';
COMMENT ON COLUMN COMMUNITY.FILESIZE is '파일 사이즈';
COMMENT ON COLUMN COMMUNITY.MAP is '지도';
COMMENT ON COLUMN COMMUNITY.YOUTUBE is '유튜브';
COMMENT ON COLUMN COMMUNITY.MP4 is 'MP4';
COMMENT ON COLUMN COMMUNITY.CNT is '조회수';
COMMENT ON COLUMN COMMUNITY.MEMBERNO is '회원번호';
COMMENT ON COLUMN COMMUNITY.COMMUNITYCATENO is '커뮤니티카테번호';

DROP SEQUENCE community_seq;

CREATE SEQUENCE community_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                      -- 다시 1부터 생성되는 것을 방지
  
--삽입--
INSERT INTO community(communityno, title, contents, rdate, memberno, communitycateno) 
VALUES(community_seq.nextval,'안녕하세요','처음 글을 씁니다.',sysdate,1,3);

--조회--
SELECT files FROM community;

SELECT CONSTRAINT_NAME
FROM USER_CONSTRAINTS
WHERE TABLE_NAME = 'COMMUNITY' AND CONSTRAINT_TYPE = 'R';

ALTER TABLE COMMUNITY
DROP CONSTRAINT SYS_C007713;

ALTER TABLE COMMUNITY
ADD CONSTRAINT community_cateno FOREIGN KEY (COMMUNITYCATENO)
REFERENCES COMMUNITYCATE (COMMUNITYCATENO)
ON DELETE CASCADE;

commit;