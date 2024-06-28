package dev.mvc.ai_history;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AIHistoryRepository extends JpaRepository<AIHistory, Integer> {
  List<AIHistory> findByMemberno(int memberno);

  // 최근 날짜의 500건의 데이터 조회
  @Query(value = "SELECT historyno, explaination, sortno, rdate, memberno, r FROM (SELECT historyno, explaination, sortno, rdate, memberno, rownum as r FROM (SELECT historyno, explaination, sortno, rdate, memberno FROM ai_history ORDER BY rdate DESC, historyno DESC)) WHERE r <= 500 and memberno = :memberno", nativeQuery = true)
  List<AIHistory> findTop500ByMembernoOrderByRdateDesc(@Param("memberno") int memberno);
}