package dev.mvc.report.community;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.report.community.ReportCommunityProc")
public class ReportCommunityProc implements ReportCommunityProcInter{
  @Autowired
  private ReportCommunityDAOInter reportCommunityDAO;
  
  @Override
  public int create(ReportCommunityVO reportCommunityVO) {
    int cnt = this.reportCommunityDAO.create(reportCommunityVO);
    return cnt;
  }

  @Override
  public ArrayList<ReportCommunityVO> list_all() {
    ArrayList<ReportCommunityVO> list = this.reportCommunityDAO.list_all();
    return list;
  }
  
  @Override
  public ArrayList<ReportCommunityVO> list_by_member(int memberno) {
    ArrayList<ReportCommunityVO> list = this.reportCommunityDAO.list_by_member(memberno);
    return list;
  }

  @Override
  public ReportCommunityVO read(int reportno) {
    ReportCommunityVO reportCommunityVO = this.reportCommunityDAO.read(reportno);
    return reportCommunityVO;
  }
  
}
