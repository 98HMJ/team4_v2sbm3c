package dev.mvc.team4;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import dev.mvc.bookmark.BookmarkProcInter;
import dev.mvc.bookmark.BookmarkVO;

public class BookmarkTest {
  @Autowired
  @Qualifier("dev.mvc.bookmark.BookmarkProc") // "dev.mvc.admin.AdminProc"라고 명명된 클래스
  private BookmarkProcInter bookmarkProc; // AdminProcInter를 구현한 AdminProc 클래스의 객체를 자동으로 생성하여 할당

  @Test
  public void testDecreaseReplycnt() {
    BookmarkVO bookmarkVO = new BookmarkVO();
    System.out.println(this.bookmarkProc.create_community(bookmarkVO));
  }
}
