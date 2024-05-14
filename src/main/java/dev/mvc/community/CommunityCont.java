package dev.mvc.community;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/community")
@Controller
public class CommunityCont {
    @Autowired
    @Qualifier("dev.mvc.community.CommunityProc")
    private CommunityProcInter communityProc;

    public CommunityCont() {
        // System.out.println("-> CommunityCont created.");
    }

    @GetMapping("")
    public String main(Model model) {
        ArrayList<CommunityVO> list = this.communityProc.list();
        model.addAttribute("list", list);

        return "community/main";
    }

    @GetMapping("/read")
    public String read(int communityno, Model model) {
        CommunityVO communityVO = this.communityProc.read(communityno);
        model.addAttribute("communityVO", communityVO);
        return "community/read";
    }
    

}
