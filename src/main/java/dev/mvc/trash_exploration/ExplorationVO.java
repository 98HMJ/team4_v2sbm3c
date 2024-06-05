package dev.mvc.trash_exploration;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ExplorationVO {
  /** 탐구 번호 */
  private int expno;
  
  /** 탐구 내용 */
  private String exponame;
  
  // --------------- 이미지 처리 ----------------
  /** 메인 이미지 */
  private String t_img = "";
  private String t_saved = "";
  private String t_thumb = "";
  private long t_size;
  private MultipartFile file0MF = null;
  
  private String c1_img = "";
  private String c1_saved = "";
  private long c1_size;
  private MultipartFile file1MF = null;

  private String c2_img = "";
  private String c2_saved = "";
  private long c2_size;
  private MultipartFile file2MF = null;

  private String c3_img = "";
  private String c3_saved = "";
  private long c3_size;
  private MultipartFile file3MF = null;

  private String c4_img = "";
  private String c4_saved = "";
  private long c4_size;
  private MultipartFile file4MF = null;

  private String c5_img = "";
  private String c5_saved = "";
  private long c5_size;
  private MultipartFile file5MF = null;

  private String c6_img = "";
  private String c6_saved = "";
  private long c6_size;
  private MultipartFile file6MF = null;
  
  // --------------- 이미지 처리 ----------------
  
  
}
