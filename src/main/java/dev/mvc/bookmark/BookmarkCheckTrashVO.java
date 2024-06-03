package dev.mvc.bookmark;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class BookmarkCheckTrashVO {

    /** 쓰레기 번호 */
    int trashno;
    
    /** 회원 번호 */
    int memberno;
    
}