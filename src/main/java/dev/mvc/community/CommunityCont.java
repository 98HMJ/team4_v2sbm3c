package dev.mvc.community;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import dev.mvc.reply.ReplyProc;
import dev.mvc.reply.ReplyVO;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/community")
@Controller
public class CommunityCont {
    @Autowired
    @Qualifier("dev.mvc.community.CommunityProc")
    private CommunityProcInter communityProc;
    
    @Autowired
    @Qualifier("dev.mvc.reply.ReplyProc")
    private ReplyProc replyProc;

    public CommunityCont() {
        // System.out.println("-> CommunityCont created.");
    }

    @GetMapping("/main")
    public String main(Model model) {
        ArrayList<CommunityVO> list = this.communityProc.list();
        model.addAttribute("list", list);
        
        return "community/main";
    }

    @GetMapping("/read/{communityno}")
    public String read(Model model, 
                          @PathVariable("communityno") int communityno) {
        CommunityVO communityVO = this.communityProc.read(communityno);
        model.addAttribute("communityVO", communityVO);
        
        ArrayList<ReplyVO> list = this.replyProc.list_by_community(communityno);
        model.addAttribute("list", list);
        
        for(ReplyVO item : list) {
          System.out.println("replyVO_num" + item.getContents());          
        }
        
        int reply_cnt = this.replyProc.count_by_communityno(communityno);
        model.addAttribute("reply_cnt", reply_cnt);
        
        return "community/read_r";
    }
    

}
