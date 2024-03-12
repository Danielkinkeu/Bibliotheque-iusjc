package com.webservice.microservice.test.oauth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/user")
public class Test {

    @GetMapping("/test")
    public String test() {
        return "Test is ok";
    }

}
