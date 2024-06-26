package dev.mvc.bookmark;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BookmarkCheckCommunityVO {

    /** 커뮤니티 번호 */
    int communityno;
    
    /** 회원 번호 */
    int memberno;
    
}