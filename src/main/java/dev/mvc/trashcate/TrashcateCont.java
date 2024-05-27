package dev.mvc.trashcate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.admin.AdminProcInter;
import jakarta.servlet.http.HttpSession;

@RequestMapping(value = "/trashcate")
@Controller
public class TrashcateCont {
  @Autowired
  @Qualifier("dev.mvc.trashcate.TrashcateProc")
  private TrashcateProcInter trashcateProc;

  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  public TrashcateCont() {

  }

  @GetMapping(value = "/msg")
  public String msg(Model model, String url) {
    return url;
  }

  @GetMapping(value = "/create")
  public String create(HttpSession session, TrashcateVO trashcateVO) {
    if (this.adminProc.isAdmin(session)) {
      return "trashcate/create";
    } else {
      return "redirect:/admin/login";
    }
  }

  @PostMapping(value = "/create")
  public String create(HttpSession session, Model model, RedirectAttributes ra, TrashcateVO trashcateVO) {
    if (this.adminProc.isAdmin(session)) {
      int cnt = this.trashcateProc.create(trashcateVO);

      if (cnt == 1) {
        return "redirect:/trashcate/trashcate_list_all";
      } else {
        ra.addFlashAttribute("code", "create_fail");
        ra.addFlashAttribute("cnt", 0);
        ra.addFlashAttribute("url", "/trashcate/msg");

        return "redirect:/trashcate/msg";
      }
    } else {
      return "redirect:/admin/login";
    }
  }
  
  @GetMapping(value="/trashcate_list_all")
  public String trashcate_list_all(HttpSession session, Model model) {
    if (this.adminProc.isAdmin(session)) {
      ArrayList<TrashcateVO> list = this.trashcateProc.trashcate_list_all();
      model.addAttribute("list", list);
      
      return "trashcate/trashcate_list_all";
    } else {
      return "redirect:/admin/login";
    }
  }
  
  @GetMapping(value = "/trashcate_read")
  public String trash_read(HttpSession session, Model model, int trashcateno) {
    TrashcateVO trashcateVO = this.trashcateProc.trashcate_read(trashcateno);
    model.addAttribute("trashcateVO", trashcateVO);

    return "trashcate/trashcate_read";
  }
  
  @GetMapping(value="/trashcate_update")
  public String trashcate_update(HttpSession session, Model model, int trashcateno) {
    if (this.adminProc.isAdmin(session)) {
      TrashcateVO trashcateVO = this.trashcateProc.trashcate_read(trashcateno);
      model.addAttribute("trashcateVO", trashcateVO);
      return "trashcate/trashcate_update";
    } else {
      return "redirect:/admin/login";
    }
  }
  
  @PostMapping(value="/trashcate_update")
  public String trashcate_update(HttpSession session, RedirectAttributes ra, Model model, TrashcateVO trashcateVO) {
    if (this.adminProc.isAdmin(session)) {
      int cnt = this.trashcateProc.trashcate_update(trashcateVO);

      if (cnt == 1) {
        ra.addAttribute("trashcateno", trashcateVO.getTrashcateno());
        
        return "redirect:/trashcate/trashcate_read";
      } else {
        ra.addFlashAttribute("code", "update_fail");
        ra.addFlashAttribute("cnt", 0);
        ra.addFlashAttribute("url", "/trashcate/msg");

        return "redirect:/trashcate/msg";
      }
    } else {
      return "redirect:/admin/login";
    }
  }
  
  @GetMapping(value = "/trashcate_delete")
  public String trashcate_delete(HttpSession session, Model model, @RequestParam(value = "trashcateno") int trashcateno) {

    if (this.adminProc.isAdmin(session)) {
      TrashcateVO trashcateVO = this.trashcateProc.trashcate_read(trashcateno);
      model.addAttribute("trashcateVO", trashcateVO);
      return "trashcate/trashcate_delete";
    } else {
      return "redirect:/admin/login";
    }

  }

  @PostMapping(value = "/trashcate_delete")
  public String trashcate_delete(HttpSession session, Model model, RedirectAttributes ra,
      @RequestParam(value = "trashcateno") int trashcateno) {

    if (this.adminProc.isAdmin(session)) {
      
      int cnt = this.trashcateProc.trashcate_delete(trashcateno);
      if (cnt == 1) {
        model.addAttribute("trashno", trashcateno);
        ra.addAttribute("trashno", trashcateno);
        
        return "redirect:/trashcate/trashcate_list_all";
      } else {
        ra.addFlashAttribute("code", "delete_fail");
        ra.addFlashAttribute("cnt", 0);
        ra.addFlashAttribute("url", "/trashcate/msg");

        return "redirect:/trashcate/msg";
      } 
    } else {
      return "redirect:/admin/login";
    }
  }
  
}
