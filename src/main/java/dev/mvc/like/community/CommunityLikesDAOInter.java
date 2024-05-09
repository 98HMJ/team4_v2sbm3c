package dev.mvc.like.community;

public interface CommunityLikesDAOInter {
  /**
   * 등록 - 커뮤니티 좋아요 
   * @param communityLikesVO
   * @return
   */
  public int create(CommunityLikesVO communityLikesVO);
  
  /**
   * cnt - 커뮤니티 좋아요 수
   * @param communityno
   * @return
   */
  public int count_by_communityno(int communityno);
  
  /**
   * 커뮤니티 좋아요 수 증가
   * @param communityno
   * @return
   */
  public int update_cnt_plus(int communityno);
  
  /**
   * 커뮤니티 좋아요 수 감소
   * @param communityno
   * @return
   */
  public int update_cnt_minus(int communityno);
  
}
