package dev.mvc.report;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.bookmark.Bookmark;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/report")
@Controller
public class ReportCont {
  @Autowired
  @Qualifier("dev.mvc.report.ReportProc")
  private ReportProcInter reportProc;
  

  /**
   * 북마크 + 검색(카테고리) + 페이징 
   * http://localhost:9093/report/list?memberno=10
   * @param session
   * @param model
   * @param memberno
   * @param word
   * @param now_page
   * @return
   */
  @GetMapping(value = "/list")
  public String list_by_memberno_search_paging(HttpSession session, Model model,
      @RequestParam(name = "memberno", defaultValue = "1") int memberno,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {

    if (session.getAttribute("id") != null) {
      memberno = (int) session.getAttribute("memberno");
      model.addAttribute("memberno", memberno);
      
      word = Tool.checkNull(word).trim();

      HashMap<String, Object> map = new HashMap<>();
      map.put("memberno", memberno);
      map.put("word", word);
      map.put("now_page", now_page);

      ArrayList<ReportVO> list = this.reportProc.list_by_memberno_serach_paging(map);
      model.addAttribute("list", list);
      
      model.addAttribute("word", word);
      
      HashMap<String, Object> c_map = new HashMap<>();
      c_map.put("word", word);
      c_map.put("memberno", memberno);
      int search_cnt = this.reportProc.list_by_memberno_search_cnt(c_map);
      
      String paging = this.reportProc.pagingBox(memberno, now_page, word, "/report/list", search_cnt,
          Report.RECORD_PER_PAGE, Report.PAGE_PER_BLOCK);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);

      model.addAttribute("search_count", search_cnt);

      // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
      int no = search_cnt - ((now_page - 1) * Bookmark.RECORD_PER_PAGE);
      model.addAttribute("no", no);
      
      return "report/list";
    }
    return "redirect:/member/login";

  }
  
  
}
