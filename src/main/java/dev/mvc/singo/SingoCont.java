package dev.mvc.singo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.member.MemberProcInter;

import dev.mvc.trash.TrashProcInter;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;




@RequestMapping("/singo")
@Controller
public class SingoCont {
  @Autowired
  @Qualifier("dev.mvc.singo.SingoProc")
  private SingoProcInter singoProc;
  
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  @Autowired
  @Qualifier("dev.mvc.trash.TrashProc")
  private TrashProcInter trashProc;

  public SingoCont(){
    // System.out.println("-> SingoCont created!");
  }

  /**
   * 신고 폼
   * @param session
   * @param model
   * @return
   */
  @GetMapping(value="/create")
  public String create(HttpSession session, Model model,@RequestParam int trashno) {
    Integer memberno = (Integer)session.getAttribute("memberno");
    if(memberno!=null){
      model.addAttribute("memberno", memberno);
      model.addAttribute("trashno", trashno);
      return "singo/create";
    } else {
      model.addAttribute("code", "login_need");
      return "singo/msg";
    }
    
  }
  /**
   * 신고 처리
   * @param session
   * @param model
   * @param singoVO
   * @param trashno
   * @return
   */
  @PostMapping(value="/create")
  public String create(HttpSession session,
                       Model model,
                       SingoVO singoVO,
                       @RequestParam int trashno) {
    int memberno = (int)session.getAttribute("memberno");
    model.addAttribute("memberno", memberno);
    model.addAttribute("trashno", trashno);
    int cnt = this.singoProc.create(singoVO);
    if(cnt==1) {
      model.addAttribute("code", "singo_success");
      model.addAttribute("cnt", cnt);
      return "singo/msg";
    } else{
      model.addAttribute("code", "singo_fail");
      model.addAttribute("cnt", cnt);
      return "singo/msg";
    }
  }

  /**
   * 신고 내용 전체 조회(관리자용)
   * @param session
   * @param model
   * @return
   */
  @GetMapping("/list")
  public String list(HttpSession session, Model model) {
    if(session.getAttribute("adminno")!=null) {
      ArrayList<SingoVO> list = this.singoProc.list();
      model.addAttribute("list", list);
      
      return "singo/list";
    } else{
      model.addAttribute("code", "permission_error");
      return "singo/msg";
    }
  }
  /**
   * 신고내용 조회
   * @param session
   * @param model
   * @param singono
   * @return
   */
  @GetMapping("/read")
  public String read(HttpSession session, Model model, int singono) {
    SingoVO singoVO = this.singoProc.read(singono);

    // 관리자 권한이거나 자신이 신고한 내용이면 조회 가능
    if(session.getAttribute("adminno")!=null || (int)session.getAttribute("memberno")==singoVO.getMemberno()){
      model.addAttribute("singoVO", singoVO);
      return "singo/read";
    } else{
      model.addAttribute("code", "permission_error");
      return "singo/msg";
    }
  }
  
  /**
   * 신고 수정 폼
   * @param session
   * @param model
   * @param singono
   * @return
   */

  @GetMapping("/update")
  public String update(HttpSession session, Model model, int singono) {
    SingoVO singoVO = this.singoProc.read(singono);
    int memberno = (int)session.getAttribute("memberno");
    //자신이 쓴 글이면 수정 가능
    if(singoVO.getMemberno() == memberno){
      model.addAttribute("singoVO", singoVO);
      return "singo/update";
    } else{
      model.addAttribute("code", "permission_error");
      return "singo/msg";
    }
  }

  /** 신고 수정 */
  @PostMapping("update")
  public String update(Model model, RedirectAttributes ra, SingoVO singoVO) {
      int cnt = this.singoProc.update(singoVO);
      if(cnt==1){
        ra.addAttribute("singono", singoVO.getSingono());
        return "redirect:/singo/read";
      } else {
        model.addAttribute("code", "update_fail");
        model.addAttribute("cnt", cnt);
        return "singo/msg";
      }
  }
  
  @GetMapping(value = "/delete")
  public String delete(HttpSession session, Model model, int singono) {
    SingoVO singoVO = this.singoProc.read(singono);
    // 관리자 권한이거나 자신이 신고한 내용이면 조회 가능
    if(session.getAttribute("adminno")!=null || (int)session.getAttribute("memberno")==singoVO.getMemberno()){
      model.addAttribute("singoVO", singoVO);
      return "singo/delete";
    } else{
      model.addAttribute("code", "permission_error");
      return "singo/msg";
    }
  }

  @PostMapping("delete")
  public String delete(Model model, RedirectAttributes ra,int singono) {
    int cnt = this.singoProc.delete(singono);         
    if(cnt == 1){
      model.addAttribute("cnt", cnt);
      model.addAttribute("code", "delete_success");
      return "singo/msg";
    } else{
      model.addAttribute("cnt", cnt);
      model.addAttribute("code", "delete_fail");
      return "singo/msg";
    }
  }
  
}

