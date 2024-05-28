package dev.mvc.trash;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.search.SearchProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import dev.mvc.trashcate.TrashcateProcInter;
import dev.mvc.trashcate.TrashcateVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@RequestMapping(value = "/trash")
@Controller
public class TrashCont {
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  @Autowired
  @Qualifier("dev.mvc.trash.TrashProc")
  private TrashProcInter trashProc;

  @Autowired
  @Qualifier("dev.mvc.trashcate.TrashcateProc")
  private TrashcateProcInter trashcateProc;
  
  @Autowired
  @Qualifier("dev.mvc.search.SearchProc")
  private SearchProcInter searchProc;

  public TrashCont() {

  }

  @GetMapping(value = "/msg")
  public String msg(Model model, String url) {
    return url; // /templates/...
  }

  @GetMapping(value = "/create")
  public String create(HttpSession session, TrashVO trashVO) {
    if (this.adminProc.isAdmin(session)) {
      return "trash/create";
    } else {
      return "redirect:/admin/login";
    }
  }

  @PostMapping(value = "/create")
  public String create(HttpServletRequest request, HttpSession session, Model model, TrashVO trashVO,
      BindingResult bindingResult, RedirectAttributes ra) {
    if (this.adminProc.isAdmin(session)) { // 관리자로 로그인한경우
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String file1 = ""; // 원본 파일명 image
      String file1saved = ""; // 저장된 파일명, image
      String thumb1 = ""; // preview image

      String upDir = Trash.getUploadDir(); // 파일을 업로드할 폴더 준비
      //System.out.println("-> upDir: " + upDir);

      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF'
      // value='' placeholder="파일 선택">
      MultipartFile mf = trashVO.getFile1MF();

      file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
      //System.out.println("-> 원본 파일명 산출 file1: " + file1);

      long size1 = mf.getSize(); // 파일 크기
      if (size1 > 0) { // 파일 크기 체크, 파일을 올리는 경우
        if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
          file1saved = Upload.saveFileSpring(mf, upDir);

          if (Tool.isImage(file1saved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
            thumb1 = Tool.preview(upDir, file1saved, 200, 150);
          }

          trashVO.setFile1(file1); // 순수 원본 파일명
          trashVO.setFile1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
          trashVO.setThumb1(thumb1); // 원본이미지 축소판
          trashVO.setSize1(size1); // 파일 크기

        } else { // 전송 못하는 파일 형식
          ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
          ra.addFlashAttribute("cnt", 0); // 업로드 실패
          ra.addFlashAttribute("url", "/trash/msg"); // msg.html, redirect parameter 적용
          return "redirect:/trash/msg"; // Post -> Get - param...
        }
      } else { // 글만 등록하는 경우
        //System.out.println("-> 글만 등록");
      }

    } else {
      return "redirect:/admin/login";
    }

    int cnt = this.trashProc.create(trashVO);
    //System.out.println("-> cnt: " + cnt);

    if (cnt == 1) {
      model.addAttribute("code", "create_success");
      model.addAttribute("name", trashVO.getName());
      return "redirect:/trash/trash_list_all";

    } else {
      ra.addFlashAttribute("code", "create_fail");
      ra.addFlashAttribute("cnt", 0);
      ra.addFlashAttribute("url", "/trash/msg");

      return "redirect:/trash/msg";
    }

  }

  @GetMapping(value = "/trash_list_all")
  public String trash_list_all(HttpSession session, Model model) {
    ArrayList<TrashVO> list = this.trashProc.trash_list_all();
    model.addAttribute("list", list);
    return "trash/trash_list_all";

  }

  @GetMapping(value = "/trash_read")
  public String trash_read(HttpSession session, Model model, int trashno) {
    TrashVO trashVO = this.trashProc.trash_read(trashno);
    model.addAttribute("trashVO", trashVO);

    TrashcateVO trashcateVO = this.trashcateProc.trashcate_read(trashVO.getTrashcateno());
    model.addAttribute("trashcateVO", trashcateVO);
    
    String searh_word = trashVO.getName();
    this.searchProc.search_create(searh_word);
    model.addAttribute("searh_word", searh_word);

    return "trash/trash_read";
  }

  @GetMapping(value = "/trash_list_search")
  public String trash_list_search(HttpSession session, Model model,
      @RequestParam(name = "word", defaultValue = "") String word) {
    ArrayList<TrashVO> list = this.trashProc.trash_list_search(word);
    model.addAttribute("list", list);
    model.addAttribute("word", word);

    return "trash/trash_list_search";
  }

  @GetMapping(value = "/trash_update")
  public String trash_update(HttpSession session, Model model, int trashno) {

    if (this.adminProc.isAdmin(session)) {
      TrashVO trashVO = this.trashProc.trash_read(trashno);
      model.addAttribute("trashVO", trashVO);

      TrashcateVO trashcateVO = this.trashcateProc.trashcate_read(trashVO.getTrashcateno());
      model.addAttribute("trashcateVO", trashcateVO);
    } else {
      return "redirect:/admin/login";
    }
    return "trash/trash_update";
  }

  @PostMapping(value = "/trash_update")
  public String trash_update(Model model, TrashVO trashVO, HttpSession session, BindingResult bindingResult,
      RedirectAttributes ra) {

    if (this.adminProc.isAdmin(session)) { 
      int cnt = this.trashProc.trash_update(trashVO);
      //System.out.println("->update cnt:" + cnt);

      if (cnt == 1) {
        ra.addAttribute("trashno", trashVO.getTrashno());
        return "redirect:/trash/trash_read";

      } else {
        ra.addFlashAttribute("code", "update_fail");
        ra.addFlashAttribute("cnt", 0);
        ra.addFlashAttribute("name", trashVO.getName());
        ra.addFlashAttribute("url", "/trash/msg");

        return "redirect:/trash/msg";
      }
    } else {
      return "redirect:/admin/login";
    }
  }

  @GetMapping(value = "/trash_delete")
  public String trash_delete(HttpSession session, Model model, @RequestParam(value = "trashno") int trashno) {

    if (this.adminProc.isAdmin(session)) {
      TrashVO trashVO = this.trashProc.trash_read(trashno);
      model.addAttribute("trashVO", trashVO);
      model.addAttribute("trashno", trashno);

      TrashcateVO trashcateVO = this.trashcateProc.trashcate_read(trashVO.getTrashcateno());
      model.addAttribute("trashcateVO", trashcateVO);
      return "trash/trash_delete";
    } else {
      return "redirect:/admin/login";
    }

  }

  @PostMapping(value = "/trash_delete")
  public String trash_delete(HttpSession session, Model model, RedirectAttributes ra,
      @RequestParam(value = "trashno") int trashno) {

    if (this.adminProc.isAdmin(session)) {
      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      // 삭제할 파일 정보를 읽어옴.
      TrashVO trashVO_read = trashProc.trash_read(trashno);

      String file1saved = trashVO_read.getFile1saved();
      String thumb1 = trashVO_read.getThumb1();

      String uploadDir = Trash.getUploadDir();
      Tool.deleteFile(uploadDir, file1saved); // 실제 저장된 파일삭제
      Tool.deleteFile(uploadDir, thumb1); // preview 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료
      // -------------------------------------------------------------------
      this.trashProc.trash_delete(trashno);
      model.addAttribute("trashno", trashno);
      ra.addAttribute("trashno", trashno);
    } else {
      return "redirect:/admin/login";
    }
    return "redirect:/trash/trash_list_all";
  }
  
}
