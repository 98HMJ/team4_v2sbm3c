package dev.mvc.trash_exploration;

public interface ExplorationProcInter {
  
  /**
   * 쓰레기 탐구 이미지 등록
   * @param explorationVO
   * @return
   */
  public int create(ExplorationVO explorationVO);
  
}
