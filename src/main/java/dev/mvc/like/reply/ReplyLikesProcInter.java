package dev.mvc.like.reply;

public interface ReplyLikesProcInter {
  /**
   * 등록 - 댓글 좋아요 
   * @param communityLikesVO
   * @return
   */
  public int create(ReplyLikesVO replyLikesVO);
  
  /**
   * cnt - 댓글 좋아요 수
   * @param communityno
   * @return
   */
  public int count_by_communityno(int replyno);
  
  /**
   * 댓글 좋아요 수 증가
   * @param replyno
   * @return
   */
  public int update_cnt_plus(int replyno);
  
  /**
   * 댓글 좋아요 수 감소
   * @param replyno
   * @return
   */
  public int update_cnt_minus(int replyno);
  
}
