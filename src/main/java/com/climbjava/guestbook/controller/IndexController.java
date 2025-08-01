package com.climbjava.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class IndexController {

  @GetMapping("")
  @ResponseBody
  public Map<?, ?> index() {
    return Map.of("test", 123456);
  }

}
