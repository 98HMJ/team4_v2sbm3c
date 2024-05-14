package dev.mvc.admin;

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
    System.out.println("-> AdminCont created!");
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
  @PostMapping(value="login")
  public String login(HttpSession session,
                      HttpServletRequest request,
                      HttpServletResponse response,
                      Model model,
                      String id,
                      String password,
                      @RequestParam(value="id_save", defaultValue="") String id_save,
                      @RequestParam(value="password_save", defaultValue = "") String password_save) {
    HashMap<String,Object> hm = new HashMap<String, Object>();
    hm.put("id", id);
    hm.put("password", this.security.aesEncode(password));  
    
    int cnt = this.adminProc.login(hm);
    model.addAttribute("cnt", cnt);

    if(cnt==1){
      //id를 이용하여 관리자 정보 조회
      AdminVO adminVO = this.adminProc.readById(id);
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
      return "redirect:/";
    } else {
      model.addAttribute("code","adminlog_fail");
    return "msg";
    }
  } else {
    model.addAttribute("code","login_fail");
    return "msg";
    }
  }


  /**
   * 회원가입 폼
   * @param model
   * @param adminVO
   * @return 회원가입 폼 html
   */
  @GetMapping(value="/signup")
  public String signup(Model model, 
                       HttpSession session,
                       AdminVO adminVO) {
    if(!this.adminProc.isAdmin(session)){
      System.out.println("-> session "+session);
      return "redirect:/admin/login";
    } 
    return "admin/signup";
  }
  
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
}
