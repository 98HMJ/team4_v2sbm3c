/**********************************/
/* Table Name: 쓰레기통 위치 */
/**********************************/
DROP TABLE NEPHRONPOINT CASCADE CONSTRAINTS;
DROP TABLE NEPHRONPOINT;
CREATE TABLE NEPHRONPOINT(
		NEPHRONNO                     		NUMBER(10)		 NOT NULL		 PRIMARY KEY,
		ROADADDRESS                   		VARCHAR2(120)		 NOT NULL,
		DETAILADDRESS                 		VARCHAR2(120)		 NOT NULL
);

COMMENT ON TABLE NEPHRONPOINT is '쓰레기통 위치';
COMMENT ON COLUMN NEPHRONPOINT.NEPHRONNO is '네프론주소번호';
COMMENT ON COLUMN NEPHRONPOINT.ROADADDRESS is '도로명주소';
COMMENT ON COLUMN NEPHRONPOINT.DETAILADDRESS is '세부위치';

DROP SEQUENCE nephronpoint_seq;

CREATE SEQUENCE nephronpoint_seq
  START WITH 1                -- 시작 번호
  INCREMENT BY 1            -- 증가값
  MAXVALUE 9999999999  -- 최대값: 9999999999 --> NUMBER(10) 대응
  CACHE 2                        -- 2번은 메모리에서만 계산
  NOCYCLE;                  -- 다시 1부터 생성되는 것을 방지 
  
SELECT nephronno, roadaddress, detailaddress
FROM nephronpoint;


  -- 등록
INSERT INTO nephronpoint(nephronno, roadaddress, detailaddress)
VALUES(nephron_seq.nextval, '서울특별시', '주민센터');

-- 전체 목록
SELECT nephronno, roadaddress, detailaddress
FROM nephronpoint
ORDER BY nephronno ASC;

-- 1번만 출력
SELECT nephronno, roadaddress, detailaddress
FROM nephronpoint
WHERE nephronno=1;

-- 삭제
DELETE FROM nephronpoint
WHERE nephronno = 25;


-- 검색
SELECT nephronno, roadaddress, detailaddress
FROM nephronpoint
WHERE roadaddress LIKE UPPER('%종로%') OR detailaddress LIKE UPPER('%종로%')
ORDER BY nephronno ASC;

-- MyBATIS 사용
SELECT nephronno, roadaddress, detailaddress
FROM nephronpoint
WHERE UPPER(roadaddress) LIKE '%' || UPPER('휴지') || '%' OR UPPER(detailaddress) LIKE '%' || UPPER('휴지') || '%'
ORDER BY nephronno ASC;


SELECT nephronno, sum (trashcateno) as total
FROM nephroncate
GROUP BY nephronno
ORDER BY nephronno;

select a.nephronno, b.total, a.roadaddress, a.detailaddress
from nephronpoint a,(select nephronno, sum(nephroncate.trashcateno) as total from nephroncate group by nephronno) b
where a.nephronno = b.nephronno;
