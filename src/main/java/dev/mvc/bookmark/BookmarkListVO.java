package dev.mvc.bookmark;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BookmarkListVO {
    
    /** 북마크 번호 */
    int bookmarno;
    
    String category_name="";
    
    /** 북마크한 시각 */
    String rdate;

    /** 북마크 url */
    String url="";
    
    /** 쓰레기 or 커뮤니티 참조 번호 */
    int ref_no;
    
    int memberno;
    
    /** 쓰레기 or 커뮤니티 표시 */
    String board="";
    
}