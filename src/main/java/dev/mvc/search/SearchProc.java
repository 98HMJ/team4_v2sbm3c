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
  public int search_create(String search_word) {
    int cnt = this.searchDAO.search_create(search_word);
    return cnt;
  }

  @Override
  public ArrayList<SearchVO> search_list_all() {
    ArrayList<SearchVO> list = this.searchDAO.search_list_all();
    return list;
  }

  @Override
  public int search_delete(int weeks) {
    int cnt = this.searchDAO.search_delete(weeks);
    return cnt;
  }

  @Override
  public SearchVO search_read(int searhno) {
    SearchVO searchVO = this.searchDAO.search_read(searhno);
    return searchVO;
  }

  @Override
  public int search_update(SearchVO searhVO) {
    int cnt = this.searchDAO.search_update(searhVO);
    return cnt;
  }

  @Override
  public ArrayList<SearchVO> search_popular() {
    ArrayList<SearchVO> list = this.searchDAO.search_popular();
    return list;
  }
  

}
