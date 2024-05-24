package dev.mvc.admin;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.log.adminlog.AdminlogProcInter;
import dev.mvc.log.adminlog.AdminlogVO;
import dev.mvc.tool.Security;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/admin")
@Controller
public class AdminCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  @Autowired
  @Qualifier("dev.mvc.log.adminlog.AdminlogProc")
  private AdminlogProcInter adminlogProc;

  @Autowired
  Security security;
  
  public AdminCont() {
    // System.out.println("-> AdminCont created!");
  }
  /**
   * 중복 아이디 검사
   * @param id
   * @return
   */
  @GetMapping(value="/checkID")
  @ResponseBody
  public String checkID(String id) {
      int cnt = this.adminProc.checkID(id);
      JSONObject obj = new JSONObject();
      obj.put("cnt", cnt);
      return obj.toString();
  }


  
  /**
   * 로그인 폼
   * @param model
   * @param request
   * @return 로그인 폼 html
   */
  @GetMapping(value="/login")
  public String login(Model model, HttpServletRequest request) {
    //쿠키 관련 코드
    Cookie[] cookies  = request.getCookies();
    Cookie cookie = null;

    String check_id = ""; //id 저장
    String check_id_save = ""; //id 저장 여부를 체크
    String check_password = ""; //비밀번호 저장
    String check_password_save = ""; //비밀번호 저장 여부를 체크

    if(cookies != null) {
      for(Cookie cook: cookies) {
        cookie = cook; //쿠키 객체 추출
        if(cookie.getName().equals("check_id")) {
          check_id = cookie.getValue(); //id
        } else if(cookie.getName().equals("check_id_save")) {
          check_id_save = cookie.getValue();  // Y, N
        } else if(cookie.getName().equals("check_password")) {
          check_password = cookie.getValue();         // 비밀번호
        } else if(cookie.getName().equals("check_password_save")) {
          check_password_save = cookie.getValue();  // Y, N
      }
    }
  }
  model.addAttribute("check_id", check_id);
  model.addAttribute("check_id_save", check_id_save);
  model.addAttribute("check_password", check_password);
  model.addAttribute("check_password_save", check_password_save);
  return "admin/login";
  }

  /**
   * 로그인 처리
   * @param session
   * @param request
   * @param response
   * @param model
   * @param id
   * @param password
   * @param id_save
   * @param password_save
   * @return
   */
  @PostMapping(value="/login")
  public String login(HttpSession session,
                      HttpServletRequest request,
                      HttpServletResponse response,
                      Model model,
                      String id,
                      String password,
                      @RequestParam(value="id_save", defaultValue="") String id_save,
                      @RequestParam(value="password_save", defaultValue = "") String password_save,
                      @RequestParam(value="prev_url", required = false) String prev_url) {
    HashMap<String,Object> hm = new HashMap<String, Object>();
    hm.put("id", id);
    hm.put("password", this.security.aesEncode(password));  
    int cnt = this.adminProc.login(hm);
    model.addAttribute("cnt", cnt);

    if(cnt==1){
      //id를 이용하여 관리자 정보 조회
      System.out.println("-> id" + id +"end");
      //AdminVO adminVO = this.adminProc.readById(id); //오류 why?
      System.out.println(this.adminProc.readById(id));
      AdminVO adminVO = this.adminProc.readById(id);
      System.out.println("-> 이름" + adminVO.getName());
      session.setAttribute("adminno", adminVO.getAdminno());
      session.setAttribute("id", adminVO.getId());
      session.setAttribute("name", adminVO.getName());
      session.setAttribute("admin", "admin");

      //Cookie저장

    // id 저장
    if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
      Cookie check_id = new Cookie("check_id", id);
      check_id.setPath("/");  // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
      check_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
      response.addCookie(check_id); // id 저장
    } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
      Cookie check_id = new Cookie("check_id", "");
      check_id.setPath("/");
      check_id.setMaxAge(0);
      response.addCookie(check_id); // id 저장
    }

    // id를 저장할지 선택하는  CheckBox 체크 여부
    Cookie check_id_save = new Cookie("check_id_save", id_save);
    check_id_save.setPath("/");
    check_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
    response.addCookie(check_id_save);

    //비밀번호 저장
    if (password_save.equals("Y")) { // 비밀번호 저장할 경우
      Cookie check_password = new Cookie("check_password", password);
      check_password.setPath("/");
      check_password.setMaxAge(60 * 60 * 24 * 30); // 30 day
      response.addCookie(check_password);
    } else { // N, 패스워드를 저장하지 않을 경우
      Cookie check_password = new Cookie("check_password", "");
      check_password.setPath("/");
      check_password.setMaxAge(0);
      response.addCookie(check_password);
    }

    // passwd를 저장할지 선택하는  CheckBox 체크 여부
    Cookie check_password_save = new Cookie("check_password_save", password_save);
    check_password_save.setPath("/");
    check_password_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
    response.addCookie(check_password_save);
    
    //로그 기록
    AdminlogVO adminlogVO = new AdminlogVO();
    adminlogVO.setAdminno(adminVO.getAdminno());
    adminlogVO.setIp(request.getRemoteAddr());
    int log_cnt = this.adminlogProc.create(adminlogVO);
    if( log_cnt==1){
      if (prev_url != null && !prev_url.isEmpty()) {
        return "redirect:" + prev_url;
    } else{
      return "redirect:/admin";
    }
    } else {
      model.addAttribute("code","adminlog_fail");
    return "admin/msg";
    }
  } else {
    model.addAttribute("code","login_fail");
    return "admin/msg";
    }
  }

  @GetMapping(value="/logout")
  public String logout(HttpSession session, Model model) {
    session.invalidate(); //모든 세션 변수 삭제
      return "redirect:/admin";
  }
  

  /**
   * 관리자 등록 폼
   * @param model
   * @param adminVO
   * @return 관리자 등록 폼 html
   */
  @GetMapping(value="/signup")
  public String signup(Model model, 
                       HttpSession session,
                       AdminVO adminVO) {
    if(!this.adminProc.isAdmin(session)){
      return "redirect:/admin/login";
    } else if(this.adminProc.isPermission(session)) {
      return "admin/signup";
    } else {
      model.addAttribute("code", "permission_error");
      return "admin/msg";
    }

  }
  
  /**
   * 관리자 등록
   * @param model
   * @param adminVO
   * @return
   */
  @PostMapping(value="/signup")
  public String signup(Model model, AdminVO adminVO) {
      int checkID_cnt = this.adminProc.checkID(adminVO.getId());
      if(checkID_cnt==0){
        int cnt = this.adminProc.create(adminVO);
        if(cnt == 1){
          model.addAttribute("code", "signup_success");
          model.addAttribute("name", adminVO.getName());
          model.addAttribute("id", adminVO.getId());
        } else{
          model.addAttribute("code", "create_fail");
        }
      } else{
        model.addAttribute("code", "duplicate_fail");
        model.addAttribute("cnt", 0);
      }
      return "admin/msg";
  }

  /**
   * 관리자 정보 조회 및 수정 폼
   * @param session
   * @param model
   * @param adminno
   * @return
   */
  @GetMapping(value="/read")
  public String read(HttpSession session, Model model, int adminno) {
    boolean isPermission = this.adminProc.isPermission(session);
    if(adminno == (int)session.getAttribute("adminno") || isPermission) {
      AdminVO adminVO = this.adminProc.read(adminno);
      model.addAttribute("adminVO", adminVO);
      return "admin/read";
    } else {
      return "redirect:/admin/login";
    }
  }

  @GetMapping(value="/list")
  public String list(HttpSession session, Model model) {
    //통합관리자 권한 확인
    boolean isPermission = this.adminProc.isPermission(session);
    if (isPermission) { 
      ArrayList<AdminVO> list = this.adminProc.list();
      model.addAttribute("list", list);
      
      return "admin/list";   
    } else {
      model.addAttribute("code", "permission_error");
      return "admin/msg";
    }  

  }

  /**
   * 관리자 정보 수정 처리
   * @param model
   * @param adminVO
   * @return
   */
  @PostMapping(value="/update")
  public String update(Model model, AdminVO adminVO) {
    int cnt = this.adminProc.update(adminVO);
    if(cnt == 1){
      model.addAttribute("code", "update_success");
      model.addAttribute("name", adminVO.getName());
      model.addAttribute("id", adminVO.getId());
    } else{
      model.addAttribute("code", "update_fail");
    }
    model.addAttribute("cnt", cnt);
    return "admin/msg";
  }

  /**
   * 비밀번호 변경 폼
   * @param session
   * @param model
   * @return
   */
  @GetMapping(value="password_update")
  public String password_update(HttpSession session, Model model, int adminno) {
    if(session.getAttribute("adminno")==null){
      return "redirect:/admin/login";
    }
    if(adminno == (int)session.getAttribute("adminno")) {
      AdminVO adminVO = this.adminProc.read(adminno);
      model.addAttribute("adminVO", adminVO);
      return "admin/password_update";
    } else {
      model.addAttribute("code", "matching_error");
      return "admin/msg";
    }
  }
  
  /**
   * 현재 비밀번호 확인
   * @param session
   * @param json_src
   * @return
   */
  @PostMapping(value = "/password_check")
  @ResponseBody
  public String password_check(HttpSession session, @RequestBody String json_src) {
    JSONObject src = new JSONObject(json_src);
    String current_password = (String)src.get("current_password");
    
    int adminno = (int)session.getAttribute("adminno");
    HashMap<String, Object> hm = new HashMap<>();
    hm.put("adminno",adminno);
    hm.put("password", this.security.aesEncode(current_password));

    int cnt = this.adminProc.password_check(hm);
    JSONObject json = new JSONObject();
    json.put("cnt", cnt);
    
    return json.toString();
  }
  
  @PostMapping(value="/password_update")
  public String password_update(HttpSession session,
                                Model model,
                                String current_password,
                                String password) {
    if(this.adminProc.isAdmin(session)){
      int adminno = (int)session.getAttribute("adminno");
      HashMap<String, Object> hm = new HashMap<>();
      hm.put("adminno", adminno);
      hm.put("password", this.security.aesEncode(current_password));

      int cnt = this. adminProc.password_check(hm);
      if(cnt == 0){ //현재 비밀번호 불일치
        model.addAttribute("code", "pasword_not_equal");
        model.addAttribute("cnt", 0);
      } else{ //현재 비밀번호 일치
        HashMap<String,Object> hm_new_password = new HashMap<>();
        hm_new_password.put("adminno", adminno);
        hm_new_password.put("password", this.security.aesEncode(password));

        int change_password_cnt = this.adminProc.password_update(hm_new_password);

        if(change_password_cnt == 1){ //비밀번호 변경 성공
          model.addAttribute("code", "password_change_success");
          model.addAttribute("cnt", 1);
        } else { // 비밀번호 변경 실패
          model.addAttribute("code", "password_change_fail");
          model.addAttribute("cnt", 0);
        }
      }
      return "admin/msg";
    } else{
      return "redirect:/admin/login";
    }
      
  }
  

  
  /**
   * 관리자 정보 삭제 폼
   * @param model
   * @param adminno
   * @return
   */
  @GetMapping(value="/delete")
  public String delete(HttpSession session, Model model, int adminno) {
    boolean isPermission = this.adminProc.isPermission(session);
    if(adminno == (int)session.getAttribute("adminno") || isPermission) {
      AdminVO adminVO = this.adminProc.read(adminno);
      model.addAttribute("adminVO", adminVO);
      return "admin/delete";
    } else{
      model.addAttribute("code", "update_fail");
    }
    return "admin/msg";
  }
  
  @PostMapping(value="/delete")
  public String delete(Model model, Integer adminno) {
    int cnt = this.adminProc.delete(adminno);
    if(cnt == 1){
      return "redirect:/admin/list";
    } else{
      model.addAttribute("code", "delete_fail");
      return "admin/msg";
    }
  }
  
}
