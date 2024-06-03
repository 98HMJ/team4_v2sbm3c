package dev.mvc.report.reply;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.rereply.RereplyVO;

@Service("dev.mvc.report.reply.ReportReplyProc")
public class ReportReplyProc implements ReportReplyProcInter{
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

  @Override
  public ArrayList<ReportReplyVO> list_by_member(int memberno) {
    ArrayList<ReportReplyVO> list = this.reportReplyDAOInter.list_by_member(memberno);
    return list;
  }
  
  
}
