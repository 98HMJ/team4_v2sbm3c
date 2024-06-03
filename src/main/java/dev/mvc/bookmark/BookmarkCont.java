package dev.mvc.bookmark;

import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/bookmark")
@Controller
public class BookmarkCont {
  @Autowired
  @Qualifier("dev.mvc.bookmark.BookmarkProc")
  private BookmarkProc bookmarkProc;
  
  public BookmarkCont() {
    // System.out.println("-> BookmarkCont created.");
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
      System.out.println("-> memberno: " + memberno);
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
    System.out.println("-> 수신 데이터: " + bookmarkVO.toString());
    
    if (session.getAttribute("id") != null) {
      int memberno = (int)session.getAttribute("memberno");
      bookmarkVO.setMemberno(memberno);
      
      int cnt = this.bookmarkProc.create_trash(bookmarkVO);
      System.out.println("-> cnt: " + cnt);
      
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
      System.out.println("-> memberno: " + memberno);
      vo.setMemberno(memberno);
      
      int cnt = this.bookmarkProc.delete_trash(vo);
      System.out.println("-> cnt: " + cnt);

      JSONObject json = new JSONObject();
      json.put("res", cnt);

      return json.toString();
    }

    return "";
  }
  
  
}