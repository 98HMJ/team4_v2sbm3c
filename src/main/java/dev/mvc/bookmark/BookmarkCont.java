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
    System.out.println("-> BookmarkCont created.");
  }
  
  
  @PostMapping("/create_community")
  @ResponseBody
  public String create_community(HttpSession session,
                          @RequestBody BookmarkVO bookmarkVO) {
    System.out.println("-> 수신 데이터: " + bookmarkVO.toString());
    
    if (session.getAttribute("id") != null) {
      int memberno = (int)session.getAttribute("memberno");
      bookmarkVO.setMemberno(memberno);
      
      int cnt = this.bookmarkProc.create_community(bookmarkVO);
      System.out.println("-> cnt: " + cnt);
      
      JSONObject json = new JSONObject();
      json.put("res", cnt);
     
      return json.toString();
      
    }
    
    return "";
  }
  
  
  @PostMapping("/check_community")
  @ResponseBody
  public String check_community(HttpSession session, int communityno) {
    
    if (session.getAttribute("id") != null) {
      int memberno = (int) session.getAttribute("memberno");
      System.out.println("-> memberno: " + memberno);
      System.out.println("-> communityno: " + communityno);
      
      HashMap<Integer, Object> noMap = new HashMap<>();
      // 값 할당
      noMap.put(memberno, communityno); // 정수값
      
      if(this.bookmarkProc.check_community(noMap) != null) {
        JSONObject json = new JSONObject();
        json.put("res", 1);
        System.out.println("-> memberno: " + memberno);
        return json.toString();
      }
      
      return "";

    }

    return "";
  }
  
}