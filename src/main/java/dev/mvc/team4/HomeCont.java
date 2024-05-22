package dev.mvc.team4;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.mvc.community.CommunityProcInter;
import dev.mvc.community.CommunityVO;
import dev.mvc.like.community.CommunityLikesProc;

@Controller
public class HomeCont {
    @Autowired
    @Qualifier("dev.mvc.community.CommunityProc")
    private CommunityProcInter communityProc;
    
    @Autowired
    @Qualifier("dev.mvc.like.community.CommunityLikesProc")
    private CommunityLikesProc communityLikesProc;
    
    //http://localhost:9093
    //main.html 이동
    @GetMapping("/")
    public String home(Model model) { 
        ArrayList<CommunityVO> community_list = this.communityProc.list();
        List<CommunityVO> limitedList = community_list.stream().limit(5).collect(Collectors.toList());
        model.addAttribute("limitedList", limitedList);
        return "main";
    }

    //http://localhost:9093/admin
    //main.html 이동
    @GetMapping("/admin")
    public String home_admin() {
        return "admin/main";
    }
}
