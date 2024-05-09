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
		PHOTO                         		VARCHAR2(1000)		 NULL ,
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
COMMENT ON COLUMN TRASH.PHOTO is '사진';
COMMENT ON COLUMN TRASH.TRASHCATENO is '쓰레기 분류 번호';

DROP SEQUENCE trash_seq;

CREATE SEQUENCE trash_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 
  
  
  -- 등록
INSERT INTO trash(trashno, name, isrecycle, tag, process, tip, photo, trashcateno)
VALUES(trash_seq.nextval, '휴지', 'N', '#일반쓰레기', '종량제 봉투에 넣어서 일반 쓰레기로 배출', '발로 밟아 압축하면 쓰레기 봉투를 아낄 수 있어요.', 'tissue.jpg', 1);

-- 전체 목록
SELECT trashno, name, isrecycle, tag, process, tip, photo, trashcateno
FROM trash
ORDER BY trashno ASC;

-- 1번 trashno 만 출력
SELECT trashno, name, isrecycle, tag, process, tip, photo, trashcateno
FROM trash
WHERE trashno=1;

-- 삭제
DELETE FROM trash
WHERE trashno = 25;

DELETE FROM contents
WHERE trash=12 AND trash <= 41;

-- 수정
UPDATE trash
SET tag ='#휴지 #일반쓰레기 #종량제봉투', tip='', photo='tissue2.jpg'
WHERE trashno = 1;

-- 검색
SELECT trashno, name, isrecycle, tag, process, tip, photo, trashcateno
FROM trash
WHERE name LIKE UPPER('%휴지%') OR tag LIKE UPPER('%휴지%')
ORDER BY trashno ASC;

-- MyBATIS 사용
SELECT trashno, name, isrecycle, tag, process, tip, photo, trashcateno
FROM trash
WHERE UPPER(name) LIKE '%' || UPPER('휴지') || '%' OR UPPER(tag) LIKE '%' || UPPER('휴지') || '%'
ORDER BY trashno ASC;