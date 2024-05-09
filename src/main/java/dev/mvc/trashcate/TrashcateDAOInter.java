package dev.mvc.trashcate;

import java.util.ArrayList;

public interface TrashcateDAOInter {
  /**
   * 쓰레기 카테고리 등록
   * insert id="create" parameterType="dev.mvc.trashcate.TrashcateVO"
   * @param trashcateVO
   * @return
   */
  public int create(TrashcateVO trashcateVO);
  
  /**
   * 쓰레기 카테고리 목록
   * select id="trashcate_list_all" resultType="dev.mvc.trashcate.TrashcateVO"
   * @return
   */
  public ArrayList<TrashcateVO> trashcate_list_all();
  
  /**
   * 쓰레기 카테고리 조회
   * select id="trashcate_read" resultType="dev.mvc.trashcate.TrashcateVO" parameterType="int"
   * @param trashcateno
   * @return
   */
  public TrashcateVO trashcate_read(int trashcateno);
  
  /**
   * 쓰레기 카테고리 수정
   * update id="trashcate_update" parameterType="dev.mvc.trashcate.TrashcateVO"
   * @param trashcateVO
   * @return
   */
  public int trashcate_update(TrashcateVO trashcateVO);
  
  /**
   * 쓰레기 카테고리 삭제
   * delete id="trashcate_delete" parameterType="int"
   * @param trashcateno
   * @return
   */
  public int trashcate_delete(int trashcateno);
}
