package dev.mvc.report.reply;

import java.util.ArrayList;

public interface ReportReplyProcInter {
  /**
   * 댓글 신고 생성
   * @param reportReplyVO
   * @return 생성한 신고 수
   */
  public int create(ReportReplyVO reportReplyVO);
  
  /**
   * 댓글 신고 목록
   * @return 신고 목록
   */
  public ArrayList<ReportReplyVO> list_all();
  
  /**
   * 멤버별 댓글 신고 목록
   * @return 멤버별 신고 목록
   */
  public ArrayList<ReportReplyVO> list_by_member(int memberno);
  
  /**
   * 댓글 신고 1건 조회
   * @param reportno
   * @return 신고한 글
   */
  public ReportReplyVO read(int reportno);
 
}
