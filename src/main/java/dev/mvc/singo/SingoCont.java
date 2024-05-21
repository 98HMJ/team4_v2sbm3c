package dev.mvc.singo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.trash.TrashProcInter;
import jakarta.servlet.http.HttpSession;

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
  public String create(HttpSession session, Model model, int trashno) {
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
  @PostMapping(value="/create")
  public String create(HttpSession session,
                       Model model,
                       SingoVO singoVO,
                       int trashno) {
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
}

