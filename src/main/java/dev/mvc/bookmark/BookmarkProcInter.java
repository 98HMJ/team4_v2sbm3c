package dev.mvc.bookmark;

import java.util.HashMap;

public interface BookmarkProcInter {
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
  public BookmarkCheckCommunityVO check_community(HashMap<Integer, Object> noMap);
  
  /**
   * 쓰레기 북마크 생성
   * @param bookmarkVO
   * @return
   */
  public int create_trash(BookmarkVO bookmarkVO);
  
}
