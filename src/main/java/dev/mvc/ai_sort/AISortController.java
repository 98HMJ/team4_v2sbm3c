package dev.mvc.ai_sort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ai-sort")
public class AISortController {

  @Autowired
  private AISortService service;

}