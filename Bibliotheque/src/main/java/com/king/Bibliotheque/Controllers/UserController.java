package com.king.Bibliotheque.Controllers;

import com.king.Bibliotheque.Models.User;
import com.king.Bibliotheque.Services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@Slf4j
@AllArgsConstructor
@RestController// This means that this class is a Controller
//@RequestMapping(path="/ENT") // This means URLs start with /ENT (after Application path)
@RequestMapping(consumes = APPLICATION_JSON_VALUE)
public class  UserController {

    private UserService userService;

    @PostMapping(path = "inscription")
    public void inscription(@RequestBody User user){
        log.info("Inscription");
        this.userService.inscription(user);
    }
    @PostMapping(path = "validation")
    public void validation(@RequestBody Map <String, String> validation){
        log.info("Validation");
        this.userService.validation(validation);
    }
}
