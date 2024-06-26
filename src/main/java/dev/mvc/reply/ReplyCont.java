package dev.mvc.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import dev.mvc.member.MemberProc;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reply")
public class ReplyCont {
  @Autowired
  @Qualifier("dev.mvc.reply.ReplyProc")
  private ReplyProc replyProc;

  // Q. reqly 에 @Qualifier 로 연결할 ~Proc 은 무엇을 더 추가해야할 사항?
  // community, replylike
  
  // Q. memberProc 가 꼭 필요한가? - test 해보면 답나옴
  @Autowired
  @Qualifier("dev.mvc.member.MemberProc")
  private MemberProc memberProc;  

  public ReplyCont() {
    System.out.println("-> CateCont created.");
  }

//  @GetMapping(value = "/create")
//  public String create() {
//    return "reply/create";
//  }

  // 등록 폼 처리
  // todo. 댓글 등록 실패는 알림으로 대체
  // todo. 글을 작성하지 않으면 작성하라는 알림 띄우기
  @PostMapping(value = "/create") // http://localhost:9091/cate/create
  public String create(RedirectAttributes ra, 
                            HttpSession session, 
                            Model model, 
                            ReplyVO replyVO) {
    if (session.getAttribute("id") != null) {
      
      model.addAttribute("contents", replyVO.getContents());
      
      replyVO.setCommunityno(replyVO.getCommunityno());
        
      int memberno = (int) session.getAttribute("memberno");      
      replyVO.setMemberno(memberno);
      
      // 1. 이미지 등록 처리
      // - advance) 우측에 드래앤 드롭으로 이미지 기능 제작
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 시작
      // ------------------------------------------------------------------------------
      String file1 = ""; // 원본 파일명 image
      String file1saved = ""; // 저장된 파일명, image
      String thumb1 = ""; // preview image

      String upDir = Reply.getUploadDir(); // 파일을 업로드할 폴더 준비

      // 전송 파일이 없어도 file1MF 객체가 생성됨.
      // <input type='file' class="form-control" name='file1MF' id='file1MF'
      // value='' placeholder="파일 선택">
      MultipartFile mf = replyVO.getFile1MF();
      file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg

      long size1 = mf.getSize(); // 파일 크기
      if (size1 > 0) { // 파일 크기 체크, 파일을 올리는 경우
        if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
          // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
          file1saved = Upload.saveFileSpring(mf, upDir);

          if (Tool.isImage(file1saved)) { // 이미지인지 검사
            // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
            thumb1 = Tool.preview(upDir, file1saved, 200, 150);
          }

          replyVO.setPhoto(file1); // 순수 원본 파일명
          replyVO.setPhoto1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
          replyVO.setThumb1(thumb1); // 원본이미지 축소판
          replyVO.setFilesize(size1); // 파일 크기

        } else { // 전송 못하는 파일 형식
          // ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
          ra.addFlashAttribute("cnt", 0); // 업로드 실패
          ra.addFlashAttribute("url", "/reply/msg"); // msg.html, redirect parameter 적용
          return "redirect:/reply/msg"; // Post -> Get - param...
        }
      } 
      // ------------------------------------------------------------------------------
      // 파일 전송 코드 종료
      // ------------------------------------------------------------------------------

      
      int cnt = this.replyProc.create(replyVO);
      ra.addAttribute("cnt", cnt);
      
      if(cnt == 1) {
        ra.addAttribute("communityno", replyVO.getCommunityno());
        
        return "redirect:/community/read"; // /templates/community/read.html 

      }else {
        // 등록 실패시
        ra.addFlashAttribute("code", "reply_create_fail"); // DBMS 등록 실패
        ra.addFlashAttribute("cnt", 0); // 업로드 실패
        ra.addFlashAttribute("url", "/reply/msg"); // msg.html, redirect parameter 적용
        
        return "redirect:/reply/msg"; // Post -> Get - param...
      }
      
    }else {
      model.addAttribute("code", "no_login");
      return "member/login";
    }

  }
  
  
  // 2. 수정 제작
  // 수정 form 
  @GetMapping("update")
  public String update(Model model, 
                            ReplyVO replyVO,
                            int communityno,
                            int replyno) {
    model.addAttribute("communityno", communityno);
    model.addAttribute("replyno", replyno);
    
    return "reply/update";
  }
  
  
  // 수정 fom 처리
  @PostMapping("update")
  public String update(HttpSession session,
                            RedirectAttributes ra,
                            ReplyVO replyVO, 
                            Model model,
                            int communityno
                            ) {
    
    if (session.getAttribute("id") != null) {
      // 1. 로그인 되었고 작성한 댓글의 memberno 조회
      if (replyVO.getMemberno() == (int) session.getAttribute("memberno")) {
        
        // update 처리 과정 진행
        
        // 1. 이미지 업데이트 처리
        // - advance) 우측에 드래앤 드롭으로 이미지 기능 제작
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 시작
        // ------------------------------------------------------------------------------
        String file1 = ""; // 원본 파일명 image
        String file1saved = ""; // 저장된 파일명, image
        String thumb1 = ""; // preview image

        String upDir = Reply.getUploadDir(); // 파일을 업로드할 폴더 준비

        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF'
        // value='' placeholder="파일 선택">
        MultipartFile mf = replyVO.getFile1MF();
        file1 = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg

        long size1 = mf.getSize(); // 파일 크기
        if (size1 > 0) { // 파일 크기 체크, 파일을 올리는 경우
          if (Tool.checkUploadFile(file1) == true) { // 업로드 가능한 파일인지 검사
            // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg, spring_2.jpg...
            file1saved = Upload.saveFileSpring(mf, upDir);

            if (Tool.isImage(file1saved)) { // 이미지인지 검사
              // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
              thumb1 = Tool.preview(upDir, file1saved, 200, 150);
            }

            replyVO.setPhoto(file1); // 순수 원본 파일명
            replyVO.setPhoto1saved(file1saved); // 저장된 파일명(파일명 중복 처리)
            replyVO.setThumb1(thumb1); // 원본이미지 축소판
            replyVO.setFilesize(size1); // 파일 크기
            
          } else { // 전송 못하는 파일 형식
            ra.addFlashAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
            ra.addFlashAttribute("cnt", 0); // 업로드 실패
            ra.addFlashAttribute("url", "/reply/msg"); // msg.html, redirect parameter 적용
            return "redirect:/reply/msg"; // Post -> Get - param...
          }
          this.replyProc.update_contents(replyVO);
          this.replyProc.update_file(replyVO);
          
        } else { 
          // 글만 수정
          this.replyProc.update_contents(replyVO);
        }
 
        return "redirect:/community/read?communityno="+ communityno;

      } else { // 댓글을 글쓴이가 아닐때
        model.addAttribute("code", "update_fail");
        return "/reply/msg";
      }

    } else {
      model.addAttribute("code", "no_login");
      return "member/login";
    }
    
    
  }
  

  /**
   * 삭제 폼
   * http://localhost:9093/reply/delete?replyno=1
   * @param model
   * @param replyVO
   * @param communityno
   * @param replyno
   * @return
   */
  @GetMapping(value = "/delete")
  public String delete(Model model, 
                              ReplyVO replyVO,
                              int communityno,
                              int replyno
                              ) {
    model.addAttribute("communityno", communityno);
    model.addAttribute("replyno", replyno);
    
    return "reply/delete";
  }
  

  /**
   * 삭제 처리
   * http://localhost:9093/reply/delete?replyno=1
   * @param ra
   * @param session
   * @param model
   * @param replyVO
   * @param communityno
   * @param replyno
   * @return
   */
  @PostMapping(value = "/delete")
  public String delete(RedirectAttributes ra,
                            HttpSession session,
                            Model model,
                            ReplyVO replyVO,
                            int communityno,
                            int replyno) {
    // 1. 로그인 되었고 작성한 댓글의 memberno 조회
    if (replyVO.getMemberno() == (int) session.getAttribute("memberno")) {
      // -------------------------------------------------------------------
      // 파일 삭제 시작
      // -------------------------------------------------------------------
      // 삭제할 파일 정보를 읽어옴.
      ReplyVO replyVO_read = this.replyProc.read(replyno);
      
      String file1saved = replyVO_read.getPhoto1saved();
      String thumb1 = replyVO_read.getThumb1();
      
      String uploadDir = Reply.getUploadDir();
      Tool.deleteFile(uploadDir, file1saved);  // 실제 저장된 파일삭제
      Tool.deleteFile(uploadDir, thumb1);     // preview 이미지 삭제
      // -------------------------------------------------------------------
      // 파일 삭제 종료
      // -------------------------------------------------------------------
      
      this.replyProc.delete_reply(replyno); // DBMS 삭제
      
    } else {
      model.addAttribute("code", "no_login");
      return "member/login";
    }
       
    
    return "redirect:/community/read?communityno="+ communityno; 
    
  }
  
  @GetMapping(value = "/update_increase_cnt_like")
  public String update_increase_cnt_like(Model model, 
                                          ReplyVO replyVO,
                                          int communityno,
                                          @RequestParam(name="replyno", defaultValue = "1") int replyno
  ) {
    
    this.replyProc.update_increase_cnt_like(replyno);
    return "redirect:/community/read?communityno="+ communityno;
  }
 
  
  
}
