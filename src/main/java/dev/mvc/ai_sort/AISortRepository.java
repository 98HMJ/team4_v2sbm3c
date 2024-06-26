package dev.mvc.ai_sort;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AISortRepository extends JpaRepository<AISort, Integer> {
  
}

