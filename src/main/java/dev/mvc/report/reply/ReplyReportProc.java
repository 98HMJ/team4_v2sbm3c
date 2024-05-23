package dev.mvc.report.reply;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.report.reply.ReportReplyProc")
public class ReplyReportProc implements ReportReplyProcInter{
  @Autowired
  ReportReplyDAOInter reportReplyDAOInter;

  @Override
  public int create(ReportReplyVO reportReplyVO) {
    int cnt = this.reportReplyDAOInter.create(reportReplyVO);
    return cnt;
  }

  @Override
  public ArrayList<ReportReplyVO> list_all() {
    ArrayList<ReportReplyVO> list = this.reportReplyDAOInter.list_all();
    
    return list;
  }

  @Override
  public ReportReplyVO read(int reportno) {
    ReportReplyVO reportReplyVO = this.reportReplyDAOInter.read(reportno);
    
    return reportReplyVO;
  }
  
  
}
