package dev.mvc.team4;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import dev.mvc.community.CommunityProcInter;
import dev.mvc.community.CommunityVO;

@Controller
public class HomeCont {
    @Autowired
    @Qualifier("dev.mvc.community.CommunityProc")
    private CommunityProcInter communityProc;
    //http://localhost:9093
    //main.html 이동
    @GetMapping("/")
    public String home(Model model) { 
        ArrayList<CommunityVO> community_list = this.communityProc.list();
        model.addAttribute("community_list", community_list);
        return "main";
    }

    //http://localhost:9093/admin
    //main.html 이동
    @GetMapping("/admin")
    public String home_admin() {
        return "admin/main";
    }
}
