package dev.mvc.log.memberlog;

import java.util.ArrayList;
import java.util.HashMap;


public interface MemberlogDAOInter {
  /**
   * 회원 로그인 내역 생성
   * @param memberlogVO
   * @return 추가 레코드 수
   */
  public int create(MemberlogVO memberlogVO);
  
  /**
   * 회원 로그 데이터 전체 조회
   * @return 모든 회원 로그인 내역
   */
  public ArrayList<MemberlogVO> list();

  /**
   * memberlogno로 회원 로그인 내역 조회
   * @return memberlogno에 해당하는 회원 로그인 정보
   */
  public MemberlogVO read(int memberlogno);

  /**
   * memberno로 회원 로그인 내역 조회
   * @param memberno
   * @return memberno로 회원 로그인 내역
   */
  public ArrayList<MemberlogVO> list_memberno(int memberno);


  /**
   * 로그인 내역 페이징
   * @param map
   * @return
   */
  public ArrayList<MemberlogVO> list_paging(HashMap<String,Object> map);

  /**
   * 검색 레코드 수
   * @param word
   * @return
   */
  public int list_cnt(HashMap<String,Object> map);
}
