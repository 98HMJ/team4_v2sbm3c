package dev.mvc.log.adminlog;

import java.util.ArrayList;
import java.util.HashMap;

public interface AdminlogDAOInter {
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
   * 로그인 내역 페이징
   * @param map
   * @return
   */
  public ArrayList<AdminlogVO> list_paging(HashMap<String,Object> map);

  /**
   * 검색 레코드 수
   * @param word
   * @return
   */
  public int list_cnt(HashMap<String,Object> map);
}
