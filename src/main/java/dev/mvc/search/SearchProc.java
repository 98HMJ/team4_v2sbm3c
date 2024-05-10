package dev.mvc.search;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.search.SearchProc")
public class SearchProc implements SearchProcInter{
  @Autowired
  private SearchDAOInter searchDAO;
  
  public SearchProc() {
    
  }

  @Override
  public int search_create(SearchVO searchVO) {
    int cnt = this.searchDAO.search_create(searchVO);
    return cnt;
  }

  @Override
  public ArrayList<SearchVO> search_list_all() {
    ArrayList<SearchVO> list = this.searchDAO.search_list_all();
    return list;
  }

  @Override
  public int search_delete(int searchno) {
    int cnt = this.searchDAO.search_delete(searchno);
    return cnt;
  }
  

}
