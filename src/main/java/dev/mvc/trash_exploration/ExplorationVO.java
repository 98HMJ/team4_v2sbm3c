package dev.mvc.trash_exploration;

import java.lang.reflect.Field;

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
  
  
  public String getSavedImage(String fieldName) {
    try {
        // ExplorationVO 클래스에서 fieldName에 해당하는 필드를 찾습니다.
        Field field = ExplorationVO.class.getDeclaredField(fieldName);
        // 필드가 private일 경우 접근 권한을 설정합니다.
        field.setAccessible(true);
        // explorationVO 객체에서 해당 필드의 값을 가져옵니다.
        String savedImage = (String) field.get(this);
        return savedImage;
    } catch (NoSuchFieldException | IllegalAccessException e) {
        // 필드가 존재하지 않거나 접근할 수 없는 경우 에러가 발생할 수 있습니다.
        e.printStackTrace();
        return null;
    }
  }
  
}
