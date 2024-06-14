package dev.mvc.singo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.member.MemberProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import dev.mvc.trash.TrashProcInter;

import jakarta.servlet.http.HttpSession;

@RequestMapping("/singo")
@Controller
public class SingoCont {
  @Autowired
  @Qualifier("dev.mvc.singo.SingoProc")
  private SingoProcInter singoProc;

  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;

  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProcInter memberProc;

  @Autowired
  @Qualifier("dev.mvc.trash.TrashProc")
  private TrashProcInter trashProc;

  public SingoCont() {

  }

  /**
   * 신고 처리
   * 
   * @param session
   * @param singoVO
   * @param trashno
   * @return
   */
  @PostMapping(value = "/create")
  @ResponseBody
  public String create(HttpSession session, SingoVO singoVO, int trashno) {
    int memberno = (int) session.getAttribute("memberno");
    singoVO.setMemberno(memberno);
    JSONObject json = new JSONObject();
    // 파일 전송 코드 시작
    String file = ""; // 원본 파일명
    String filesaved = ""; // 저장된 파일명
    String thumb = ""; // 썸네일 이미지

    String upDir = Singo.getUploadDir(); // 파일을 업로드할 폴더 준비

    ArrayList<MultipartFile> mf_list = new ArrayList<>();
    if (singoVO.getFile1MF() == null) {
      singoVO.setTrashno(trashno);

      int cnt = this.singoProc.create(singoVO);
      json.put("res", cnt);

      return json.toString();
    }
    mf_list.add(singoVO.getFile1MF());

    for (MultipartFile mf : mf_list) {
      file = mf.getOriginalFilename(); // 원본 파일명 산출
      long size = mf.getSize(); // 파일 크기
      if (size > 0 && file != null) { // 파일 크기 체크
        if (Tool.checkUploadFile(file)) { // 업로드 가능한 파일인지 검사
          filesaved = Upload.saveFileSpring(mf, upDir);

          if (Tool.isImage(filesaved)) { // 이미지인지 검사
            thumb = Tool.preview(upDir, filesaved, 200, 150);
            singoVO.setFiles(file); // 원본 파일명
            singoVO.setFilesaved(filesaved); // 저장된 파일명
            singoVO.setThumb(thumb); // 썸네일 이미지
            singoVO.setSize1(size); // 파일 크기
          }
        } else { // 전송할 수 없는 파일 형식
          json.put("res", 0);
        }
      }
    }
    singoVO.setTrashno(trashno);

    int cnt = this.singoProc.create(singoVO);
    json.put("res", cnt);

    return json.toString();
  }

