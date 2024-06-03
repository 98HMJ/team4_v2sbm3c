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
  public int check_community(BookmarkCheckCommunityVO bookmarkCheckCommunityVO) {
    int cnt = this.bookmarkDAO.check_community(bookmarkCheckCommunityVO);
    return cnt;
  }

  @Override
  public int delete_community(BookmarkCheckCommunityVO bookmarkCheckCommunityVO) {
    int cnt = this.bookmarkDAO.delete_community(bookmarkCheckCommunityVO);
    return cnt;
  }

  @Override
  public int create_trash(BookmarkVO bookmarkVO) {
    int cnt = this.bookmarkDAO.create_trash(bookmarkVO);
    return cnt;
  }

  @Override
  public int check_trash(BookmarkCheckTrashVO vo) {
    int cnt = this.bookmarkDAO.check_trash(vo);
    return cnt;
  }

  @Override
  public int delete_trash(BookmarkCheckTrashVO vo) {
    int cnt = this.bookmarkDAO.delete_trash(vo);
    return cnt;
  }
  
}
