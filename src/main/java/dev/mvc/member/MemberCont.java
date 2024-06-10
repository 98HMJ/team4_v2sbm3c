package dev.mvc.member;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.mvc.log.memberlog.MemberlogProcInter;
import dev.mvc.log.memberlog.MemberlogVO;
import dev.mvc.report.ReportProcInter;
import dev.mvc.report.ReportVO;
import dev.mvc.report.community.ReportCommunityProcInter;
import dev.mvc.report.community.ReportCommunityVO;
import dev.mvc.report.reply.ReportReplyProcInter;
import dev.mvc.report.reply.ReportReplyVO;
import dev.mvc.tool.Mail;
import dev.mvc.tool.Security;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/member")
@Controller
public class MemberCont {
    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;

    @Autowired
    @Qualifier("dev.mvc.log.memberlog.MemberlogProc")
    private MemberlogProcInter memberlogProc;
    
    @Autowired
    @Qualifier("dev.mvc.report.ReportProc")
    private ReportProcInter reportProc;

    @Autowired
    private Security security;

    @Autowired
    private Mail mail;

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request,
            @RequestParam(name = "change", defaultValue = "0") int change) {

        // return "member/login";
        Cookie[] cookies = request.getCookies();
        Cookie cookie = null;

        String ck_id = ""; // id 저장
        String ck_id_save = ""; // id 저장 여부를 체크
        String ck_password = ""; // passwd 저장
        String ck_password_save = ""; // passwd 저장 여부를 체크

        if (cookies != null) { // 쿠키가 존재한다면
            for (int i = 0; i < cookies.length; i++) {
                cookie = cookies[i]; // 쿠키 객체 추출

                if (cookie.getName().equals("ck_id")) {
                    ck_id = cookie.getValue();
                } else if (cookie.getName().equals("ck_id_save")) {
                    ck_id_save = cookie.getValue(); // Y, N
                } else if (cookie.getName().equals("ck_password")) {
                    ck_password = cookie.getValue(); // 1234
                } else if (cookie.getName().equals("ck_password_save")) {
                    ck_password_save = cookie.getValue(); // Y, N
                }
            }
        }

        model.addAttribute("ck_id", ck_id);
        model.addAttribute("ck_id_save", ck_id_save);
        model.addAttribute("ck_password", ck_password);
        model.addAttribute("ck_password_save", ck_password_save);
        model.addAttribute("change", change);

        return "member/login"; // 쿠키 사용 로그인 관련
    }

    @PostMapping("/login")
    public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            Model model,
            String id,
            String password,
            int change,
            @RequestParam(value = "id_save", defaultValue = "") String id_save,
            @RequestParam(value = "password_save", defaultValue = "") String password_save,
            @RequestParam(value = "prev_url", required = false) String prev_url) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("password", password);
        int cnt = this.memberProc.login(map);

        if (cnt == 1) {
            MemberVO memberVO = this.memberProc.readByid(id);

            session.setAttribute("memberno", memberVO.getMemberno());
            session.setAttribute("id", memberVO.getId());
            session.setAttribute("password", memberVO.getPassword());
            session.setAttribute("nickname", memberVO.getNickname());

            if (id_save.equals("Y")) { // id를 저장할 경우, Checkbox를 체크한 경우
                Cookie ck_id = new Cookie("ck_id", id);
                ck_id.setPath("/"); // root 폴더에 쿠키를 기록함으로 모든 경로에서 쿠기 접근 가능
                ck_id.setMaxAge(60 * 60 * 24 * 30); // 30 day, 초단위
                response.addCookie(ck_id); // id 저장
            } else { // N, id를 저장하지 않는 경우, Checkbox를 체크 해제한 경우
                Cookie ck_id = new Cookie("ck_id", "");
                ck_id.setPath("/");
                ck_id.setMaxAge(0);
                response.addCookie(ck_id); // id 저장
            }

            // id를 저장할지 선택하는 CheckBox 체크 여부
            // -------------------------------------------------------------------
            Cookie ck_id_save = new Cookie("ck_id_save", id_save);
            ck_id_save.setPath("/");
            ck_id_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
            response.addCookie(ck_id_save);
            // -------------------------------------------------------------------

            // Password 관련 쿠기 저장
            // -------------------------------------------------------------------
            if (password_save.equals("Y")) { // 패스워드 저장할 경우
                Cookie ck_password = new Cookie("ck_password", password);
                ck_password.setPath("/");
                ck_password.setMaxAge(60 * 60 * 24 * 30); // 30 day
                response.addCookie(ck_password);
            } else { // N, 패스워드를 저장하지 않을 경우
                Cookie ck_password = new Cookie("ck_password", "");
                ck_password.setPath("/");
                ck_password.setMaxAge(0);
                response.addCookie(ck_password);
            }
            // -------------------------------------------------------------------

            // passwd를 저장할지 선택하는 CheckBox 체크 여부
            Cookie check_password_save = new Cookie("check_password_save", password_save);
            check_password_save.setPath("/");
            check_password_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
            response.addCookie(check_password_save);

            // 로그 기록
            MemberlogVO memberlogVO = new MemberlogVO();
            memberlogVO.setMemberno(memberVO.getMemberno());
            memberlogVO.setIp(request.getRemoteAddr());
            int log_cnt = this.memberlogProc.create(memberlogVO);
            if (log_cnt == 1) {
                if (change == 1) {
                    return "member/changepassword";
                } else {
                    if (prev_url != null && !prev_url.isEmpty()) {
                        return "redirect:" + prev_url;
                    } else {
                        return "redirect:/community/main";
                    }
                }
            } else {
                model.addAttribute("code", "memberlog_fail");
                model.addAttribute("cnt", log_cnt);
                return "member/msg";
            }
        } else {
            model.addAttribute("code", "login_fail");
            model.addAttribute("cnt", cnt);
            return "member/msg";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 모든 세션 변수 삭제
        return "redirect:/";
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, MemberVO memberVO) {
        int cnt = this.memberProc.create(memberVO);

        if (cnt == 1) {
            model.addAttribute("code", "singupsuccessful");
        } else {
            model.addAttribute("code", "signupfail");
        }
        model.addAttribute("cnt", cnt);

        return "member/msg";
    }

    @PostMapping("/checkid")
    @ResponseBody
    public String checkID(@RequestBody Map<String, String> jsonMap) {
        int cnt = this.memberProc.checkid(jsonMap.get("checkid"));

        JSONObject obj = new JSONObject();
        obj.put("cnt", cnt);

        return obj.toString();
    }

    @GetMapping("update")
    public String update(HttpSession session, Model model) {
        if (session.getAttribute("id") != null || session.getAttribute("adminno") != null) {
            MemberVO memberVO = this.memberProc.read((int) session.getAttribute("memberno"));
            model.addAttribute("memberVO", memberVO);
            
            return "member/update";
        } else {
            return "redirect:/member/login";
        }
    }

    @PostMapping("update")
    public String update(MemberVO memberVO, Model model) {
        int cnt = this.memberProc.update(memberVO);
        model.addAttribute("cnt", cnt);
        if (cnt == 1) {
            model.addAttribute("code", "update_success");
        } else {
            model.addAttribute("code", "update_fail");
        }
        return "member/msg";
    }
    

    @GetMapping("/findid")
    public String findid() {
        return "member/findid";
    }

    @PostMapping("findid")
    public String findid(String name, String tel, Model model) {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("name", name);
        map.put("tel", tel);
        int cnt;
        MemberVO memberVO = this.memberProc.findid(map);
        if(memberVO != null) {
            model.addAttribute("memberVO", memberVO);
            model.addAttribute("code", "findid");
            cnt = 1;
        } else {
            model.addAttribute("code", "findidfail");
            cnt = 0;
        }
        model.addAttribute("cnt", cnt);
        return "member/msg";
    }

    @GetMapping("/findpassword")
    public String findpassword() {
        return "member/findpassword";
    }

    @PostMapping("/findpassword")
    public String findpassword(String id, String name, Model model) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("name", name);

        try {
            MemberVO memberVO = this.memberProc.findpassword(map);
            String key = this.security.aesEncode(memberVO.getPassword());

            String content;
            content = """
                    <div style="text-align: center;">
                    <h1>패스워드 변경 안내</h1>
                    <span>
                    """;
            content += key;
            content += """
                    </span>
                    </div>
                    """;

            mail.send(memberVO.getEmail(), "mjhon1998@gmail.com", "패스워드 변경 안내", content);
            model.addAttribute("email", memberVO.getEmail());

            map.put("memberno", memberVO.getMemberno());
            map.put("password", key);

            int cnt = this.memberProc.changepassword(map);
            if (cnt != 1) {
                model.addAttribute("code", "findpasswordwrong");
                model.addAttribute("cnt", cnt);
            } else {
                model.addAttribute("code", "findpasswordsuccess");
                model.addAttribute("cnt", 2);
            }
            return "member/msg";
        } catch (Exception e) {
            model.addAttribute("code", "findpasswordfail");
            model.addAttribute("cnt", 0);
            return "member/msg";
        }
    }

    @PostMapping("/changepassword")
    public String changepassword(HttpSession session, String password, Model model) {
        MemberVO memberVO = this.memberProc.read((int) session.getAttribute("memberno"));

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("memberno", memberVO.getMemberno());
        map.put("password", password);

        int cnt = this.memberProc.changepassword(map);
        model.addAttribute("cnt", cnt);
        if (cnt == 1) {
            session.setAttribute("password", memberVO.getPassword());
            model.addAttribute("code", "chagepassword_success");
        } else {
            model.addAttribute("code", "chagepasswordfail");
        }
        return "member/msg";
    }
    

}
