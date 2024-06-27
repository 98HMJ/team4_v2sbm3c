package dev.mvc.ai_history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AIHistoryRepository extends JpaRepository<AIHistory, Integer> {
  
}