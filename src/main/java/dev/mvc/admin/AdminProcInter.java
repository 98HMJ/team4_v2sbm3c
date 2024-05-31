package dev.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;

import jakarta.servlet.http.HttpSession;

public interface AdminProcInter {
  /**
   * 중복 아이디 검사
   * @param id
   * @return id 개수, 0: 중복 없음 1 이상: 중복
   */
  public int checkID(String id);

  /**
   * 회원 가입
   * @param adminVO
   * @return 추가한 레코드 수
   */
  public int create(AdminVO adminVO);

  /**
   * 관리자 전체 목록
   * @return ArrayList<AdminVO> 회원 전체 목록 
   */
  public ArrayList<AdminVO> list();

  /**
   * adminno로 관리자 정보 조회
   * @param adminno
   * @return adminno에 해당하는 AdminVO 관리자 정보
   */
  public AdminVO read(int adminno);
  
  /**
   * id로 관리자 정보 조회
   * @param id
   * @return id에 해당하는 AdminVO 관리자 정보
   */
  public AdminVO readById(String id);

  /**
   * 수정 처리
   * @param adminVO
   * @return 관리자 정보 변경
   */
  public int update(AdminVO adminVO);
 
  /**
   * 관리자 삭제 처리
   * @param adminno
   * @return
   */
  public int delete(int adminno);
  
  /**
   * 현재 비밀번호 검사
   * @param map
   * @return 0: 일치하지 않음, 1: 일치함
   */
  public int password_check(HashMap<String, Object> map);
  
  /**
   * 비밀번호 변경
   * @param map
   * @return 변경된 비밀번호 개수
   */
  public int password_update(HashMap<String, Object> map);
  
  /**
   * 로그인 처리
   */
  public int login(HashMap<String, Object> map);

  /**
   * 로그인된 관리자 검사
   * @param session
   * @return true: 관리자 false: 관리자 X 
   */
  public boolean isAdmin(HttpSession session);

  /**
   * 통합관리자 검사
   * @param session
   * @return true: 통합관리자 false: 통합관리자 X
   */
  public boolean isPermission(HttpSession session);

  /**
     * 관리자 아이디 찾기
     * @param map
     * @return adminVO
     */
    public AdminVO findid(HashMap<String,String> map);

    /**
     * 관리자 비밀번호 찾기
     * @param map
     * @return adminVO
     */
    public AdminVO findpassword(HashMap<String,Object> map);

    /**
     * 관리자 비밀번호 수정
     * @param map
     * @return adminVO
     */
    public int changepassword(HashMap<String,Object> map);
  
}
