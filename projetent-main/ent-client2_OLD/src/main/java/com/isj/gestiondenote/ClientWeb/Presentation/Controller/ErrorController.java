package com.isj.gestiondenote.ClientWeb.Presentation.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @GetMapping("/error404")
    public String pageErreur404(Model model){
        return "error-404";
    }


    @GetMapping("/error500")
    public String pageErreur500(Model model){
        return "error-500";
    }
}
