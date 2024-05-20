package dev.mvc.singo;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.singo.SingoProc")
public class SingoProc implements SingoProcInter {

  @Autowired
  private SingoDAOInter singoDAO;

  /** 페이지당 출력할 레코드 개수 */
  public static int RECORD_PER_PAGE = 3;

  /** 블럭당 페이지 수, 하나의 블럭은 10개의 페이지로 구성됨 */
  public static int PAGE_PER_BLOCK = 10;

  public SingoProc(){
    // System.out.println("-> SingoProc created!");
  }

  @Override
  public int create(SingoVO singoVO) {
    return this.singoDAO.create(singoVO);
    
  }

  @Override
  public ArrayList<SingoVO> list() {
    return this.singoDAO.list();
  }

  @Override
  public SingoVO read(int singono) {
    return this.singoDAO.read(singono);
  }

  @Override
  public int update(SingoVO singoVO) {
    return this.singoDAO.update(singoVO);
  }

  @Override
  public int delete(int singono) {
    return this.singoDAO.delete(singono);
  }

  @Override
  public ArrayList<SingoVO> search(String word) {
    return this.singoDAO.search(word);
  }
  
}
