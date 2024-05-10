package dev.mvc.nephron;

import java.util.ArrayList;

public interface NephronProcInter {
  /**
   * 네프론 등록
   * insert id="create" parameterType="dev.mvc.nephron.NephronVO"
   * @param nephronVO
   * @return
   */
  public int create(NephronVO nephronVO);
  
  /**
   * 네프론 목록
   * select id="nephron_list_all" resultType="dev.mvc.nephron.NephronVO"
   * @return
   */
  public ArrayList<NephronVO> nephron_list_all();
  
  /**
   * 네프론 조회
   * select id="nephron_read" resultType="dev.mvc.nephron.NephronVO" parameterType="int"
   * @param nephronno
   * @return
   */
  public NephronVO nephron_read(int nephronno);
  
  
  /**
   * 네프론 삭제
   * delete id="nephron_delete" parameterType="int"
   * @param nephronno
   * @return
   */
  public int nephron_delete(int nephronno);
  
  /**
   * 네프론 검색
   * select id="nephron_search" resultType="dev.mvc.nephron.NephronVO" parameterType="String"
   * @param word
   * @return
   */
  public ArrayList<NephronVO> nephron_search(String word);

}
