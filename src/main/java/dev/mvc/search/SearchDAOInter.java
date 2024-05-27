package dev.mvc.search;

import java.util.ArrayList;

public interface SearchDAOInter {
  /**
   * 검색어 등록
   * insert id="search_create" parameterType="String"
   * @param search_word
   * @return
   */
  public int search_create(String search_word);
  
  /**
   * 검색 기록 목록
   * select id="search_list_all" resultType="dev.mvc.search.SearchVO"
   * @return
   */
  public ArrayList<SearchVO> search_list_all();
  
  /**
   * 검색 기록 조회
   * select id="search_read" resultType="dev.mvc.search.SearchVO"
   * @param searhno
   * @return
   */
  public SearchVO search_read(int searhno);
  
  /**
   * 검색 기록 수정(논리상 사용 x)
   * update id="search_update" parameterType="dev.mvc.search.SearchVO"
   * @param searhVO
   * @return
   */
  public int search_update(SearchVO searhVO);
  
  /**
   * 검색 기록 삭제
   * delete id="search_delete" parameterType="int"
   * @param searchno
   * @return
   */
  public int search_delete(int weeks);
  
  /**
   * 인기 검색어 내림차순 출력(순위)
   * select id="search_popular" resultType="dev.mvc.search.SearchVO"
   * @return
   */
  public ArrayList<SearchVO> search_popular();

}
