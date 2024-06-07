package dev.mvc.bookmark;

import java.util.ArrayList;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.community.CommunityVO;

import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;


@RequestMapping("/bookmark")
@Controller
public class BookmarkCont {
  @Autowired
  @Qualifier("dev.mvc.bookmark.BookmarkProc")
  private BookmarkProc bookmarkProc;
  
  public BookmarkCont() {
//     System.out.println("-> BookmarkCont created.");
  }
  
  
  @PostMapping("/create_community")
  @ResponseBody
  public String create_community(HttpSession session, 
                          @RequestBody BookmarkVO bookmarkVO) {
    // System.out.println("-> 수신 데이터: " + bookmarkVO.toString());
    
    if (session.getAttribute("id") != null) {
      int memberno = (int)session.getAttribute("memberno");
      bookmarkVO.setMemberno(memberno);
      
      int cnt = this.bookmarkProc.create_community(bookmarkVO);
      // System.out.println("-> cnt: " + cnt);
      
      JSONObject json = new JSONObject();
      json.put("res", cnt);
     
      return json.toString();
      
    }
    
    return "";
  }
  
  @PostMapping("/check_community")
  @ResponseBody
  public String check_community(HttpSession session, @RequestBody BookmarkCheckCommunityVO vo) {
      if (session.getAttribute("id") != null) {
          int memberno = (int) session.getAttribute("memberno");
          vo.setMemberno(memberno);
          
          int count = this.bookmarkProc.check_community(vo);

          JSONObject json = new JSONObject();
          if (count > 0) {
              json.put("res", 1);
          } else {
              json.put("res", 0);
          }
          return json.toString();
      }

      JSONObject json = new JSONObject();
      json.put("res", 0);
      return json.toString();
  }

  
  @PostMapping("/delete_community")
  @ResponseBody
  public String delete_community(HttpSession session, @RequestBody BookmarkCheckCommunityVO vo) {
    
    if (session.getAttribute("id") != null) {
      int memberno = (int) session.getAttribute("memberno");
      // System.out.println("-> memberno: " + memberno);
      vo.setMemberno(memberno);
      
      int cnt = this.bookmarkProc.delete_community(vo);
      // System.out.println("-> cnt: " + cnt);

      JSONObject json = new JSONObject();
      json.put("res", cnt);

      return json.toString();
    }

    return "";
  }
  
  @PostMapping("/create_trash")
  @ResponseBody
  public String create_trash(HttpSession session, 
                          @RequestBody BookmarkVO bookmarkVO) {
    // System.out.println("-> 수신 데이터: " + bookmarkVO.toString());
    
    if (session.getAttribute("id") != null) {
      int memberno = (int)session.getAttribute("memberno");
      bookmarkVO.setMemberno(memberno);
      
      int cnt = this.bookmarkProc.create_trash(bookmarkVO);
      // System.out.println("-> cnt: " + cnt);
      
      JSONObject json = new JSONObject();
      json.put("res", cnt);
     
      return json.toString();
      
    }
    
    return "";
  }
  
  @PostMapping("/check_trash")
  @ResponseBody
  public String check_trash(HttpSession session, @RequestBody BookmarkCheckTrashVO vo) {
      if (session.getAttribute("id") != null) {
          int memberno = (int) session.getAttribute("memberno");
          vo.setMemberno(memberno);
          
          int count = this.bookmarkProc.check_trash(vo);

          JSONObject json = new JSONObject();
          if (count > 0) {
              json.put("res", 1);
          } else {
              json.put("res", 0);
          }
          return json.toString();
      }

      JSONObject json = new JSONObject();
      json.put("res", 0);
      return json.toString();
  }

  
  @PostMapping("/delete_trash")
  @ResponseBody
  public String delete_trash(HttpSession session, @RequestBody BookmarkCheckTrashVO vo) {
    
    if (session.getAttribute("id") != null) {
      int memberno = (int) session.getAttribute("memberno");
      // System.out.println("-> memberno: " + memberno);
      vo.setMemberno(memberno);
      
      int cnt = this.bookmarkProc.delete_trash(vo);
      // System.out.println("-> cnt: " + cnt);

      JSONObject json = new JSONObject();
      json.put("res", cnt);

      return json.toString();
    }

    return "";
  }
  
//  @GetMapping("/list")
//  public String main(HttpSession session, Model model) {
//    if (session.getAttribute("id") != null) {
//      int memberno = (int) session.getAttribute("memberno");
//      // System.out.println("-> memberno: " + memberno);
//      
//      ArrayList<BookmarkListVO> list = this.bookmarkProc.list_all(memberno);
//      model.addAttribute("list", list);
//      
//      return "bookmark/list";
//    }
//      
//    return "redirect:/member/login";
//  }
  

  /**
   * 북마크 + 검색(카테고리) + 페이징 
   * http://localhost:9093/bookmark/list?memberno=10
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

      ArrayList<BookmarkListVO> list = this.bookmarkProc.list_by_memberno_search_paging(map);
      model.addAttribute("list", list);
      
      model.addAttribute("word", word);
      
      HashMap<String, Object> c_map = new HashMap<>();
      c_map.put("word", word);
      c_map.put("memberno", memberno);
      int search_cnt = this.bookmarkProc.list_by_memberno_search_cnt(c_map);
      
      String paging = this.bookmarkProc.pagingBox(memberno, now_page, word, "/bookmark/list", search_cnt,
          Bookmark.RECORD_PER_PAGE, Bookmark.PAGE_PER_BLOCK);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);

      model.addAttribute("search_count", search_cnt);

      // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
      int no = search_cnt - ((now_page - 1) * Bookmark.RECORD_PER_PAGE);
      model.addAttribute("no", no);

      
      return "bookmark/list";
    }
      
    return "redirect:/member/login";

  }

  
}