  /**
   * 신고 내용 전체 조회(관리자용)
   * 
   * @param session
   * @param model
   * @return
   */
  @GetMapping("/list")
  public String list(HttpSession session, Model model,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page,
      String word) {
    if (this.adminProc.isAdmin(session)) {
      word = Tool.checkNull(word).trim();
      ArrayList<SingoVO> list = this.singoProc.list(word, now_page, Singo.RECORD_PER_PAGE);
      model.addAttribute("list", list);

      // 페이징 버튼 목록
      int search_count = this.singoProc.list_cnt(word);
      String paging = this.singoProc.pagingBox(now_page,
          word, "/singo/list", search_count, Singo.RECORD_PER_PAGE, Singo.PAGE_PER_BLOCK);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);

      model.addAttribute("word", word);

      // 일련 변호 생성: 레코드 갯수 - ((현재 페이지수 -1) * 페이지당 레코드 수)
      int no = ((now_page - 1) * Singo.RECORD_PER_PAGE);
      model.addAttribute("no", no);

      return "singo/list";
    } else {
      model.addAttribute("code", "permission_error");
      return "singo/msg";
    }
  }

  /**
   * 신고내용 조회
   * 
   * @param session
   * @param model
   * @param singono
   * @return
   */
  @GetMapping("/read")
  public String read(HttpSession session, Model model, int singono) {
    SingoVO singoVO = this.singoProc.read(singono);
    boolean isAdmin = this.adminProc.isAdmin(session);
    boolean isWriter = false;
    if (session.getAttribute("memberno") != null) {
      isWriter = ((int) session.getAttribute("memberno") == singoVO.getMemberno());
    }
    // 관리자 권한이거나 자신이 신고한 내용이면 조회 가능
    if (isAdmin || isWriter) {
      model.addAttribute("singoVO", singoVO);
      model.addAttribute("isAdmin", isAdmin);
      model.addAttribute("isWriter", isWriter);
      return "singo/read";
    } else {
      model.addAttribute("code", "permission_error");
      return "singo/msg";
    }
  }

  /**
   * 신고 수정 폼
   * 
   * @param session
   * @param model
   * @param singono
   * @return
   */

  @GetMapping("/update")
  public String update(HttpSession session, Model model, int singono) {
    SingoVO singoVO = this.singoProc.read(singono);
    int memberno = (int) session.getAttribute("memberno");
    // 자신이 쓴 글이면 수정 가능
    if (singoVO.getMemberno() == memberno) {
      model.addAttribute("singoVO", singoVO);
      return "singo/update";
    } else {
      model.addAttribute("code", "permission_error");
      return "singo/msg";
    }
  }

  /** 신고 수정 */
  @PostMapping("update")
  public String update(Model model, RedirectAttributes ra, SingoVO singoVO) {
    // ------------------------------------------------------------------------------
    // 파일 전송 코드 시작
    // ------------------------------------------------------------------------------
    String file = ""; // 원본 파일명 image
    String filesaved = ""; // 저장된 파일명, image
    String thumb = ""; // preview image

    String upDir = Singo.getUploadDir(); // 파일을 업로드할 폴더 준비

    // 전송 파일이 없어도 file1MF 객체가 생성됨.
    // <input type='file' class="form-control" name='file1MF' id='file1MF'
    // value='' placeholder="파일 선택">
    ArrayList<MultipartFile> mf_list = new ArrayList<MultipartFile>();
    mf_list.add(singoVO.getFile1MF());

    for (MultipartFile mf : mf_list) {
      file = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
      long size = mf.getSize(); // 파일 크기
      if (size > 0 && file != null) { // 파일 크기 체크
        if (Tool.checkUploadFile(file) == true) { // 업로드 가능한 파일인지 검사
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
          filesaved = Upload.saveFileSpring(mf, upDir);

          if (Tool.isImage(filesaved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
            thumb = Tool.preview(upDir, filesaved, 200, 150);
            singoVO.setFiles(file); // 순수 원본 파일명
            singoVO.setFilesaved(filesaved); // 저장된 파일명(파일명 중복 처리)
            singoVO.setThumb(thumb); // 원본이미지 축소판
            singoVO.setSize1(size); // 파일 크기
          }
        } else { // 전송 못하는 파일 형식
          model.addAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
          return "singo/msg"; // Post -> Get - param...
        }
      }
    }
    int cnt = this.singoProc.update(singoVO);
    if (cnt == 1) {
      ra.addAttribute("singono", singoVO.getSingono());
      return "redirect:/singo/read";
    } else {
      model.addAttribute("code", "update_fail");
      model.addAttribute("cnt", cnt);
      return "singo/msg";
    }
  }

  @GetMapping(value = "/delete")
  public String delete(HttpSession session, Model model, int singono,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    SingoVO singoVO = this.singoProc.read(singono);
    if (this.adminProc.isAdmin(session)) {
      model.addAttribute("singoVO", singoVO);
      word = Tool.checkNull(word).trim();
      ArrayList<SingoVO> list = this.singoProc.list(word, now_page, Singo.RECORD_PER_PAGE);
      model.addAttribute("list", list);

      // 페이징 버튼 목록
      int search_count = this.singoProc.list_cnt(word);
      String paging = this.singoProc.pagingBox(now_page,
          word, "/singo/list", search_count, Singo.RECORD_PER_PAGE, Singo.PAGE_PER_BLOCK);
      model.addAttribute("paging", paging);
      model.addAttribute("now_page", now_page);

      model.addAttribute("word", word);

      int no = ((now_page - 1) * Singo.RECORD_PER_PAGE);
      model.addAttribute("no", no);

      return "singo/delete";
    } else {
      model.addAttribute("code", "permission_error");
      return "singo/msg";
    }
  }

  @PostMapping("/delete")
  public String delete_proc(HttpSession session, Model model, int singono,
      @RequestParam(name = "word", defaultValue = "") String word,
      @RequestParam(name = "now_page", defaultValue = "1") int now_page) {
    int cnt = this.singoProc.delete(singono);
    if (cnt == 1) {
      // ----------------------------------------------------------------------------------------------------------
      // 마지막 페이지에서 모든 레코드가 삭제되면 페이지수를 1 감소 시켜야함.
      int search_cnt = this.singoProc.list_cnt(word);
      if (search_cnt % Singo.RECORD_PER_PAGE == 0) {
        now_page = now_page - 1;
        if (now_page < 1) {
          now_page = 1; // 최소 시작 페이지
        }
      }
      System.out.println("-> now_page" + now_page);
      // ----------------------------------------------------------------------------------------------------------
      return "redirect:/singo/list?word=" + Tool.encode(word) + "&now_page=" + now_page;
    } else {
      model.addAttribute("cnt", cnt);
      model.addAttribute("code", "delete_fail");
      return "singo/msg";
    }
  }

}
