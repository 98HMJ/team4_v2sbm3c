package dev.mvc.nephron;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.nephron.NephronProc")
public class NephronProc implements NephronProcInter{
  @Autowired
  private NephronDAOInter nephronDAO;
  
  public NephronProc() {
    
  }

  @Override
  public int create(NephronVO nephronVO) {
    int cnt = this.nephronDAO.create(nephronVO);
    return cnt;
  }

  @Override
  public ArrayList<NephronVO> nephron_list_all() {
    ArrayList<NephronVO> list = this.nephronDAO.nephron_list_all();
    return list;
  }

  @Override
  public NephronVO nephron_read(int nephronno) {
    NephronVO nephronVO = this.nephronDAO.nephron_read(nephronno);
    return nephronVO;
  }

  @Override
  public int nephron_delete(int nephronno) {
    int cnt = this.nephronDAO.nephron_delete(nephronno);
    return cnt;
  }

  @Override
  public ArrayList<NephronVO> nephron_search(String word) {
    ArrayList<NephronVO> list = this.nephronDAO.nephron_search(word);
    return list;
  }

}
