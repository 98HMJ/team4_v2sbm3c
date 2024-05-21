package dev.mvc.community;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.communitycate.CommunityCateProcInter;
import dev.mvc.communitycate.CommunityCateVO;
import dev.mvc.reply.ReplyProc;
import dev.mvc.reply.ReplyVO;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.PostMapping;

@RequestMapping("/community")
@Controller
public class CommunityCont {
    @Autowired
    @Qualifier("dev.mvc.community.CommunityProc")
    private CommunityProcInter communityProc;

    @Autowired
    @Qualifier("dev.mvc.reply.ReplyProc")
    private ReplyProc replyProc;

    @Autowired
    @Qualifier("dev.mvc.communitycate.CommunityCateProc")
    private CommunityCateProcInter communityCateProc;

    public CommunityCont() {
        System.out.println("-> CommunityCont created.");
    }

    @GetMapping("/main")
    public String main(Model model) {
        ArrayList<CommunityVO> list = this.communityProc.list();
        model.addAttribute("list", list);

        return "community/main";
    }

    @GetMapping("/read")
    public String read(HttpSession session, int communityno, Model model) {
        if (session.getAttribute("id") != null) {
            ArrayList<ReplyVO> list = this.replyProc.list_by_community(communityno);
            model.addAttribute("list", list);

            int reply_cnt = this.replyProc.count_by_communityno(communityno);
            model.addAttribute("reply_cnt", reply_cnt);

            CommunityVO communityVO = this.communityProc.read(communityno);
            if (communityVO.getMemberno() == (int) session.getAttribute("memberno")) {
                model.addAttribute("bool", true);
            }
            model.addAttribute("communityVO", communityVO);
            return "community/read_r";
        } else {
            model.addAttribute("code", "no_login");
            return "member/login";
        }
    }

    @GetMapping("/create")
    public String create(HttpSession session, Model model) {
        if (session.getAttribute("id") != null) {
            ArrayList<CommunityCateVO> list = this.communityCateProc.list();
            model.addAttribute("list", list);
            return "community/create";
        } else {
            model.addAttribute("code", "no_login");
            return "member/login";
        }
    }

    @PostMapping("/create")
    public String create(CommunityVO communityVO, Model model, HttpSession session) {
        communityVO.setMemberno((int) session.getAttribute("memberno"));
        int cnt = this.communityProc.create(communityVO);
        if (cnt == 1) {
            return "redirect:/community/main";
        } else {
            model.addAttribute("code", "community_create_fail");
            return "msg";
        }
    }

    @GetMapping("update")
    public String update(HttpSession session, int communityno, Model model) {
        CommunityVO communityVO = this.communityProc.read(communityno);
        if (session.getAttribute("id") != null) {
            if (communityVO.getMemberno() == (int) session.getAttribute("memberno")) {
                model.addAttribute("communityVO", communityVO);
                model.addAttribute("memberno", session.getAttribute("memberno"));
                return "community/update";
            } else {
                model.addAttribute("code", "not_access");
                return "msg";
            }
        } else {
            model.addAttribute("code", "no_login");
            return "member/login";
        }
    }

    @PostMapping("update")
    public String update(CommunityVO communityVO, Model model) {
        int cnt = this.communityProc.update(communityVO);
        if (cnt == 1) {
            return "community/main";
        } else {
            model.addAttribute("code", "community_update_fail");
            return "msg";
        }
    }

    @GetMapping("delete")
    public String delete(HttpSession session, int communityno, Model model) {
        CommunityVO communityVO = this.communityProc.read(communityno);
        if (session.getAttribute("id") != null) {
            if (communityVO.getMemberno() == (int) session.getAttribute("memberno")) {
                int cnt = this.communityProc.delete(communityno);
                if (cnt == 1) {
                    model.addAttribute("code", "community_delete_success");
                    return "redirect:/community/main";
                } else {
                    model.addAttribute("code", "community_delete_fail");
                }
            } else {
                model.addAttribute("code", "not_access");
            }
            return "msg";
        } else {
            model.addAttribute("code", "no_login");
            return "member/login";
        }
    }

    @PostMapping("search")
    public String search(String word, Model model) {
        ArrayList<CommunityVO> list = this.communityProc.search(word);
        model.addAttribute("list", list);
        return "community";
    }

}
