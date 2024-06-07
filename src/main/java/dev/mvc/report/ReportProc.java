package dev.mvc.report;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.reply.ReplyDAOInter;
import dev.mvc.rereply.RereplyVO;

@Service("dev.mvc.report.ReportProc")
public class ReportProc implements ReportProcInter{
  @Autowired
  ReportDAOInter reportDAOInter;

  @Override
  public ArrayList<ReportVO> list_all_reply_community_signo(int memberno) {
    ArrayList<ReportVO> list = this.reportDAOInter.list_all_reply_community_signo(memberno);
    return list;
  }
  
  
}
