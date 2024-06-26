package dev.mvc.ai_sort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AISortService {

  private final AISortRepository repository;

  @Autowired
  public AISortService(AISortRepository repository) {
    this.repository = repository;
  }

  /** Create, INSERT~, UPDATE~ */
  public AISort saveEntity(AISort entity) {
    return repository.save(entity); // method/SQL 자동 생성
  }

  /** 모든 레코드 출력 */
  public List<AISort> find_all() {
    return repository.findAll(); // method/SQL 자동 생성
  }
  
}