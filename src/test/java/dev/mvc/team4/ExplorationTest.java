package dev.mvc.team4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import dev.mvc.trash_exploration.ExplorationProcInter;
import dev.mvc.trash_exploration.ExplorationVO;

public class ExplorationTest {
  @Autowired
  @Qualifier("dev.mvc.trash.exploration.ExplorationProc")
  private ExplorationProcInter explorationProc;

  @Test
  public void testExplorationCreate() {
    ExplorationVO explorationVO = new ExplorationVO();
    explorationVO.setExpno(2);
    explorationVO.setExponame("탐구1");
    explorationVO.setT_img("11_.jpg");
    explorationVO.setC1_img("11_.jpg");
    
    System.out.println(this.explorationProc.create(explorationVO));
  }
}
