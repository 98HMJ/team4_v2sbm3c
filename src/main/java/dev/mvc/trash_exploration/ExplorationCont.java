package dev.mvc.trash_exploration;

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
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.lang.reflect.Field;


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
    // System.out.println("-> ExplorationCont created");
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
      
      // 제목0번과 컨텐츠 1번 파일은 필수 등록해야 합니다
      int fileNum = 7;
      String[] files = new String[fileNum];
      String[] fileSaveds = new String[fileNum];
      String[] thumb = new String[fileNum];
      MultipartFile[] mf = new MultipartFile[fileNum];
      long[] size = new long[fileNum];
      
      // for 문을 사용하여 배열 초기화
      // System.out.println("-> files.length: " + files.length);
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
        if (mf[i] != null ) {
          files[i] = mf[i].getOriginalFilename(); // 원본 파일명 산출
          // System.out.println("-> 원본 파일명 산출 file " + i + ":" + files[i]);
          size[i] = mf[i].getSize();
          posted++;
        } else {
          // System.out.println("-> mf[" + i + "] is null or empty");
          files[i] = "";
          size[i] = 0;
        }
      }
      
      // System.out.println("-> posted: " + posted);

      for (int i = 0; i < files.length; i++) {
        if (size[i] > 0) { // 파일 크기 체크, 파일을 올리는 경우
          if (Tool.checkUploadFile(files[i]) == true) { // 업로드 가능한 파일인지 검사
            // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
            fileSaveds[i] = Upload.saveFileSpring(mf[i], upDir);

            if (i == 0 &&Tool.isImage(fileSaveds[i])) { // 이미지인지 검사
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
            return "redirect:/trash_exploration/msg"; // Post -> Get - param...
          }
        }
      }
    }else {
      return "redirect:/admin/login";
    }
    
    int cnt = this.explorationProc.create(explorationVO);
    // System.out.println("-> cnt: " + cnt);
    
    if (cnt == 1) {
      model.addAttribute("code", "create_success");
      model.addAttribute("name", explorationVO.getExponame());
      return "redirect:/trash_exploration/list_all";
    } else {
      ra.addFlashAttribute("code", "create_fail");
      ra.addFlashAttribute("cnt", 0);
      ra.addFlashAttribute("url", "/trash/msg");

      return "redirect:/trash_exploration/msg";
    }

  }
  
  @GetMapping(value = "/list_all")
  public String trash_list_all(HttpSession session, Model model) {
    if (this.adminProc.isAdmin(session)) {    
      ArrayList<ExplorationVO> list = this.explorationProc.list_all();
      model.addAttribute("list", list);
      
      return "trash_exploration/list_all";
    } else {
      return "redirect:/admin/login";
    }

  }
  
  @GetMapping(value = "/read")
  public String trash_read(HttpSession session, Model model, int expno) {
    ExplorationVO explorationVO = this.explorationProc.read(expno);
    model.addAttribute("explorationVO", explorationVO);
    
    if(this.adminProc.isAdmin(session)) {
      return "trash_exploration/read";
    }else {
      return "redirect:/admin/login";
    }
    
  }
  
  @PostMapping("/read")
  @ResponseBody
  public Map<String, Object> read(@RequestParam int expno) {
      ExplorationVO explorationVO = this.explorationProc.read(expno);
      // System.out.println("-> expno: " + expno);
      // 탐구 내용에 대한 처리
      // ExplorationVO 객체의 필드 중 이미지 관련 필드를 탐색하여 이미지 경로를 리스트에 추가합니다.
      ArrayList<String> images = new ArrayList<>();
      if (explorationVO != null) {
          // 메인 이미지 추가
          if (!explorationVO.getT_saved().isEmpty()) {
              images.add("/images/trash_exploration/storage/" + explorationVO.getT_saved());
          }
          // 추가 이미지들 추가
          for (int i = 1; i <= 6; i++) {
              String fieldName = "c" + i + "_saved";
              
              String savedImage = (String) explorationVO.getSavedImage(fieldName);
              if (!savedImage.isEmpty()) {
                  images.add("/images/trash_exploration/storage/" + savedImage);
              }
          }
      }
      

      Map<String, Object> response = new HashMap<>();
      response.put("res", images.isEmpty() ? 0 : 1);
      response.put("images", images);
      
      // System.out.println("-> response: " + response);
      
      return response;
  }
  
  /**
   * 쓰레기 탐구 항목 업데이트 폼
   * @param session
   * @param model
   * @param expno
   * @return
   */
  @GetMapping(value = "/update")
  public String update(HttpSession session, Model model, int expno) {

    if (this.adminProc.isAdmin(session)) {
      ExplorationVO explorationVO = this.explorationProc.read(expno);
      model.addAttribute("explorationVO", explorationVO);

    } else {
      return "redirect:/admin/login";
    }
    return "trash_exploration/update";
  }
  
  /**
   * 쓰레기 탐구 항목 업데이트 처리
   * @param session
   * @param model
   * @param expno
   * @return
   */
  @PostMapping(value = "/update")
  public String trash_update(RedirectAttributes ra, 
                                    Model model, 
                                    HttpSession session, 
                                    ExplorationVO explorationVO,
                                    int expno) {

    if (this.adminProc.isAdmin(session)) { 
      // 삭제할 파일 정보를 읽어옴, 기존에 등록된 레코드
      ExplorationVO explorationVO_old = this.explorationProc.read(expno);

      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      ArrayList<String> file1Saveds = new ArrayList<>(); // 실제 저장된 파일명들
      // 추가 이미지들 추가
      for (int i = 0; i < 7; i++) {
        String fieldName = new String();
        if(i == 0) {
          fieldName = "t_saved";
        } else {
          fieldName = "c" + i + "_saved";
        }
        String savedImage = (String) explorationVO.getSavedImage(fieldName);
        file1Saveds.add(savedImage);
      }
      String thumb1 = explorationVO_old.getT_thumb(); // 실제 저장된 타이틀 preview 이미지 파일명
      
      String upDir = Exploration.getUploadDir(); // C:/kd/deploy/resort_v4sbm3c/contents/storage/

      for (int i = 0; i< 7; i++) {
        Tool.deleteFile(upDir, file1Saveds.get(i)); // 실제 저장된 파일삭제
      }
      Tool.deleteFile(upDir, thumb1); // preview 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료
      // -------------------------------------------------------------------

      // -------------------------------------------------------------------
      // 파일 전송 시작
      // -------------------------------------------------------------------
      // 제목0번과 컨텐츠 1번 파일은 필수 등록해야 합니다
      int fileNum = 7;
      String[] files = new String[fileNum]; // 원본 파일명 images
      
      // 원본 파일명 
      // 파일 크기 mf 에서 가져오기 
      long[] size = new long[fileNum];

      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF'
      // value='' placeholder="파일 선택">
      MultipartFile[] mf = new MultipartFile[fileNum];
      for (int i = 0; i < fileNum; i++) {
        String fieldName = "file" + i + "MF";
        mf[i] = explorationVO.getFileMF(fieldName);
        files[i] = mf[i].getOriginalFilename();
        size[i] = mf[i].getSize();
      }
      
      // VO 에 파일 경로 저장
      for (int i = 0; i < files.length; i++) {
        if (size[i] > 0) {
          if(Tool.checkUploadFile(files[i])){
            file1Saveds.set(i, Upload.saveFileSpring(mf[i], upDir));
      
            if (i == 0 && Tool.isImage(file1Saveds.get(i))) { // 이미지인지 검사
              thumb1 = Tool.preview(upDir, file1Saveds.get(i), 200, 150);
              explorationVO.setT_thumb(thumb1); // 원본이미지 축소판
            }

          } else { // 전송 못하는 파일 형식
            ra.addFlashAttribute("code", "check_upload_file_fail");
            ra.addFlashAttribute("cnt", 0);
            ra.addFlashAttribute("name", explorationVO.getExponame());
            ra.addFlashAttribute("url", "/trash_exploration/msg");
            return "redirect:/trash_exploration/msg";
          } 
        }else{
          files[i] = "";
          file1Saveds.set(i, "");
          size[i] = 0;
          if (i == 0) {
            thumb1 = "";
          }
        }
        // system.out.println("img: " + files[i]);
        explorationVO.setImageData(i, files[i], file1Saveds.get(i), size[i]);
      }
      // -------------------------------------------------------------------
      // 파일 전송 코드 종료
      // -------------------------------------------------------------------

      explorationVO.setExpno(expno);
      int cnt = this.explorationProc.update(explorationVO);
      // system.out.println("->update cnt:" + cnt);

      if (cnt == 1) {
        ra.addAttribute("expno", explorationVO.getExpno());
        return "redirect:/trash_exploration/read";

      } else {
        ra.addFlashAttribute("code", "update_fail");
        ra.addFlashAttribute("cnt", 0);
        ra.addFlashAttribute("name", explorationVO.getExponame());
        ra.addFlashAttribute("url", "/trash_exploration/msg");

        return "redirect:/trash_exploration/msg";
      }
    } else {
      return "redirect:/admin/login";
    }
  }
  
  @GetMapping(value = "/delete")
  public String trash_delete(HttpSession session, Model model, 
                                  @RequestParam(value = "expno") int expno) {

    if (this.adminProc.isAdmin(session)) {
      ExplorationVO explorationVO = this.explorationProc.read(expno);
      model.addAttribute("explorationVO", explorationVO);
      model.addAttribute("expno", expno);

      return "trash_exploration/delete";
    } else {
      return "redirect:/admin/login";
    }

  }
  
  @PostMapping(value = "/delete")
  public String trash_delete(HttpSession session,
                                    Model model, 
                                    RedirectAttributes ra,
                                    @RequestParam(value = "expno") int expno) {

    if (this.adminProc.isAdmin(session)) {
      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      // 삭제할 파일 정보를 읽어옴.
      ExplorationVO explorationVO = this.explorationProc.read(expno);

      int fileNum = 7;
      String[] filesSaved = new String[fileNum];
      String[] thumb = new String[fileNum];
      
      String uploadDir = Exploration.getUploadDir();
      
      // for 문을 사용하여 배열 초기화
      for (int i = 0; i < filesSaved.length; i++) {
        switch (i) {
            case 0:
                filesSaved[i] = explorationVO.getT_saved(); // 순수 원본 파일명
                thumb[i] = explorationVO.getT_thumb(); // Thumb 파일명
                Tool.deleteFile(uploadDir, thumb[i]); // preview 이미지 삭제
                break;
            case 1:
                filesSaved[i] = explorationVO.getC1_saved(); 
                break;
            case 2:
                filesSaved[i] = explorationVO.getC2_saved(); 
                break;
            case 3:
                filesSaved[i] = explorationVO.getC3_saved(); 
                break;
            case 4:
                filesSaved[i] = explorationVO.getC4_saved();
                break;
            case 5:
                filesSaved[i] = explorationVO.getC5_saved();
                break;
            case 6:
                filesSaved[i] = explorationVO.getC6_saved();
                break;
            default:
                break;
        }
        Tool.deleteFile(uploadDir, filesSaved[i]); // 실제 저장된 파일삭제
//        // System.out.println("-> files: " +filesSaved[i] + thumb[i] + "삭제함" );
      }
      

      // -------------------------------------------------------------------
      // 파일 삭제 종료
      // -------------------------------------------------------------------
      int cnt = this.explorationProc.delete(expno);
      // System.out.println("-> cnt: " + cnt);
      model.addAttribute("expno", expno);
      ra.addAttribute("expno", expno);
      
    } else {
      return "redirect:/admin/login";
    }
    return "redirect:/trash_exploration/list_all";
  }
  
  
  
}
