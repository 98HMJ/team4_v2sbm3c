package dev.mvc.bookmark;

import java.util.ArrayList;
import java.util.HashMap;

public interface BookmarkProcInter {
  
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
   * 삭제: 쓰레기 북마크
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
  
  
  /**
   * SPAN태그를 이용한 박스 모델의 지원, 1 페이지부터 시작 
   * 현재 페이지: 11 / 22   [이전] 11 12 13 14 15 16 17 18 19 20 [다음] 
   *
   * @param memberno 멤버 번호
   * @param now_page 현재 페이지
   * @param word 검색어
   * @param list_file 목록 파일명
   * @param search_count 검색 레코드수   
   * @param record_per_page 페이지당 레코드 수
   * @param page_per_block 블럭당 페이지 수
   * @return 페이징 생성 문자열
   */ 
  public String pagingBox(int memberno, int now_page, String word, String list_file, int search_count, int record_per_page,
      int page_per_block);
  
}
