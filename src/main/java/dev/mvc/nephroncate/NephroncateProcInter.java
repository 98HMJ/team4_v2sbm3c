package dev.mvc.nephroncate;

import java.util.ArrayList;

public interface NephroncateProcInter {
  /**
   * 네프론 카테고리 등록
   * insert id="create" parameterType="dev.mvc.nephroncate.NephroncateVO"
   * @param nephroncateVO
   * @return
   */
  public int create(NephroncateVO nephroncateVO);
  
  /**
   * 네프론 카테고리 목록
   * select id="nephroncate_list_all" resultType="dev.mvc.nephroncate.NephroncateVO"
   * @return
   */
  public ArrayList<NephroncateVO> nephroncate_list_all();
  
  /**
   * 네프론 카테고리 조회
   * select id="nephroncate_read" resultType="dev.mvc.nephroncate.NephroncateVO" parameterType="int"
   * @param nephroncateno
   * @return
   */
  public NephroncateVO nephroncate_read(int nephroncateno);
  
  /**
   * 네프론 카테고리 수정
   * update id="nephroncate_update" parameterType="dev.mvc.nephroncate.NephroncateVO"
   * @param nephroncateVO
   * @return
   */
  public int nephroncate_update(NephroncateVO nephroncateVO);
  
  /**
   * 네프론 카테고리 삭제
   * delete id="nephroncate_delete" parameterType="int"
   * @param nephroncateno
   * @return
   */
  public int nephroncate_delete(int nephroncateno);

}
