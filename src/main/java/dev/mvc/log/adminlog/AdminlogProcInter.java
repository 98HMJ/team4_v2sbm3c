package dev.mvc.log.adminlog;

import java.util.ArrayList;

import dev.mvc.singo.SingoVO;

public interface AdminlogProcInter {
  /**
   * 관리자 로그인 내역 생성
   * @param adminlogVO
   * @return 추가 레코드 수
   */
  public int create(AdminlogVO adminlogVO);
  
  /**
   * 관리자 로그 데이터 전체 조회
   * @return 모든 관리자 로그인 내역
   */
  public ArrayList<AdminlogVO> list();

  /**
   * adminlogno로 관리자 로그인 내역 조회
   * @return adminlogno에 해당하는 관리자 로그인 정보
   */
  public AdminlogVO read(int adminlogno);

  /**
   * adminno로 관리자 로그인 내역 조회
   * @param adminno
   * @return adminno로 관리자 로그인 내역 조회
   */
  public ArrayList<AdminlogVO> list_adminno(int adminno);
  
  /**
   * 리스트 페이징
   * @param word
   * @param now_page
   * @param record_per_page
   * @param adminno
   * @return
   */
  public ArrayList<AdminlogVO> list_paging(String word, int now_page, int record_per_page, int adminno);

  /**
   * 검색된 레코드 수
   * @param word
   * @param adminno
   * @return
   */
  public int list_cnt(String word, int adminno);

  /** 
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param now_page  현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명
   * @param search_count 검색 레코드수   
   * @param record_per_page 페이지당 레코드 수
   * @param page_per_block 블럭당 페이지 수
   * @return 페이징 생성 문자열
   */ 
  public String pagingBox(int now_page, String word, String list_file, int search_count, 
                                      int record_per_page, int page_per_block); 
}
