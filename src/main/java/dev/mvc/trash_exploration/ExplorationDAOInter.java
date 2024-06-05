package dev.mvc.trash_exploration;

import java.util.ArrayList;

public interface ExplorationDAOInter {
  /**
   * 쓰레기 탐구 이미지 등록
   * 
   * @param explorationVO
   * @return
   */
  public int create(ExplorationVO explorationVO);

  /**
   * 쓰레기 탐구 목록
   * 
   * @return ArrayList<ExplorationVO>
   */
  public ArrayList<ExplorationVO> list_all();

  /**
   * 쓰레기 탐구 항목 조회
   * 
   * @param expno
   * @return
   */
  public ExplorationVO read(int expno);
  
  /**
   * 쓰레기 탐구 항목 업데이트
   * @param explorationVO
   * @return
   */
  public int update(ExplorationVO explorationVO);
  
  /**
   * 쓰레기 탐구 항목 삭제
   * @param expno
   * @return
   */
  public int delete(int expno);
}
