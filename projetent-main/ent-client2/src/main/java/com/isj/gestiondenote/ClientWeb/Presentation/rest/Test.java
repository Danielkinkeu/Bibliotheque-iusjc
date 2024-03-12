package com.isj.gestiondenote.ClientWeb.Presentation.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/test")
    public  String test() {
      return "Test is ok";
    }
}
