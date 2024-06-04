package dev.mvc.log;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.log.adminlog.AdminlogProcInter;
import dev.mvc.log.adminlog.AdminlogVO;
import dev.mvc.log.memberlog.MemberlogProcInter;
import dev.mvc.log.memberlog.MemberlogVO;
import dev.mvc.singo.Singo;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RequestMapping("/log")
@Controller
public class LogCont {
  public static int RECORD_PER_PAGE = 10;
  public static int PAGE_PER_BLOCK = 10;
  @Autowired
  @Qualifier("dev.mvc.log.adminlog.AdminlogProc")
  private AdminlogProcInter adminlogProc;

  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  @Autowired
  @Qualifier("dev.mvc.log.memberlog.MemberlogProc")
  private MemberlogProcInter memberlogProc;

  public LogCont(){

  }

  @GetMapping("/admin")
  public String admin(HttpSession session, Model model,
                      @RequestParam(name="now_page", defaultValue ="1")int now_page,
                      @RequestParam(name="word", defaultValue ="all")String word) {
    if(this.adminProc.isAdmin(session)){
      int adminno=(int)session.getAttribute("adminno");
      ArrayList<AdminlogVO> list = this.adminlogProc.list_paging(word,now_page,RECORD_PER_PAGE,adminno);
      model.addAttribute("list", list);

      // 페이징 버튼 목록
       int search_count = this.adminlogProc.list_cnt(word,adminno);
       String paging = this.adminlogProc.pagingBox(now_page, 
           word, "/log/admin", search_count, RECORD_PER_PAGE, PAGE_PER_BLOCK);
       model.addAttribute("paging", paging);
       model.addAttribute("now_page", 1);
       model.addAttribute("word", word);
       
       // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
       int no = ((now_page - 1) * 10);
       model.addAttribute("no", no);


      return "log/list";
    } else{
      model.addAttribute("code", "no_login");
      return "admin/msg";
    }
  }

  @GetMapping("/member")
  public String member(HttpSession session, Model model) {
    if(session.getAttribute("memberno")==null){
      model.addAttribute("code", "no_login");
      return "member/msg";
    } else{
      ArrayList<MemberlogVO> list = this.memberlogProc.list_memberno((int)session.getAttribute("memberno"));
      model.addAttribute("list", list);
      return "log/list";
    }
  }
  
}
