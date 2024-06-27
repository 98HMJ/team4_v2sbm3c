package dev.mvc.ai_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mvc.ai_sort.AISort;
import dev.mvc.ai_sort.AISortRepository;
import dev.mvc.member.MemberDAOInter;
import dev.mvc.tool.Tool;
import jakarta.servlet.http.HttpSession;

import java.util.Date;
import java.util.List;

@Service
public class AIHistoryService {

  private final AIHistoryRepository historyRepository;
  private final AISortRepository sortRepository;
  private final MemberDAOInter memberDAO; // MyBatis DAO 인터페이스

    @Autowired
    public AIHistoryService(AIHistoryRepository historyRepository, AISortRepository sortRepository, MemberDAOInter memberDAO) {
        this.historyRepository = historyRepository;
        this.sortRepository = sortRepository;
        this.memberDAO = memberDAO;
    }

  public void saveAIHistory(HttpSession session, AIHIistoryVO aiHistoryVO) {
    AIHistory aiHistory = new AIHistory();
    aiHistory.setExplaination(aiHistoryVO.getExplaination());
    aiHistory.setRdate(Tool.formatDate(new Date())); // 현재 날짜로 설정
    int memberno = (int) session.getAttribute("memberno");
    if (memberno == 0) {
        throw new RuntimeException("User is not logged in or session has expired");
    }

    aiHistory.setMemberno(memberno);

    
    // sortno를 통해 AISort 객체 설정
    AISort aiSort = sortRepository.findById(aiHistoryVO.getSortno())
        .orElseThrow(() -> new RuntimeException("Sort not found"));
    aiHistory.setAiSort(aiSort);

    historyRepository.save(aiHistory);
  }

  // 분석 결과 조회 메서드
  public List<AIHistory> read(int memberno) {
    return historyRepository.findByMemberno(memberno);
  }

  // 최근 500건의 데이터 조회 메서드
  public List<AIHistory> getRecent500AnalysisResults(int memberno) {
    return historyRepository.findTop500ByMembernoOrderByRdateDesc(memberno);
}


}
