package com.king.Bibliotheque.Controllers;

import com.king.Bibliotheque.Models.User;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
@RestController// This means that this class is a Controller
//@RequestMapping(path="/ENT") // This means URLs start with /ENT (after Application path)
@RequestMapping(path = "user")
public class  UserController {

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public void addUser(@RequestBody User user){

    }
}
