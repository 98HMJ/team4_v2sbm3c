package dev.mvc.singo;

import java.util.ArrayList;
import java.util.Map;

public interface SingoDAOInter {
  /**
   * 신고 등록
   * @param singoVO
   * @return 추가한 레코드 수
   */
  public int create(SingoVO singoVO);

  /**
   * 신고 전체 조회(관리자용)
   * @return 전체 리스트
   */
  public ArrayList<SingoVO> list(Map<String, Object> map);

  /**
   * 검색 레코드 수
   * @param word
   * @return
   */
  public int list_cnt(String word);
  
  /**
   * singono로 신고 조회
   * @param singono
   * @return singono에 해당하는 SingoVO 신고 정보
   */
  public SingoVO read(int singono);

  /**
   * 신고 내용 수정
   * @param singoVO
   * @return
   */
  public int update(SingoVO singoVO);

  /**
   * 신고 삭제
   * @param singono
   * @return
   */
  public int delete(int singono);

  public ArrayList<SingoVO> search(String word);  
}
