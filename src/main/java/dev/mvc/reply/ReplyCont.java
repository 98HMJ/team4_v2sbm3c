package dev.mvc.reply;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.community.CommunityProcInter;
import dev.mvc.member.MemberProc;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reply")
public class ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc")
  private ReplyProc replyProc;

  // Q. reqly 에 @Qualifier 로 연결할 ~Proc 은 무엇을 더 추가해야할 사항?
  // community, replylike
  
  // Q. memberProc 가 꼭 필요한가? - test 해보면 답나옴
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProc memberProc;  

  public ReplyCont() {
    System.out.println("-> CateCont created.");
  }

  @GetMapping(value = "/create")
  public String create() {
    return "reply/create";
  }

  // 등록 폼 처리
  // - 5. 댓글 등록 실패는 알림으로 대체
  @PostMapping(value = "/create") // http://localhost:9091/cate/create
  public String create(RedirectAttributes ra, 
                            HttpSession session, 
                            Model model, 
                            ReplyVO replyVO) {
    if (session.getAttribute("id") != null) {
      // System.out.println("-> communityno: "+ replyVO.getCommunityno());
      
      model.addAttribute("contents", replyVO.getContents());
      
      replyVO.setCommunityno(replyVO.getCommunityno());
        
      int memberno = (int) session.getAttribute("memberno");      
      replyVO.setMemberno(memberno);
      
      // 1. 이미지 등록 처리
      // - 이미지 파일 등록 처리하기
      // - 1) 이미지를 등록할 html 만들기
      // model.addAttribute("imgs", replyVO.getPhoto());
      // - advance) 우측에 드래앤 드롭으로 이미지 기능 제작
      int cnt = this.replyProc.create(replyVO);
      System.out.println("-> cnt: "+ cnt);
    
      model.addAttribute("cnt", cnt);
      ra.addAttribute("communityno", replyVO.getCommunityno());
      
      return "redirect:/community/read"; // /templates/community/read.html 
    }else {
      model.addAttribute("code", "no_login");
      return "member/login";
    }

  }
  
  
  // 2. 수정 제작
  
  // 3. 삭제 제작
}
