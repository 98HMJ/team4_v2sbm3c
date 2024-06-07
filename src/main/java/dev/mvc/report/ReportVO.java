package dev.mvc.report;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ReportVO {
  // 보고서 번호 (순차적으로 매겨짐)
  int reportno_m;
  
  // 카테고리 이름 (SINGO, REPLY, COMMUNITY)
  String category_name;
  
  // 쓰레기 신고 번호 (SINGO인 경우에만 해당)
  int singno;
  
  // 커뮤니티 및 댓글 신고 번호 
  int reportno;
  
  // 내용 (문자열)
  String contents="";
  
  // 작성일자 (문자열 형식)
  String rdate;
  
  // 회원 번호
  int memberno;
  
  // 쓰레기 번호 (TRASH인 경우에만 해당)
  int trashno;
  
  // 리플 번호 (REPLY인 경우에만 해당)
  int replyno;
  
  // 커뮤니티 번호 (COMMUNITY인 경우에만 해당)
  int communityno;
}
