package dev.mvc.ai_history;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/ai_history")
public class AIHistoryController {

  @Autowired
  private AIHistoryService service;

  /**
   * ai_history 기록 1개 create
   * http://127.0.0.1:9091/ai_history/create
   * @param session
   * @param aiHistoryVO
   * @return
   */
  @PostMapping("/create")
  public String saveAIHistory(HttpSession session, @RequestBody AIHIistoryVO aiHistoryVO) {
    service.saveAIHistory(session, aiHistoryVO);
    return "Data saved successfully";
  }

  // 분석 결과 조회 엔드포인트
  /**
   * ai 기록 전체 조회
   * http://127.0.0.1:9091/ai_history/read
   * @param session
   * @return
   */
  // @GetMapping("/read")
  // public ResponseEntity<List<AIHistory>> read(HttpSession session) {
  //     // 세션에서 회원 번호 가져오기
  //     int memberno = (int) session.getAttribute("memberno");
  //     if (memberno == 0) {
  //       throw new RuntimeException("User is not logged in or session has expired");
  //     }

  //     List<AIHistory> results = service.read(memberno);
  //     return ResponseEntity.ok(results);
  // }
  
  // 최근 500건의 분석 결과 조회 엔드포인트
  // http://127.0.0.1:9091/ai_history/read_paging
  @GetMapping("/read_paging")
  public ResponseEntity<List<AIHistory>> read_paging(HttpSession session, 
                                                    @RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "6") int pageSize) {
    // 세션에서 회원 번호 가져오기
    int memberno = (int) session.getAttribute("memberno");
    if (memberno == 0) {
      throw new RuntimeException("User is not logged in or session has expired");
    }

    List<AIHistory> recentHistory = service.getRecent500AnalysisResults(memberno);
    int fromIndex = page * pageSize;
    int toIndex = Math.min(fromIndex + pageSize, recentHistory.size());

    if (fromIndex >= recentHistory.size()) {
      return ResponseEntity.ok().body(List.of()); // Empty list if page is out of bounds
    }

    // List<AIHistory> list = recentHistory.subList(fromIndex, toIndex);
    // for (AIHistory aiHistory : list) {
    //   System.out.println("-> aiHistory" + aiHistory);
    // }
    System.out.println("-> memberno: " + memberno);
    return ResponseEntity.ok().body(recentHistory.subList(fromIndex, toIndex));
    
  }

}