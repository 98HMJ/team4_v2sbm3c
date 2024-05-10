package dev.mvc.trashcate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.trashcate.TrashcateProc")
public class TrashcateProc implements TrashcateProcInter{
  @Autowired
  private TrashcateDAOInter trashcateDAO;
  
  public TrashcateProc() {
    
  }

  @Override
  public int create(TrashcateVO trashcateVO) {
    int cnt = this.trashcateDAO.create(trashcateVO);
    return cnt;
  }

  @Override
  public ArrayList<TrashcateVO> trashcate_list_all() {
    ArrayList<TrashcateVO> list = this.trashcateDAO.trashcate_list_all();
    return list;
  }

  @Override
  public TrashcateVO trashcate_read(int trashcateno) {
    TrashcateVO trashcateVO = this.trashcateDAO.trashcate_read(trashcateno);
    return trashcateVO;
  }

  @Override
  public int trashcate_update(TrashcateVO trashcateVO) {
    int cnt = this.trashcateDAO.trashcate_update(trashcateVO);
    return cnt;
  }

  @Override
  public int trashcate_delete(int trashcateno) {
    int cnt = this.trashcateDAO.trashcate_delete(trashcateno);
    return cnt;
  }  
  
}
