package dev.mvc.nephron;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value="/nephron")
@Controller
public class NephronCont {
  @Autowired
  @Qualifier("dev.mvc.nephron.NephronProc")
  private NephronProcInter nephronProc;
  
  public NephronCont() {
    
  }
  
  @GetMapping(value="/nephron_list_all")
  public ArrayList<NephronVO> nephron_list_all() {
    return nephronProc.nephron_list_all();
  }

}
