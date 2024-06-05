package dev.mvc.trash_exploration;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import dev.mvc.trash.TrashVO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping(value = "/trash_exploration")
@Controller
public class ExplorationCont {
  @Autowired
  @Qualifier("dev.mvc.trash.exploration.ExplorationProc")
  private ExplorationProcInter explorationProc;
  
  @Autowired
  @Qualifier("dev.mvc.admin.AdminProc")
  private AdminProcInter adminProc;
  
  public ExplorationCont() {
    System.out.println("-> ExplorationCont created");
  }
  
  /**
   * 탐구 등록 폼
   * @param session
   * @param trashVO
   * @return
   */
  @GetMapping(value = "/create")
  public String create(HttpSession session, ExplorationVO explorationVO) {
    if (this.adminProc.isAdmin(session)) {
      return "/trash_exploration/create";
    } else {
      return "redirect:/admin/login";
    }
  }
  
  @PostMapping(value = "/create")
  public String create(HttpServletRequest request, 
                            RedirectAttributes ra,
                            HttpSession session, 
                            Model model, 
                            ExplorationVO explorationVO
                            ) {
    if (this.adminProc.isAdmin(session)) { // 관리자로 로그인한경우
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      
      // 제목0번과 컨텐츠 1번 파일은 필수 등록해야 합니다.
      int fileNum = 7;
      String[] files = new String[fileNum];
      String[] fileSaveds = new String[fileNum];
      String[] thumb = new String[fileNum];
      MultipartFile[] mf = new MultipartFile[fileNum];
      long[] size = new long[fileNum];
      
      // for 문을 사용하여 배열 초기화
      System.out.println("-> files.length: " + files.length);
      for (int i = 0; i < files.length; i++) {
        files[i] = "" + (i + 1);
        fileSaveds[i] = "" + (i + 1);
        thumb[i] = "" + (i + 1);  
      }
      
      String upDir = "src/main/resources/static/images/trash_exploration/storage/"; // 파일을 업로드할 폴더 준비(프로젝트)
      //System.out.println("-> upDir: " + upDir);

      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF'
      // value='' placeholder="파일 선택">
      mf[0] = explorationVO.getFile0MF();
      mf[1] = explorationVO.getFile1MF();
      mf[2] = explorationVO.getFile2MF();
      mf[3] = explorationVO.getFile3MF();
      mf[4] = explorationVO.getFile4MF();
      mf[5] = explorationVO.getFile5MF();
      mf[6] = explorationVO.getFile6MF();

//      for (int i = 0; i < files.length; i++) {
//        files[i] = mf[i].getOriginalFilename(); // 원본 파일명 산출, 01.jpg
//        System.out.println("-> 원본 파일명 산출 file " + i + ":" + files[i]);
//        size[i] = mf[i].getSize();
//      }
      
      int posted = 0;
      for (int i = 0; i < files.length; i++) {
        if (mf[i] != null && !mf[i].isEmpty()) {
          files[i] = mf[i].getOriginalFilename(); // 원본 파일명 산출
          System.out.println("-> 원본 파일명 산출 file " + i + ":" + files[i]);
          size[i] = mf[i].getSize();
          posted++;
        } else {
          System.out.println("-> mf[" + i + "] is null or empty");
          files[i] = null;
          size[i] = 0;
        }
      }

      for (int i = 0; i < files.length; i++) {
        if (size[i] > 0) { // 파일 크기 체크, 파일을 올리는 경우
          if (Tool.checkUploadFile(files[i]) == true) { // 업로드 가능한 파일인지 검사
            // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
            fileSaveds[i] = Upload.saveFileSpring(mf[i], upDir);

            if (Tool.isImage(fileSaveds[i])) { // 이미지인지 검사
              // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
              thumb[i] = Tool.preview(upDir, fileSaveds[i], 200, 150);
            }

            switch (i) {
            case 0:
              explorationVO.setT_img(files[i]); // 순수 원본 파일명
              explorationVO.setT_saved(fileSaveds[i]); // 저장된 파일명(파일명 중복 처리)
              explorationVO.setT_thumb(thumb[i]); // 원본이미지 축소판
              explorationVO.setT_size(size[i]); // 파일 크기
              break;
            case 1:
              explorationVO.setC1_img(files[i]); // 순수 원본 파일명
              explorationVO.setC1_saved(fileSaveds[i]); // 저장된 파일명(파일명 중복 처리)
              explorationVO.setC1_size(size[i]); // 파일 크기
              break;
            case 2:
              explorationVO.setC2_img(files[i]); // 순수 원본 파일명
              explorationVO.setC2_saved(fileSaveds[i]); // 저장된 파일명(파일명 중복 처리)
              explorationVO.setC2_size(size[i]); // 파일 크기
              break;
            case 3:
              explorationVO.setC3_img(files[i]); // 순수 원본 파일명
              explorationVO.setC3_saved(fileSaveds[i]); // 저장된 파일명(파일명 중복 처리)
              explorationVO.setC3_size(size[i]); // 파일 크기
              break;
            case 4:
              explorationVO.setC4_img(files[i]); // 순수 원본 파일명
              explorationVO.setC4_saved(fileSaveds[i]); // 저장된 파일명(파일명 중복 처리)
              explorationVO.setC4_size(size[i]); // 파일 크기
              break;
            case 5:
              explorationVO.setC5_img(files[i]); // 순수 원본 파일명
              explorationVO.setC5_saved(fileSaveds[i]); // 저장된 파일명(파일명 중복 처리)
              explorationVO.setC5_size(size[i]); // 파일 크기
              break;
            case 6:
              explorationVO.setC6_img(files[i]); // 순수 원본 파일명
              explorationVO.setC6_saved(fileSaveds[i]); // 저장된 파일명(파일명 중복 처리)
              explorationVO.setC6_size(size[i]); // 파일 크기
              break;
            default:
              break;
            }

          } else { // 전송 못하는 파일 형식
            ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
            ra.addFlashAttribute("cnt", 0); // 업로드 실패
            ra.addFlashAttribute("url", "/trash/msg"); // msg.html, redirect parameter 적용
            return "redirect:/trash/msg"; // Post -> Get - param...
          }
        }
      }
    }else {
      return "redirect:/admin/login";
    }
    
    int cnt = this.explorationProc.create(explorationVO);
    System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      model.addAttribute("code", "create_success");
      model.addAttribute("name", explorationVO.getExponame());
      return "redirect:/trash_exploration/list_all";
    } else {
      ra.addFlashAttribute("code", "create_fail");
      ra.addFlashAttribute("cnt", 0);
      ra.addFlashAttribute("url", "/trash/msg");

      return "redirect:/trash/msg";
    }

  }
  
  
  
}
