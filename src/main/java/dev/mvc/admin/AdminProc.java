package dev.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.tool.Security;
import jakarta.servlet.http.HttpSession;

@Service("dev.mvc.admin.AdminProc")
public class AdminProc implements AdminProcInter{

  @Autowired
  private AdminDAOInter adminDAO;

  @Autowired
  Security security;

  public AdminProc(){
    // System.out.println("-> AdminProc created!");
  }

  @Override
  public int checkID(String id) {
    int cnt = this.adminDAO.checkID(id);
    return cnt;
  }

  /**
   * 회원 가입
   */
  @Override
  public int create(AdminVO adminVO) {
    adminVO.setPassword(security.aesEncode(adminVO.getPassword()));
    int cnt = this.adminDAO.create(adminVO);
    return cnt;
  }

  /**
   * 관리자 전체 조회
   */
  @Override
  public ArrayList<AdminVO> list() {
    ArrayList<AdminVO> list = this.adminDAO.list();
    return list;
  }

  /**
   * 관리자 번호로 admin 조회
   */
  @Override
  public AdminVO read(int adminno) {
    AdminVO adminVO = this.adminDAO.read(adminno);
    return adminVO;
  }

  /**
   * id로 admin 조회
   */
  @Override
  public AdminVO readById(String id) {
    AdminVO adminVO = this.adminDAO.readById(id);
    return adminVO;
  }


  /**
   * 관리자 정보 수정
   */
  @Override
  public int update(AdminVO adminVO) {
    int cnt = this.adminDAO.update(adminVO);
    return cnt;
  }

  /**
   * 관리자 삭제
   */
  @Override
  public int delete(int adminno) {
    int cnt = this.adminDAO.delete(adminno);
    return cnt;
  }

  /**
   * login과 같음
   */
  @Override
  public int password_check(HashMap<String, Object> map) {
    int cnt = this.adminDAO.password_check(map);
    return cnt;
  }

  @Override
  public int password_update(HashMap<String, Object> map) {
    int cnt = this.adminDAO.password_update(map);
    return cnt;
  }

  @Override
  public int login(HashMap<String, Object> map) {
    int cnt = this.adminDAO.login(map);
    return cnt;
  }

  @Override
  public boolean isAdmin(HttpSession session) {
    boolean isAdmin = false; //관리자가 아닌 것으로 초기화
    isAdmin = session.getAttribute("admin") == null ? false : true;
    return isAdmin;
  }
  
  @Override
  public boolean isPermission(HttpSession session) {
    boolean isPermission = false; //통합관리자가 아닌 것으로 초기화
    if(session.getAttribute("adminno")== null)
      return false;
    isPermission = ((int)session.getAttribute("adminno") == 1) ? true: false;
    return isPermission;
  }
}
