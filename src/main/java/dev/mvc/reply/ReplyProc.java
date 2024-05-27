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
  public ArrayList<ReplyVO> list_by_community(int communityno) {
    ArrayList<ReplyVO> list = this.replyDAOInter.list_by_community(communityno);
    return list;
  }
  
  @Override
  public ArrayList<ReplyVO> list_by_community_plus_memberid(int communityno) {
     ArrayList<ReplyVO> list = this.replyDAOInter.list_by_community_plus_memberid(communityno);
     return list;
  }

  @Override
  public int update_contents(ReplyVO replyVO) {
    int cnt = this.replyDAOInter.update_contents(replyVO);
    return cnt;
  }
  
  @Override
  public int update_file(ReplyVO replyVO) {
    int cnt = this.replyDAOInter.update_file(replyVO);
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

  @Override
  public ReplyVO read(int replyno) {
    ReplyVO replyVO = this.replyDAOInter.read(replyno);
    return replyVO;
  }

  @Override
  public int update_increase_cnt_like(int replyno) {
    int cnt = this.replyDAOInter.update_increase_cnt_like(replyno);
    return cnt;
  }

  
}
