package dev.mvc.bookmark;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.bookmark.BookmarkProc")
public class BookmarkProc implements BookmarkProcInter{
  @Autowired
  BookmarkDAOInter bookmarkDAO;

  @Override
  public int create_community(BookmarkVO bookmarkVO) {
    int cnt = this.bookmarkDAO.create_community(bookmarkVO);
    return cnt;
  }
  
  @Override
  public BookmarkCheckCommunityVO check_community(HashMap<Integer, Object> noMap) {

    // 값 할당
    BookmarkCheckCommunityVO bookmarkVO = this.bookmarkDAO.check_community(noMap);
    return bookmarkVO;
  }

  @Override
  public int create_trash(BookmarkVO bookmarkVO) {
    int cnt = this.bookmarkDAO.create_trash(bookmarkVO);
    return cnt;
  }
  
}
