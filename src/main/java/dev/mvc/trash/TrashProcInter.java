package dev.mvc.trash;

import java.util.ArrayList;

public interface TrashProcInter {
  /**
   * 쓰레기 등록
   * insert id="create" parameterType="dev.mvc.trash.TrashVO"
   * @param TrashVO
   * @return
   */
    public int create(TrashVO trashVO);
    
    /**
     * 쓰레기 목록
     * select id="trash_list_all" resultType="dev.mvc.trash.TrashVO"
     * @return TrashVO
     */
    public ArrayList<TrashVO> trash_list_all();
    
    /**
     * 쓰레기 조회
     * select id="trash_read" resultType="dev.mvc.trash.TrashVO" parameterType="int"
     * @param trashno
     * @return
     */
    public TrashVO trash_read(int trashno);
    
    /**
     * 쓰레기 검색
     * select id="trash_list_search" resultType="dev.mvc.trash.TrashVO parameterType="String""
     * @return TrashVO
     */
    public ArrayList<TrashVO> trash_list_search(String word);
    
    /**
     * 쓰레기 수정
     * update id="trash_update" parameterType="dev.mvc.trash.TrashVO"
     * @param trashVO
     * @return
     */
    public int trash_update(TrashVO trashVO);
    
    /**
     * 쓰레기 삭제
     * delete id="trash_delete" parameterType="int"
     * @param trashno
     * @return
     */
    public int trash_delete(int trashno);
}
