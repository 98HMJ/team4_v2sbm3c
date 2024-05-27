package dev.mvc.search;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.admin.AdminProcInter;
import jakarta.servlet.http.HttpSession;

@RequestMapping(value="/search")
@Controller
public class SearchCont {
  @Autowired
  @Qualifier("dev.mvc.search.SearchProc")
  private SearchProcInter searchProc;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;
  
  public SearchCont() {
    
  }

  @PostMapping(value="/create")
  public void create(Model model, String search_word) {
    this.searchProc.search_create(search_word);
  }
  
  @GetMapping(value="/search_list_all")
  public String search_list_all(Model model) {
    ArrayList<SearchVO> list = this.searchProc.search_list_all();
    model.addAttribute("list", list);
    
    return "search/search_list_all";
  }
  
  @GetMapping(value="/search_delete")
  public String search_delete(Model model) {
    return "search/search_delete";
  }
  
  @PostMapping(value="/search_delete")
  public String search_delete(HttpSession session, Model model, int weeks) {
    if(this.adminProc.isAdmin(session)) {
    this.searchProc.search_delete(weeks);
    return "redirect:/search/search_list_all";
    } else {
      return "redirect:/admin/login";
    }
  }
  
  @GetMapping(value="/search_popular")
  public String search_popular(Model model) {
    ArrayList<SearchVO> list = this.searchProc.search_popular();
    model.addAttribute("list", list);
    
    return "search/search_popular";
  }
     
}
