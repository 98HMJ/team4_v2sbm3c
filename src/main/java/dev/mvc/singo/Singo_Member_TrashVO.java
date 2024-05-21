package dev.mvc.singo;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Singo_Member_TrashVO {
  /** 신고테이블이 참조하고 있는 회원테이블의 memberno */
  private int memberno;
  /** 회원 닉네임 */
  private String nickname = "";
  /** 신고테이블이 참조하고 있는 쓰레기의 trashno */
  private int trashno;
  /** 쓰레기이름 */
  private String trashname = "";
  /** 신고 */
  private ArrayList<SingoVO> singo_list;
}
