package dev.mvc.trash_exploration;

import java.util.ArrayList;

public interface ExplorationProcInter {
  
  /**
   * 쓰레기 탐구 이미지 등록
   * @param explorationVO
   * @return
   */
  public int create(ExplorationVO explorationVO);
  
  /**
   * 쓰레기 탐구 목록
   * @return ArrayList<ExplorationVO>
   */
  public ArrayList<ExplorationVO> list_all ();
  
  /**
   * 쓰레기 탐구 항목 조회
   * 
   * @param expno
   * @return
   */
  public ExplorationVO read(int expno);
}
