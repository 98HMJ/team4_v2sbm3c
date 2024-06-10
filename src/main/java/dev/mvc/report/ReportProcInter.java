package dev.mvc.report;

import java.util.ArrayList;
import java.util.HashMap;

public interface ReportProcInter {
  
  /**
   * 조회: 신고(댓글, 커뮤니티, 쓰레기) 목록
   * @return
   */
  public ArrayList<ReportVO> list_all_reply_community_signo(int memberno);
  
  /**
   * 신고 검색(카테고리, 신고 내용) 개수 조회
   * @param map
   * @return
   */
  public int list_by_memberno_search_cnt(HashMap<String, Object> map);
  
  /**
   * 신고 검색(카테고리, 신고 내용) + 페이징
   * @param map
   * @return
   */
  public ArrayList<ReportVO> list_by_memberno_serach_paging(HashMap<String, Object> map);
  
  /**
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param now_page        현재 페이지
   * @param word            검색어
   * @param list_file       목록 파일명
   * @param search_count    검색 레코드수
   * @param record_per_page 페이지당 레코드 수
   * @param page_per_block  블럭당 페이지 수
   * @return 페이징 생성 문자열
   */
  public String pagingBox(int memberno, int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block);
}
