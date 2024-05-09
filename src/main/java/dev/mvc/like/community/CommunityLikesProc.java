package dev.mvc.like.community;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.like.community.CommunityLikesProc")
public class CommunityLikesProc implements CommunityLikesProcInter{

  @Autowired
  private CommunityLikesDAOInter communityLikesDAOInter;
  
  public CommunityLikesProc(){
    System.out.println("-> CommunityLikesProc Created.");
  }
  @Override
  public int create(CommunityLikesVO communityLikesVO) {
    int cnt = this.communityLikesDAOInter.create(communityLikesVO);
    return cnt;
  }

  @Override
  public int count_by_communityno(int communityno) {
    int cnt = this.communityLikesDAOInter.count_by_communityno(communityno);
    return cnt;
  }

  @Override
  public int update_cnt_plus(int communityno) {
    int cnt = this.update_cnt_plus(communityno);
    return cnt;
  }

  @Override
  public int update_cnt_minus(int communityno) {
    int cnt = this.update_cnt_minus(communityno);
    return cnt;
  }
  
  
  
}
