package dev.mvc.bookmark;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BookmarkCheckCommunityVO {
    
    /** 북마크 번호 */
    int bookmarno;
    
    /** 북마크한 시각 */
    String rdate="";

    /** 북마크 url */
    String url="";
    
    /** 커뮤니티 번호 */
    int communityno;
    
    /** 회원 번호 */
    int memberno;
    
}