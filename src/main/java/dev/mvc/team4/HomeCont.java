package dev.mvc.team4;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeCont {
    //http://localhost:9093
    //main.html 이동
    @GetMapping("/")
    public String home() { 
        return "main";
    }

    //http://localhost:9093/admin
    //main.html 이동
    @GetMapping("/admin")
    public String home_admin() { 
        return "admin/main";
    }
}
