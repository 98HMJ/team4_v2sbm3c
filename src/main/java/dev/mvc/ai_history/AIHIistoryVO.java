package dev.mvc.ai_history;

import java.util.Date;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

/**
 * AIHIistoryVO
 */
@Getter @Setter @ToString
public class AIHIistoryVO {
  /** 기록 번호 */
  int historyno;
  
  /** 설명 */
  String explaination;

  /** 분류 번호 */
  int sortno;

  /** 기록 날짜 */
  Date rdate;

  /** 기록한 회원 */
  int memberno;

}