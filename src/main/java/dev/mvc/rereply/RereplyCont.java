package dev.mvc.rereply;

import java.util.ArrayList;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import dev.mvc.member.MemberProcInter;
import dev.mvc.member.MemberVO;
import dev.mvc.reply.Reply;
import dev.mvc.reply.ReplyProcInter;
import dev.mvc.reply.ReplyVO;
import dev.mvc.tool.Tool;
import dev.mvc.tool.Upload;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/rereply")
public class RereplyCont {
    @Autowired
    @Qualifier("dev.mvc.rereply.RereplyProc")
    RereplyProcInter rereplyProc;

    @Autowired
    @Qualifier("dev.mvc.member.MemberProc")
    MemberProcInter memberProc;

    @Autowired
    @Qualifier("dev.mvc.reply.ReplyProc")
    ReplyProcInter replyProc;

    public RereplyCont() {
        System.out.println("-> RereplyCont created.");
    }

    @PostMapping("create")
    public String create(HttpSession session, int replyno, String contents, MultipartFile mf) {
        // 클라이언트로부터 전송된 JSON 데이터를 바로 RereplyVO 객체로 매핑
        // 이후 파일 업로드 처리와 관련된 코드를 진행
        // 나머지 코드는 이전과 동일
        RereplyVO rereplyVO = new RereplyVO();

        rereplyVO.setContents(contents);
        rereplyVO.setReplyno(replyno);
        rereplyVO.setFile1MF(mf);
        rereplyVO.setMemberno((int)session.getAttribute("memberno"));

        if(rereplyVO.getFile1MF()!=null){
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
            mf = rereplyVO.getFile1MF();
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

                    rereplyVO.setPhoto(file1); // 순수 원본 파일명
                    rereplyVO.setPhoto1saved(file1saved);// 저장된 파일명(파일명 중복 처리)
                    rereplyVO.setThumb1(thumb1); // 원본이미지 축소판
                    rereplyVO.setFilesize(size1); // 파일 크기

                }
            }
        }
        this.rereplyProc.create(rereplyVO);
        ReplyVO replyVO = this.replyProc.read(rereplyVO.getReplyno());
        return "redirect:/community/read?communityno="+ replyVO.getCommunityno();
    }

    @PostMapping("/list")
    @ResponseBody
    public String list(@RequestBody Map<String, Integer> jsonMap, HttpSession session) {
        ArrayList<RereplyVO> list = this.rereplyProc.list_by_rereply((int)jsonMap.get("replyno"));
        JSONObject data = new JSONObject();
        if (list != null) {
            JSONArray jArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                JSONObject obj = new JSONObject();
                obj.put("rereplyno", list.get(i).getRereplyno());
                obj.put("contents", list.get(i).getContents());
                obj.put("rdate", list.get(i).getRdate());
                obj.put("photo", list.get(i).getPhoto());
                MemberVO memberVO = memberProc.read(list.get(i).getMemberno());
                obj.put("nickname", memberVO.getNickname());
                obj.put("likecnt", list.get(i).getLikecnt());
                obj.put("thumb1", list.get(i).getThumb1());
                obj.put("memberno", list.get(i).getMemberno());
                obj.put("session_memberno",(int)session.getAttribute("memberno"));
                jArray.put(obj);
            }
            data.put("rereply", jArray);
        } else {
            data.put("rereply", "no");
            System.out.println("no");
        }
        return data.toString();
    }

    @GetMapping("update_increase_cnt_like")
    public String getMethodName(int rereplyno) {
        RereplyVO rereplyVO = this.rereplyProc.read(rereplyno);
        ReplyVO replyVO = this.replyProc.read(rereplyVO.getReplyno());
        this.rereplyProc.update_increase_cnt_like(rereplyno);
        return "redirect:/community/read?communityno="+ replyVO.getCommunityno();
    }
    
}
