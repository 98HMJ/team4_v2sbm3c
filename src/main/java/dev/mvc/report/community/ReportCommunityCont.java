package dev.mvc.report.community;


import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestBody;


@RequestMapping("/report_community")
@Controller
public class ReportCommunityCont {
  @Autowired
  @Qualifier("dev.mvc.report.community.ReportCommunityProc")
  private ReportCommunityProc reportCommunityProc;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;
  
  public ReportCommunityCont(){
    // System.out.println("->ReportCommunityCont created");
  }

  // /**
  //  * http://localhost:9093/report_community/create
  //  * 신고 폼
  //  * @param session
  //  * @param model
  //  * @return
  //  */
  // @GetMapping(value="/create")
  // public String create(HttpSession session, 
  //                           Model model, 
  //                           ReportCommunityVO reportCommunityVO,
  //                           int communityno) {
  //   // 로그인 되어있을 때
  //   if (session.getAttribute("id") != null) {
  //     model.addAttribute("memberno", session.getAttribute("memberno"));
  //     model.addAttribute("communityno", communityno);
  //     model.addAttribute(reportCommunityVO);
      
  //   }
    
  //   return "report_community/create";
    
  // }
  
  // // http://localhost:9093/report_community/create
  // /**
  //  * 신고 처리
  //  * @param ra
  //  * @param session
  //  * @param model
  //  * @param communityno
  //  * @param memberno
  //  * @param reportCommunityVO
  //  * @return
  //  */
  // @PostMapping(value = "/create")
  // public String create(RedirectAttributes ra, 
  //                           HttpSession session, 
  //                           Model model, 
  //                           int communityno,
  //                           int memberno,
  //                           ReportCommunityVO reportCommunityVO) {
    
  //   if (session.getAttribute("id") != null) {
      
  //     reportCommunityVO.setCommunityno(communityno);
  //     reportCommunityVO.setMemberno(memberno);
      
      

  //     this.reportCommunityProc.create(reportCommunityVO);
      
  //   }else {
  //     model.addAttribute("code", "no_login");
  //     return "report_community/msg";
  //   }
    
  //   return "redirect:/community/read?communityno=" + communityno;
  // }

  @PostMapping("/create")
  @ResponseBody
  public String create(HttpSession session, ReportCommunityVO reportCommunityVO, int communityno){
    int memberno = (int) session.getAttribute("memberno");
    reportCommunityVO.setMemberno(memberno);
    reportCommunityVO.setCommunityno(communityno);
    JSONObject json = new JSONObject();
    int cnt = this.reportCommunityProc.create(reportCommunityVO);
    json.put("res",cnt);
    return json.toString();

  }
  
  /**
   * 신고 내용 전체 조회(관리자용)
   * @param session
   * @param model
   * @return
   */
  @GetMapping("/list_admin")
  public String list(HttpSession session, 
                        Model model, 
                        ReportCommunityVO reportCommunityVO) {
    
    if(this.adminProc.isAdmin(session)) {
      ArrayList<ReportCommunityVO> list = this.reportCommunityProc.list_all();
      model.addAttribute("list", list);

      
      ArrayList<MemberVO> m_list = new ArrayList<MemberVO>();
      for (ReportCommunityVO item : list) {
        MemberVO memberVO = this.memberProc.read(item.getMemberno());
        m_list.add(memberVO);
      }
      model.addAttribute("m_list", m_list);
      
      return "report_community/list_admin";
      
    }else {
      model.addAttribute("code", "no_admin");
      return "report_community/msg";
    }
    
  }
  
  /**
   * 신고 내역 조회
   * @param session
   * @param model
   * @param reportno
   * @return
   */
  @GetMapping("/read")
  public String list(HttpSession session, 
                      Model model,
                      int reportno) {

    if (this.adminProc.isAdmin(session)) {
      
      
      ReportCommunityVO recCommunityVO = this.reportCommunityProc.read(reportno);
      model.addAttribute(recCommunityVO);
      
      return "report_community/read";

    } else {
      model.addAttribute("code", "no_admin");
      return "report_community/msg";
    }

  }
  
  
}
