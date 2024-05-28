package dev.mvc.team4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.mvc.community.CommunityProcInter;
import dev.mvc.community.CommunityVO;
import dev.mvc.search.SearchProcInter;
import dev.mvc.search.SearchVO;
import dev.mvc.trash.TrashProcInter;
import dev.mvc.trash.TrashVO;

@Controller
public class HomeCont {
    @Autowired
    @Qualifier("dev.mvc.community.CommunityProc")
    private CommunityProcInter communityProc;
       
    @Autowired
    @Qualifier("dev.mvc.search.SearchProc")
    private SearchProcInter searchProc;
    
    @Autowired
    @Qualifier("dev.mvc.trash.TrashProc")
    private TrashProcInter trashProc;
    
    //http://localhost:9093
    //main.html 이동
    @GetMapping("/")
    public String home(Model model) { 
        ArrayList<CommunityVO> community_list = this.communityProc.list();
        List<CommunityVO> limitedList = community_list.stream().limit(5).collect(Collectors.toList());
        model.addAttribute("limitedList", limitedList);
        
        ArrayList<SearchVO> popular_list = this.searchProc.search_popular();
        model.addAttribute("popular_list", popular_list);
        
        List<HashMap<String, Object>> dir_list = new ArrayList<>();
        
        for (int i=0; i<popular_list.size();i++) {
          String name = popular_list.get(i).getSearch_word();
          dir_list.add(this.trashProc.trash_read_by_name(name));
        }
        
        model.addAttribute("dir_list", dir_list);
        
        return "main";
    }

    //http://localhost:9093/admin
    //main.html 이동
    @GetMapping("/admin")
    public String home_admin(Model model) {
      ArrayList<CommunityVO> community_list = this.communityProc.list();
      List<CommunityVO> limitedList = community_list.stream().limit(5).collect(Collectors.toList());
      model.addAttribute("limitedList", limitedList);
      
      ArrayList<SearchVO> popular_list = this.searchProc.search_popular();
      model.addAttribute("popular_list", popular_list);
      
      List<HashMap<String, Object>> dir_list = new ArrayList<>();
      
      for (int i=0; i<popular_list.size();i++) {
        String name = popular_list.get(i).getSearch_word();
        dir_list.add(this.trashProc.trash_read_by_name(name));
      }
      
      model.addAttribute("dir_list", dir_list);
      return "admin/main";
    }
}
