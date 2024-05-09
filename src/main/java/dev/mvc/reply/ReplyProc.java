package dev.mvc.reply;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("dev.mvc.reply.ReplyProc")
public class ReplyProc implements ReplyProcInter {
  @Autowired
  private ReplyDAOInter replyDAOInter;
  
  public ReplyProc() {
    System.out.println("-> ReplyProc Created");
  }
  
  @Override
  public int create(ReplyVO replyVO) {
    int cnt = this.replyDAOInter.create(replyVO);
    return cnt;
  }

  @Override
  public ArrayList<ReplyVO> list_all() {
    ArrayList<ReplyVO> list = this.replyDAOInter.list_all();
    return list;
  }

  @Override
  public int update_contents(ReplyVO replyVO) {
    int cnt = this.replyDAOInter.update_contents(replyVO);
    return cnt;
  }
  
  @Override
  public int count_by_communityno(int communityno) {
    int cnt = this.replyDAOInter.count_by_communityno(communityno);
    return cnt;
  }
  @Override
  public int delete_reply(int replyno) {
    int cnt = this.replyDAOInter.delete_reply(replyno);
    return cnt;
  }
  
}
