package dev.mvc.singo;

import java.util.ArrayList;

public interface SingoProcInter {
  /**
   * 신고 등록
   * @param singoVO
   * @return 추가한 레코드 수
   */
  public int create(SingoVO singoVO);

  /**
   * 신고 전체 조회(관리자용)
   * @return 전체 리스트
   */
  public ArrayList<SingoVO> list(String word, int now_page, int record_per_page);

  /**
   * 검색된 레코드 수
   * @param word
   * @return
   */
  public int list_cnt(String word);

  /**
   * singono로 신고 조회
   * @param singono
   * @return singono에 해당하는 SingoVO 신고 정보
   */
  public SingoVO read(int singono);

  /**
   * 신고 내용 수정
   * @param singoVO
   * @return
   */
  public int update(SingoVO singoVO);

  /**
   * 신고 삭제
   * @param singono
   * @return
   */
  public int delete(int singono);

  public ArrayList<SingoVO> search(String word);
  
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
