package dev.mvc.member;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberVO {
    /* 회원번호 */
    private int memberno;

    /* 성함 */
    private String name = "";

    /* 성별 */
    private String sex = "";

    /* 나이 */
    private int age = 0;

    /* 아이디 */
    private String id = "";

    /* 패스워드 */
    private String password = "";

    /* 별명 */
    private String nickname = "";

    /* 전화번호 */
    private String tel = "";

    /* 주소1 */
    private String address1 = "";

    /* 주소2 */
    private String address2 = "";

    /* 이메일 */
    private String email = "";
    
    /* 등록일 */
    private String rdate = "";
}
