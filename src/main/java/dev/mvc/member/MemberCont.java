package dev.mvc.member;

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

    @GetMapping("/login")
    public String login(Model model, HttpServletRequest request) {
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

        return "member/login"; // 쿠키 사용 로그인 관련
    }

    @PostMapping("/login")
    public String login(HttpSession session, HttpServletRequest request, HttpServletResponse response,
            Model model,
            String id,
            String password,
            @RequestParam(value = "id_save", defaultValue = "") String id_save,
            @RequestParam(value = "password_save", defaultValue = "") String password_save) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("id", id);
        map.put("password", password);
        System.out.println(map);
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
            Cookie ck_password_save = new Cookie("ck_password_save", password_save);
            ck_password_save.setPath("/");
            ck_password_save.setMaxAge(60 * 60 * 24 * 30); // 30 day
            response.addCookie(ck_password_save);
            // -------------------------------------------------------------------

            return "redirect:/";
        } else {
            model.addAttribute("code", "login_fail");
            return "msg";
        }
    }

    @GetMapping("/signup")
    public String signup() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(Model model, MemberVO memberVO) {
        int cnt = this.memberProc.create(memberVO);

        if (cnt == 1) {
            model.addAttribute("code", "successful");
        } else {
            model.addAttribute("code", "signupfail");
        }

        return "msg";
    }

    @PostMapping("/checkid")
    @ResponseBody
    public String checkID(@RequestBody Map<String, String> jsonMap) {
        int cnt = this.memberProc.checkid(jsonMap.get("checkid"));

        JSONObject obj = new JSONObject();
        obj.put("cnt", cnt);

        return obj.toString();
    }

}
