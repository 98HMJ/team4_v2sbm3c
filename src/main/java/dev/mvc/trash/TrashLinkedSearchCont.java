package dev.mvc.trash;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value="/trash_linked")
@RestController
public class TrashLinkedSearchCont {
  @Autowired
  @Qualifier("dev.mvc.trash.TrashProc")
  private TrashProcInter trashProc;
  
  @GetMapping(value="/trash_list_search")
  public ArrayList<TrashVO> trash_list_search(String word){
    return this.trashProc.trash_list_search(word);
  }
}
