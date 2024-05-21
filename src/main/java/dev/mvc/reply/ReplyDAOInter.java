package dev.mvc.reply;

import java.util.ArrayList;

public interface ReplyDAOInter {
  
  /**
   * 댓글 등록
   * @param replyVO
   * @return
   */
  public int create(ReplyVO replyVO);
  
  /**
   * 댓글 목록
   * @return ArrayList<ReplyVO>
   */
  public ArrayList<ReplyVO> list_all();
  
  /**
   * 특정 커뮤니티의 댓글 목록
   * @return ArrayList<ReplyVO> 
   */
  public ArrayList<ReplyVO> list_by_community(int communityno);
  
  /**
   * 댓글 내용 업데이트
   * @param replyVO
   * @return
   */
  public int update_contents(ReplyVO replyVO);
  
  /**
   * 댓글 사진 업데이트
   * @param replyVO
   * @return
   */
  public int update_file(ReplyVO replyVO);
  
  /**
   * 댓글 삭제 
   * @param replyno
   * @return
   */
  public int delete_reply(int replyno);
  
  /**
   * 특정 커뮤니티 게시글의 댓글 수
   * @param communityno
   * @return 
   */
  public int count_by_communityno(int communityno);
  
}
