package dev.mvc.log.adminlog;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.log.adminlog.AdminlogProc")
public class AdminlogProc implements AdminlogProcInter {
  @Autowired
  private AdminlogDAOInter adminlogDAO;
  
  public AdminlogProc(){
    // System.out.println("-> AdminlogProc created!");
  }

  @Override
  public int create(AdminlogVO adminlogVO) {
    int cnt = this.adminlogDAO.create(adminlogVO);
    return cnt;
  }

  @Override
  public ArrayList<AdminlogVO> list() {
    ArrayList<AdminlogVO> list = this.adminlogDAO.list();
    return list;
  }

  @Override
  public AdminlogVO read(int adminlogno) {
    AdminlogVO adminlogVO = this.adminlogDAO.read(adminlogno);
    return adminlogVO;
    
  }

  @Override
  public ArrayList<AdminlogVO> list_adminno(int adminno) {
    ArrayList<AdminlogVO> list_adminno = this.adminlogDAO.list_adminno(adminno);
    return list_adminno;
  }
  
}
