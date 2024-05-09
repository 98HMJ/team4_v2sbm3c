package dev.mvc.like.reply;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.like.reply.ReplyLikesProc")
public class ReplyLikesProc implements ReplyLikesProcInter{

  @Autowired
  private ReplyLikesDAOInter replyLikesDAOInter;
  
  @Override
  public int create(ReplyLikesVO replyLikesVO) {
    int cnt = this.replyLikesDAOInter.create(replyLikesVO);
    return cnt;
  }

  @Override
  public int count_by_communityno(int replyno) {
    int cnt = this.replyLikesDAOInter.count_by_communityno(replyno);
    return cnt;
  }

  @Override
  public int update_cnt_plus(int replyno) {
    int cnt = this.replyLikesDAOInter.update_cnt_plus(replyno);
    return cnt;
  }

  @Override
  public int update_cnt_minus(int replyno) {
    int cnt = this.replyLikesDAOInter.update_cnt_minus(replyno);
    return cnt;
  }

}
