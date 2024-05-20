package dev.mvc.nephroncate;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("dev.mvc.nephroncate.nephroncateProc")
public class NephroncateProc implements NephroncateProcInter{
  @Autowired
  private NephroncateDAOInter nephroncateDAO;
  
  public NephroncateProc() {
    
  }
  
  @Override
  public int create(NephroncateVO nephroncateVO) {
    int cnt = this.nephroncateDAO.create(nephroncateVO);
    return cnt;
  }

  @Override
  public ArrayList<NephroncateVO> nephroncate_list_all() {
    ArrayList<NephroncateVO> list = this.nephroncateDAO.nephroncate_list_all();
    return list;
  }

  @Override
  public NephroncateVO nephroncate_read(int nephroncateno) {
    NephroncateVO nephroncateVO = this.nephroncateDAO.nephroncate_read(nephroncateno);
    return nephroncateVO;
  }

  @Override
  public int nephroncate_update(NephroncateVO nephroncateVO) {
    int cnt = this.nephroncateDAO.nephroncate_update(nephroncateVO);
    return cnt;
  }

  @Override
  public int nephroncate_delete(int nephroncateno) {
    int cnt = this.nephroncateDAO.nephroncate_delete(nephroncateno);
    return cnt;
  }
  

}
