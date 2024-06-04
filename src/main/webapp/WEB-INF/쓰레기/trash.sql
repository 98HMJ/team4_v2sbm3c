/**********************************/
/* Table Name: 쓰레기 종류 */
/**********************************/
DROP TABLE TRASH CASCADE CONSTRAINTS;
DROP TABLE TRASH;

CREATE TABLE TRASH(
		TRASHNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		ISRECYCLE                     		CHAR(1)		 DEFAULT 'N'		 NOT NULL,
		TAG                           		VARCHAR2(100)		 NULL ,
		PROCESS                       		VARCHAR2(3000)		 NOT NULL,
		TIP                           		VARCHAR2(3000)		 NULL ,
		file1                         		VARCHAR2(1000)		 NULL ,
		TRASHCATENO                   		NUMBER(10)		 NULL ,
  FOREIGN KEY (TRASHCATENO) REFERENCES TRASHCATE (TRASHCATENO)
);

COMMENT ON TABLE TRASH is '쓰레기 종류';
COMMENT ON COLUMN TRASH.TRASHNO is '쓰레기 종류 번호';
COMMENT ON COLUMN TRASH.NAME is '쓰레기 이름';
COMMENT ON COLUMN TRASH.ISRECYCLE is '재활용 여부';
COMMENT ON COLUMN TRASH.TAG is '태그';
COMMENT ON COLUMN TRASH.PROCESS is '버리는 방법';
COMMENT ON COLUMN TRASH.TIP is '유의할 점';
COMMENT ON COLUMN TRASH.file1 is '사진';
COMMENT ON COLUMN TRASH.TRASHCATENO is '쓰레기 분류 번호';

DROP SEQUENCE trash_seq;

CREATE SEQUENCE trash_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 
  
  
  -- 등록
/**********************************/
/* Table Name: 쓰레기 종류 */
/**********************************/
DROP TABLE TRASH CASCADE CONSTRAINTS;
DROP TABLE TRASH;

CREATE TABLE TRASH(
		TRASHNO                       		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		NAME                          		VARCHAR2(100)		 NOT NULL,
		ISRECYCLE                     		CHAR(1)		 DEFAULT 'N'		 NOT NULL,
		TAG                           		VARCHAR2(100)		 NULL ,
		PROCESS                       		VARCHAR2(3000)		 NOT NULL,
		TIP                           		VARCHAR2(3000)		 NULL ,
		file1                         		VARCHAR2(1000)		 NULL ,
		TRASHCATENO                   		NUMBER(10)		 NULL ,
  FOREIGN KEY (TRASHCATENO) REFERENCES TRASHCATE (TRASHCATENO)
);

COMMENT ON TABLE TRASH is '쓰레기 종류';
COMMENT ON COLUMN TRASH.TRASHNO is '쓰레기 종류 번호';
COMMENT ON COLUMN TRASH.NAME is '쓰레기 이름';
COMMENT ON COLUMN TRASH.ISRECYCLE is '재활용 여부';
COMMENT ON COLUMN TRASH.TAG is '태그';
COMMENT ON COLUMN TRASH.PROCESS is '버리는 방법';
COMMENT ON COLUMN TRASH.TIP is '유의할 점';
COMMENT ON COLUMN TRASH.file1 is '사진';
COMMENT ON COLUMN TRASH.TRASHCATENO is '쓰레기 분류 번호';

DROP SEQUENCE trash_seq;

CREATE SEQUENCE trash_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 
  
  
  -- 등록
INSERT INTO trash(trashno, name, isrecycle, tag, process, tip, file1, trashcateno)
VALUES(trash_seq.nextval, '휴지', 'N', '#일반쓰레기', '종량제 봉투에 넣어서 일반 쓰레기로 배출', '발로 밟아 압축하면 쓰레기 봉투를 아낄 수 있어요.', '일반쓰레기_t.jpg', 1);

