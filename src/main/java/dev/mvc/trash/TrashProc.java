package dev.mvc.trash;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.trash.TrashProc")
public class TrashProc implements TrashProcInter{
  @Autowired
  private TrashDAOInter trashDAO;
  
  public TrashProc() {
    
  }

  @Override
  public int create(TrashVO trashVO) {
    int cnt = this.trashDAO.create(trashVO);
    return cnt;
  }

  @Override
  public ArrayList<TrashVO> trash_list_all() {
    ArrayList<TrashVO> list = this.trashDAO.trash_list_all();
    return list;
  }

  @Override
  public TrashVO trash_read(int trashno) {
    TrashVO trashVO = this.trashDAO.trash_read(trashno);
    return trashVO;
  }

  @Override
  public ArrayList<TrashVO> trash_list_search(String word) {
    ArrayList<TrashVO> list = this.trashDAO.trash_list_search(word);
    return list;
  }

  @Override
  public int trash_update(TrashVO trashVO) {
    int cnt = this.trashDAO.trash_update(trashVO);
    return cnt;
  }

  @Override
  public int trash_delete(int trashno) {
    int cnt = this.trashDAO.trash_delete(trashno);
    return cnt;
  }

  @Override
  public HashMap<String, Object> trash_read_by_name(String name) {
    HashMap<String, Object> file_dir = new HashMap<String,Object>();
    file_dir=this.trashDAO.trash_read_by_name(name);
    return file_dir;
  }

  
  

}
