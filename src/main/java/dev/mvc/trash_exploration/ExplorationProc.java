package dev.mvc.trash_exploration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.trash.exploration.ExplorationProc")
public class ExplorationProc implements ExplorationProcInter{
  
  @Autowired
  private ExplorationDAOInter explorationDAOInter;
  
  public ExplorationProc() {
    
  }

  @Override
  public int create(ExplorationVO explorationVO) {
    int cnt = this.explorationDAOInter.create(explorationVO);
    return cnt;
  }
  
  
}
