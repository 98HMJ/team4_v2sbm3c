package dev.mvc.search;

import java.util.ArrayList;

public interface SearchProcInter {
  /**
   * 검색어 등록
   * insert id="search_create" parameterType="dev.mvc.search.SearchVO"
   * @param searchVO
   * @return
   */
  public int search_create(SearchVO searchVO);
  
  /**
   * 검색 기록 목록
   * select id="search_list_all" resultType="dev.mvc.search.SearchVO"
   * @return
   */
  public ArrayList<SearchVO> search_list_all();
  
  /**
   * 검색 기록 삭제
   * delete id="search_delete" parameterType="int"
   * @param searchno
   * @return
   */
  public int search_delete(int searchno);

}
