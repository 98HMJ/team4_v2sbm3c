package dev.mvc.community;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CommunityVO {
    /* 커뮤니티 번호 */
    private int communityno;

    /* 글 제목 */
    private String title = "";

    /* 글 내용 */
    private String contents = "";

    /* 등록일 */
    private String rdate;

    /* 원본 파일명 */
    private String files = "";

    /* 저장된 파일명 */
    private String thumb = "";

    /* 파일 사이즈 */
    private double filesize = 0;

    /* 지도 */
    private String map = "";

    /* 유튜브 */
    private String youtube = "";

    /* mp4 */
    private String mp4 = "";

    /* 조회수 */
    private int cnt = 0;

    /* 회원번호 */
    private int memberno;

    /* 커뮤니티카테번호 */
    private int coummnitycateno;
}
