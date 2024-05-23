package dev.mvc.report.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.report.community.ReportCommunityVO;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/report_reply")
@Controller
public class ReportReplyCont {
  @Autowired
  @Qualifier("dev.mvc.report.reply.ReportReplyProc")
  private ReportReplyProcInter reportReplyProc;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  public ReportReplyCont() {
    System.out.println("-> ReportReplyCont Created.");
  }
  
  /**
   * http://localhost:9093/report_reply/create
   * 신고 폼
   * @param session
   * @param model
   * @return
   */
  @GetMapping(value="/create")
  public String create(HttpSession session, 
                            Model model, 
                            ReportReplyVO reportReplyVO,
                            int communityno,
                            int replyno) {
    // 로그인 되어있을 때
    if (session.getAttribute("id") != null) {
      model.addAttribute("memberno", session.getAttribute("memberno"));
      model.addAttribute("communityno", communityno);
      model.addAttribute("replyno", replyno);
      model.addAttribute("reportReplyVO", reportReplyVO);
      
    }
    
    return "report_reply/create";
    
  }
  
// http://localhost:9093/report_reply/create
 /**
  * 신고 처리
  * @param ra
  * @param session
  * @param model
  * @param communityno
  * @param memberno
  * @param reportCommunityVO
  * @return
  */
 @PostMapping(value = "/create")
 public String create(RedirectAttributes ra, 
                           HttpSession session, 
                           Model model, 
                           int communityno,
                           int replyno,
                           int memberno,
                           ReportReplyVO reportReplyVO) {
   
   if (session.getAttribute("id") != null) {
     
     reportReplyVO.setReplyno(replyno);
     reportReplyVO.setMemberno(memberno);
     
    
     int cnt = this.reportReplyProc.create(reportReplyVO);
     System.out.println("-> cnt: "+ cnt);
     
   }else {
     model.addAttribute("code", "no_login");
     return "report_community/msg";
   }
   
   return "redirect:/community/read?communityno=" + communityno;
 }
}
