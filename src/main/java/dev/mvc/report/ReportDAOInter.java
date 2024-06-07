package dev.mvc.report;

import java.util.ArrayList;


public interface ReportDAOInter {
  /**
   * 조회: 신고(댓글, 커뮤니티, 쓰레기) 목록
   * @return
   */
  public ArrayList<ReportVO> list_all_reply_community_signo(int memberno);
}
