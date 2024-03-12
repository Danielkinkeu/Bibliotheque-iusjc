package org.isj.ing3.isi.webservice.webservicerest.presentation.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/test")
    public String test() {
        return "test is ok";
    }

}
