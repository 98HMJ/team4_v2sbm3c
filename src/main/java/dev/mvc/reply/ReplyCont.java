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
import dev.mvc.community.CommunityProcInter;
import dev.mvc.member.MemberProc;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reply") // Q. reply 를 requestmapping으로 주어도 적절한가?
public class ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc")
  private ReplyProc replyProc;

  // Q. reqly 에 @Qualifier 로 연결할 ~Proc 은 무엇을 더 추가해야 하는가?
  // member, community, replylike
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProc memberProc;  

  // Q. 등록 절차에 필요한 작업은 무엇이 있는가?
  // 1. 컨트롤러 제작 테스트 및 크리에이트 폼 데이터 처리
  public ReplyCont() {
    System.out.println("-> CateCont created.");
  }

  // 등록 폼 출력 - test 를 위해 작성 나중에 삭제 필요
  @GetMapping(value = "/create")
  public String create() {
    return "reply/create";
  }

  // 등록 폼 처리
  // 1. 등록 html 제작
  // - 1. 정상적으로 등록 되는지 test
  // - 2. 댓글 등록 실패는 알림으로 대체
  @PostMapping(value = "/create") // http://localhost:9091/cate/create
  public String create(HttpSession session, Model model, ReplyVO replyVO, int communityno) {
      
    // form 에 등록된 text 를 가져오는 방법
    // contents 가 text 로 등록이 되는지 확인
    model.addAttribute("contents", replyVO.getContents());
    
    
    // 1)커뮤니티 no 와 멤버 no 는 어떻게 가져올 것인지?
    // * 커뮤니티 read 제작
    // 커뮤니티 read.html의 form 태그 안에 
    // <input type="hidden" name="communityno" th:value="${param.CommunityVO.communitino}">
    // param 으로 커뮤니티 no 를 가져올 수 있는가?
    replyVO.setCommunityno(communityno);
    
    // 2)멤버no 의 번호를  session 에서 받아옴
    int memberno = (int) session.getAttribute("memberno");      
    replyVO.setMemberno(memberno);
    
    // 3) 이미지 등록 처리
    // - 파일 등록 처리하기
    // model.addAttribute("imgs", replyVO.getPho());
    // - advance) 우측에 드래앤 드롭으로 이미지 기능 제작
    // - 어떻게?
    int cnt = this.replyProc.create(replyVO);
    System.out.println("-> cnt: "+ cnt);
      

    model.addAttribute("cnt", cnt);
    
    // 
    return "redirect:/community/read"; // /templates/reply/create.html
  }

  // 2. 목록 제작
  // 커뮤니티 read 에서 조회가 되는지 확인 작업 필요
  // ArrayList<ReplyVO> list = this.replyProc.list_all();
  
  // 3. cnt 조회
  // int cnt = this.replyProc.count_by_communityno(communityno);
}
