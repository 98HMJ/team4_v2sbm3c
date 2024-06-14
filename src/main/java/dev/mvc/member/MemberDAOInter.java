package dev.mvc.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * MemberDAOInter
 */
public interface MemberDAOInter {

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
     * 회원 정보 조회(회원 번호)
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

    /**
     * 관리자 멤버 목록 검색 페이징
     * 
     * @param map
     * @return
     */
    public ArrayList<MemberVO> list(Map<String, Object> map);

    /**
     * 검색 레코드 수
     * 
     * @param word
     * @return
     */
    public int list_cnt(String word);
}