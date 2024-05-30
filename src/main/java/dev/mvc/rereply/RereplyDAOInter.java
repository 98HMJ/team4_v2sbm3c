package dev.mvc.rereply;

import java.util.ArrayList;

public interface RereplyDAOInter {

    /**
     * 대댓글 등록
     * @param rereplyVO
     * @return
     */
    public int create(RereplyVO rereplyVO);

    /**
     * 특정 대댓글 목록
     * @param replyno
     * @return
     */
    public ArrayList<RereplyVO> list_by_rereply(int replyno);

    /**
     * 특정 대댓글 
     * @param rereplyno
     * @return
     */
    public RereplyVO read(int rereplyno);

    /**
     * 대댓글 내용 수정
     * @param rereplyVO
     * @return
     */
    public int update_contents(RereplyVO rereplyVO);

    /**
     * 대댓글 파일 수정
     * @param rereplyVO
     * @return
     */
    public int update_file(RereplyVO rereplyVO);

    /**
     * 대댓글 삭제
     * @param rereplyno
     * @return
     */
    public int delete_rereply(int rereplyno);

    /**
     * 특정 댓글의 대댓글 수
     * @param replyno
     * @return
     */
    public int count_by_rereplyno(int replyno);

    /**
     * 대댓글 좋아요 수 증가
     * @param rereplyno
     * @return
     */
    public int update_increase_cnt_like(int rereplyno);
}
