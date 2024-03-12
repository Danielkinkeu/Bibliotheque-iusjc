package com.isi.trombinoscope.Controller;

//import ch.qos.logback.core.model.Model;
import com.isi.trombinoscope.Entities.User;
import com.isi.trombinoscope.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/Trombo")
@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

   @GetMapping("/AllUser")
    public ResponseEntity<List<User>> GetAllUser(){
        List<User> userList = userRepository.findAll();
       System.out.println(userList);
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
