package dev.mvc.community;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import dev.mvc.admin.AdminProcInter;
import dev.mvc.communitycate.CommunityCateProcInter;
import dev.mvc.communitycate.CommunityCateVO;
import dev.mvc.member.MemberProcInter;
import dev.mvc.reply.ReplyMemberVO;
import dev.mvc.reply.ReplyProc;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
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

    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    private MemberProcInter memberProc;

    @Autowired
    @Qualifier("dev.mvc.admin.AdminProc")
    private AdminProcInter adminProc;

    public CommunityCont() {
        System.out.println("-> CommunityCont created.");
    }

    @GetMapping("/main")
    public String main(HttpSession session, Model model) {
        ArrayList<CommunityVO> list = this.communityProc.list();
        model.addAttribute("list", list);
        
        if(session.getAttribute("id") == null) {
          model.addAttribute("code", "no_login");
        }
        
        return "community/main";
    }

    @GetMapping("read_admin")
    public String read_admin(HttpSession session, Model model, int communityno) {
        if(this.adminProc.isAdmin(session)){
            CommunityVO communityVO = this.communityProc.read(communityno);
            model.addAttribute("communityVO", communityVO);

            ArrayList<ReplyMemberVO> list = this.replyProc.list_by_community_join_member(communityno);
            model.addAttribute("list", list);
            return "community/read_admin";
        }else{
            return "admin/login";
        }
    }
    
    @GetMapping("/read")
    public String read(HttpSession session, int communityno, Model model) {
        if (session.getAttribute("id")!=null || session.getAttribute("adminno")!=null) {
            int cnt = this.communityProc.update_cnt(communityno);
            if (cnt != 1) {
                model.addAttribute("code", "update_cnt_fail");
                model.addAttribute("cnt", cnt);
                return "community/msg";
            }

            ArrayList<ReplyMemberVO> list = this.replyProc.list_by_community_join_member(communityno);
            model.addAttribute("list", list);
            
            int reply_cnt = this.replyProc.count_by_communityno(communityno);
            model.addAttribute("reply_cnt", reply_cnt);
            
            int session_memberno = (int) session.getAttribute("memberno");
            model.addAttribute("memberno", session_memberno);

            CommunityVO communityVO = this.communityProc.read(communityno);
            if (communityVO.getMemberno() == session_memberno) {
                model.addAttribute("bool", true);
            } else {
                model.addAttribute("bool", false);
            }
            model.addAttribute("communityVO", communityVO);
            return "community/read";
        } else {
            model.addAttribute("code", "no_login");
            return "redirect:/member/login";
        }
    }

    @GetMapping("/create")
    public String create(HttpSession session, Model model) {
        if (session.getAttribute("id") != null || session.getAttribute("adminno")!=null) {
            ArrayList<CommunityCateVO> list = this.communityCateProc.list();
            model.addAttribute("list", list);
            return "community/create";
        } else {
            return "redirect:/member/login";
        }
    }

    @PostMapping("/create")
    public String create(CommunityVO communityVO, Model model, HttpSession session) {
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 시작
        // ------------------------------------------------------------------------------
        String file = ""; // 원본 파일명 image
        String filesaved = ""; // 저장된 파일명, image
        String thumb = ""; // preview image

        //String upDir = Community.getUploadDir(); // 파일을 업로드할 폴더 준비
        String upDir = "src/main/resources/static/images/test/";

        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF'
        // value='' placeholder="파일 선택">
        ArrayList<MultipartFile> mf_list = new ArrayList<MultipartFile>();
        mf_list.add(communityVO.getFile1MF());
        mf_list.add(communityVO.getFile2MF());

        for (MultipartFile mf : mf_list) {
            if (mf != null) {
                file = mf.getOriginalFilename(); // 원본 파일명 산출, 01.jpg
                long size = mf.getSize(); // 파일 크기
                if (size > 0 && file != null) { // 파일 크기 체크
                    if (Tool.checkUploadFile(file) == true) { // 업로드 가능한 파일인지 검사
                        // 파일 저장 후 업로드된 파일명이 리턴됨, spring.jsp, spring_1.jpg...
                        filesaved = Upload.saveFileSpring(mf, upDir);

                        if (Tool.isImage(filesaved)) { // 이미지인지 검사
                            // thumb 이미지 생성후 파일명 리턴됨, width: 200, height: 150
                            thumb = Tool.preview(upDir, filesaved, 200, 150);
                            communityVO.setFiles(file); // 순수 원본 파일명
                            communityVO.setFilesaved(filesaved); // 저장된 파일명(파일명 중복 처리)
                            communityVO.setThumb(thumb); // 원본이미지 축소판
                            communityVO.setSize1(size); // 파일 크기
                        } else {
                            communityVO.setMp4(file);
                        }
                    } else { // 전송 못하는 파일 형식
                        model.addAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
                        model.addAttribute("cnt", 0);
                        return "member/msg"; // Post -> Get - param...
                    }
                }
            }
        }
        communityVO.setMemberno((int) session.getAttribute("memberno"));
        int cnt = this.communityProc.create(communityVO);
        if (cnt == 1) {
            return "redirect:/community/main";
        } else {
            model.addAttribute("code", "community_create_fail");
            model.addAttribute("cnt", cnt);
            return "community/msg";
        }
    }

    @GetMapping("update")
    public String update(int communityno, Model model, HttpSession session) {
        if (session.getAttribute("id")!=null || session.getAttribute("adminno")!=null) {
            CommunityVO communityVO = this.communityProc.read(communityno);
            model.addAttribute("communityVO", communityVO);
            return "community/update";
        } else{
            return "redirect:/member/login";
        }
    }

    @PostMapping("update")
    public String update(CommunityVO communityVO, Model model) {
        // ------------------------------------------------------------------------------
        // 파일 전송 코드 시작
        // ------------------------------------------------------------------------------
        String file = ""; // 원본 파일명 image
        String filesaved = ""; // 저장된 파일명, image
        String thumb = ""; // preview image

        String upDir = Community.getUploadDir(); // 파일을 업로드할 폴더 준비

        // 전송 파일이 없어도 file1MF 객체가 생성됨.
        // <input type='file' class="form-control" name='file1MF' id='file1MF'
        // value='' placeholder="파일 선택">
        ArrayList<MultipartFile> mf_list = new ArrayList<MultipartFile>();
        mf_list.add(communityVO.getFile1MF());
        mf_list.add(communityVO.getFile2MF());

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
                        communityVO.setFiles(file); // 순수 원본 파일명
                        communityVO.setFilesaved(filesaved); // 저장된 파일명(파일명 중복 처리)
                        communityVO.setThumb(thumb); // 원본이미지 축소판
                        communityVO.setSize1(size); // 파일 크기
                    } else {
                        communityVO.setMp4(file);
                    }
                } else { // 전송 못하는 파일 형식
                    model.addAttribute("code", "check_upload_file_fail"); // 업로드 할 수 없는 파일
                    model.addAttribute("cnt", 0);
                    return "member/msg"; // Post -> Get - param...
                }
            }
        }

        int cnt = this.communityProc.update(communityVO);
        if (cnt == 1) {
            return "redirect:/community/main";
        } else {
            model.addAttribute("code", "community_create_fail");
            model.addAttribute("cnt", cnt);
            return "community/msg";
        }
    }

    @GetMapping("delete")
    public String delete(HttpSession session, int communityno, Model model) {
        CommunityVO communityVO = this.communityProc.read(communityno);
        if (session.getAttribute("id") != null || session.getAttribute("adminno") != null) {
            if (communityVO.getMemberno() == (int) session.getAttribute("memberno")) {
                int cnt = this.communityProc.delete(communityno);
                if (cnt == 1) {
                    model.addAttribute("code", "community_delete_success");
                    return "community/msg";
                } else {
                    model.addAttribute("cnt", cnt);
                    model.addAttribute("code", "community_delete_fail");
                }
            } else {
                model.addAttribute("cnt", 1);
                model.addAttribute("code", "not_access");
            }
            return "community/msg";
        } else {
            return "redirect:/member/login";
        }
    }

    @PostMapping("search")
    public String search(String word, Model model) {
        ArrayList<CommunityVO> list = this.communityProc.search(word);
        model.addAttribute("list", list);
        model.addAttribute("word", word);
        return "community/main";
    }

    @GetMapping("update_increase_cnt_like")
    public String update_likes(int communityno, Model model) {
        int cnt = this.communityProc.update_likes(communityno);
        if (cnt == 1) {
            return "redirect:/community/read?communityno=" + communityno;
        } else {
            model.addAttribute("cnt", cnt);
            model.addAttribute("code", "update_likes_error");
            return "community/msg";
        }
    }

}
