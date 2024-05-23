package dev.mvc.nephron;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import dev.mvc.tool.MyAppProperties;

@RequestMapping(value = "/nephron")
@Controller
public class NephronCont {
  @Autowired
  @Qualifier("dev.mvc.nephron.NephronProc")
  private NephronProcInter nephronProc;

  private final MyAppProperties myAppProperties;

  @Autowired
  public NephronCont(MyAppProperties myAppProperties) {
    this.myAppProperties = myAppProperties;
  }

  @GetMapping(value = "/nephron_list_all")
  public ArrayList<NephronVO> nephron_list_all(Model model) {
    model.addAttribute("map_key", myAppProperties.getMap_key());
    return nephronProc.nephron_list_all();
  }

}
