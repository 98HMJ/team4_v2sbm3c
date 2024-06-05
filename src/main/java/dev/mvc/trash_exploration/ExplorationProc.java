package dev.mvc.trash_exploration;

import java.util.ArrayList;

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

  @Override
  public ArrayList<ExplorationVO> list_all() {
    ArrayList<ExplorationVO> list = this.explorationDAOInter.list_all();
    return list;
  }

  @Override
  public ExplorationVO read(int expno) {
    ExplorationVO explorationVO = this.explorationDAOInter.read(expno);
    return explorationVO;
  }

  @Override
  public int update(ExplorationVO explorationVO) {
    int cnt = this.explorationDAOInter.update(explorationVO);
    return cnt;
  }

  @Override
  public int delete(int expno) {
    int cnt = this.explorationDAOInter.delete(expno);
    return cnt;
  }
  
  
}
