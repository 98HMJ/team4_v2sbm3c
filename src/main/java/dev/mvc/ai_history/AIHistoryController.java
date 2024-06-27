package dev.mvc.ai_history;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/ai_history")
public class AIHistoryController {

  @Autowired
  private AIHistoryService service;

  @PostMapping("/save")
  public String saveAIHistory(HttpSession session, @RequestBody AIHIistoryVO aiHistoryVO) {
    service.saveAIHistory(session, aiHistoryVO);
    return "Data saved successfully";
  }

}