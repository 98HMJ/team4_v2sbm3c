package dev.mvc.bookmark;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookmarkDAOInter {
  
  /**
   * 모든 북마크 목록
   * @param memberno
   * @return
   */
  public ArrayList<BookmarkListVO> list_all(int memberno);
  
  /**
   * 커뮤니티 북마크 생성
   * @param bookmarkVO
   * @return
   */
  public int create_community(BookmarkVO bookmarkVO);
  
  /**
   * 커뮤니티 북마크 확인
   * @param communityno
   * @param memberno
   * @return
   */
  public int check_community(BookmarkCheckCommunityVO bookmarkCheckCommunityVO);
  
  /**
   * 삭제: 커뮤니티 북마크
   * @param noMap
   * @return 삭제한 레코드 수
   */
  public int delete_community(BookmarkCheckCommunityVO bookmarkCheckCommunityVO);
  
  /**
   * 쓰레기 북마크 생성
   * @param bookmarkVO
   * @return
   */
  public int create_trash(BookmarkVO bookmarkVO);
  
  /**
   * 쓰레기 북마크 체크
   * @param vo
   * @return
   */
  public int check_trash(BookmarkCheckTrashVO vo);
  
  /**
   * 쓰레기 북마크 삭제
   * @param vo
   * @return
   */
  public int delete_trash(BookmarkCheckTrashVO vo);
  
  /**
   * 북마크 검색 목록
   * @param hashMap
   * @return
   */
  public ArrayList<BookmarkListVO> list_by_memberno_search_paging(HashMap<String, Object> hashMap);
  
  /**
   * 북마크 검색(카테고리) 개수 조회
   * @param word
   * @return 검색 된 레코드 수
   */
  public int list_by_memberno_search_cnt(HashMap<String, Object> map);
  
}
