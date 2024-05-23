package dev.mvc.report.community;

import java.util.ArrayList;

public interface ReportCommunityDAOInter {
  
  /**
   * 신고 등록
   * @param reportCommunityVO
   * @return 신고 글 등록 수 
   */
  public int create(ReportCommunityVO reportCommunityVO);
  
  /**
   * 신고 목록
   * @return 신고한 글 전체 목록
   */
  public ArrayList<ReportCommunityVO> list_all();
  
  /**
   * 신고 내역 조회
   * @return 조회한 글
   */
  public ReportCommunityVO read(int reportno);
}