-- 전체 목록
SELECT trashno, name, isrecycle, tag, process, tip, file1, trashcateno
FROM trash
ORDER BY trashno ASC;

-- 1번 trashno 만 출력
SELECT *
FROM trash
WHERE trashno=18;

-- 삭제
DELETE FROM trash
WHERE trashno = 1;

DELETE FROM contents
WHERE trash=12 AND trash <= 41;

-- 수정
UPDATE trash
SET thumb1 = "갑티슈_t.jpg"
WHERE trashno = 18;

-- 검색
SELECT trashno, name, isrecycle, tag, process, tip, file1, trashcateno
FROM trash
WHERE name LIKE UPPER('%휴지%') OR tag LIKE UPPER('%휴지%')
ORDER BY trashno ASC;

-- MyBATIS 사용
SELECT trashno, name, isrecycle, tag, process, tip, file1, trashcateno
FROM trash
WHERE UPPER(name) LIKE '%' || UPPER('휴지') || '%' OR UPPER(tag) LIKE '%' || UPPER('휴지') || '%'
ORDER BY trashno ASC;

file1                                   VARCHAR(100)          NULL,  -- 원본 파일명 image
file1saved                            VARCHAR(100)          NULL,  -- 저장된 파일명, image
thumb1                              VARCHAR(100)          NULL,   -- preview image
size1                                 NUMBER(10)      DEFAULT 0 NULL,  -- 파일 사이즈

ALTER TABLE trash rename column file1 to FILE1;
ALTER TABLE trash ADD FILE1SAVED varchar(100) not null;
ALTER TABLE trash ADD THUMB1 varchar(100) not null;
ALTER TABLE trash ADD SIZE1 NUMBER(10) NOT NULL;

alter table trash modify file1saved null;
alter table trash modify thumb1  null;
alter table trash modify size1 null;

select * from trash;

commit;

delete from trash
where trashno between 6 and 14;



-- 전체 목록
SELECT trashno, name, isrecycle, tag, process, tip, file1, trashcateno
FROM trash
ORDER BY trashno ASC;

-- 1번 trashno 만 출력
SELECT *
FROM trash
WHERE trashno=26;

-- 삭제
DELETE FROM trash
WHERE trashno = 1;

DELETE FROM contents
WHERE trash=12 AND trash <= 41;

-- 수정
UPDATE trash
SET file1saved="박스_2.jpeg",thumb1="박스_2_t.jpg"
WHERE trashno = 26;

UPDATE trash
SET trashno = 6
WHERE trashno = 67;

commit;

-- 검색
SELECT trashno, name, isrecycle, tag, process, tip, file1, trashcateno
FROM trash
WHERE name LIKE UPPER('%휴지%') OR tag LIKE UPPER('%휴지%')
ORDER BY trashno ASC;

-- MyBATIS 사용
SELECT trashno, name, isrecycle, tag, process, tip, file1, trashcateno
FROM trash
WHERE UPPER(name) LIKE '%' || UPPER('휴지') || '%' OR UPPER(tag) LIKE '%' || UPPER('휴지') || '%'
ORDER BY trashno ASC;

file1                                   VARCHAR(100)          NULL,  -- 원본 파일명 image
file1saved                            VARCHAR(100)          NULL,  -- 저장된 파일명, image
thumb1                              VARCHAR(100)          NULL,   -- preview image
size1                                 NUMBER(10)      DEFAULT 0 NULL,  -- 파일 사이즈

ALTER TABLE trash rename column file1 to FILE1;
ALTER TABLE trash ADD FILE1SAVED varchar(100) not null;
ALTER TABLE trash ADD THUMB1 varchar(100) not null;
ALTER TABLE trash ADD SIZE1 NUMBER(10) NOT NULL;

alter table trash modify file1saved null;
alter table trash modify thumb1  null;
alter table trash modify size1 null;

select * from trash;

commit;

delete from trash
where trashno between 6 and 14;

commit;
