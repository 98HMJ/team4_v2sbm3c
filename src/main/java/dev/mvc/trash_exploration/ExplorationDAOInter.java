package dev.mvc.trash_exploration;

public interface ExplorationDAOInter {
  /**
   * 쓰레기 탐구 이미지 등록
   * @param explorationVO
   * @return
   */
  public int create(ExplorationVO explorationVO);
}
