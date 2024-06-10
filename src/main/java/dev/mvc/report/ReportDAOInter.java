package dev.mvc.report;

import java.util.ArrayList;
import java.util.HashMap;


public interface ReportDAOInter {
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

}
