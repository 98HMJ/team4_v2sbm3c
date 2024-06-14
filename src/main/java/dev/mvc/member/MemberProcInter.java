package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public interface MemberProcInter {

    /**
     * 회원가입
     * 
     * @param memberVO
     * @return int
     */
    public int create(MemberVO memberVO);

    /**
     * 회원가입시 중복 아이디 확인
     * 
     * @param id
     * @return int
     */
    public int checkid(String id);

    /**
     * 회원 로그인
     * 
     * @param memberVO
     * @return int
     */
    public int login(HashMap<String, Object> map);

    /**
     * 관리자 멤버 목록 검색 페이징
     * 
     * @param map
     * @return
     */
    public ArrayList<MemberVO> list(String word, int now_page, int record_per_page);

    /**
     * 검색된 레코드 수
     * 
     * @param word
     * @return
     */
    public int list_cnt(String word);

    /**
     * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작
     * 현재 페이지: 11 / 22 [이전] 11 12 13 14 15 16 17 18 19 20 [다음]
     *
     * @param now_page        현재 페이지
     * @param word            검색어
     * @param list_file       목록 파일명
     * @param search_count    검색 레코드수
     * @param record_per_page 페이지당 레코드 수
     * @param page_per_block  블럭당 페이지 수
     * @return 페이징 생성 문자열
     */
    public String pagingBox(int now_page, String word, String list_file, int search_count,
            int record_per_page, int page_per_block);

    /**
     * 회원 정보 조회
     * 
     * @param memberno
     * @return MemberVO
     */
    public MemberVO read(int memberno);

    /**
     * 회원 정보 조회(회원 아이디)
     * 
     * @param id
     * @return MemberVO
     */
    public MemberVO readByid(String id);

    /**
     * 회원 정보 수정
     * 
     * @param memberVO
     * @return int
     */
    public int update(MemberVO memberVO);

    /**
     * 회원 삭제
     * 
     * @param memberVO
     * @return int
     */
    public int delete(int memberno);

    /**
     * 회원 아이디 찾기
     * 
     * @param map
     * @return memberVO
     */
    public MemberVO findid(HashMap<String, String> map);

    /**
     * 회원 패스워드 찾기
     * 
     * @param map
     * @return memberVO
     */
    public MemberVO findpassword(HashMap<String, Object> map);

    /**
     * 회원 패스워드 수정
     * 
     * @param map
     * @return memberVO
     */
    public int changepassword(HashMap<String, Object> map);

}
