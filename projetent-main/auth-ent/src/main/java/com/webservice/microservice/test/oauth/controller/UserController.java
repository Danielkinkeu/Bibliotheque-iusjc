package com.webservice.microservice.test.oauth.controller;

import com.webservice.microservice.test.oauth.dto.*;
import com.webservice.microservice.test.oauth.model.User;
import com.webservice.microservice.test.oauth.reponse.JwtResponse;
import com.webservice.microservice.test.oauth.jwt.JwtProvider;

import com.webservice.microservice.test.oauth.service.UserDetailsServiceImpl;
import com.webservice.microservice.test.oauth.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user")
@RestController

public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;


    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @RequestMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Validated UserDto userDto) {
        //userService.save(userDto);
        if(userDto.getEmail() == null  ) {
            return new ResponseEntity<>("email is null",
                    HttpStatus.OK);
        }
        //Client client = clientservice.getCustomerByEmail(loginRequest.getUsername());
        System.out.println("clear password = " + userDto.getPassword());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
        System.out.println("encrypted password = " + usernamePasswordAuthenticationToken.getCredentials().toString());

        System.out.println("resultat myPassword:"+new BCryptPasswordEncoder().encode("myPassword"));

        Authentication authentication = authenticationManager.authenticate(
                usernamePasswordAuthenticationToken);
        System.out.println("resultat authentification:"+authentication.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //String jwt = jwtProvider.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userDetailsService.findByEmail(userDetails.getUsername());
        List<RoleDto> roleDtos = userDetailsService.getRole(user.getEmail());
        List<DroitDtoPrint> droitDtoPrints = userDetailsService.getDroit(userDetails.getUsername());
        String jwt2 = jwtUtil.generateToken(userDto.getEmail()+"&"+roleDtos.get(0).getLibelle());
        JwtResponse response = new JwtResponse(jwt2, "Bearer", userDetails.getUsername(), userDetails.getAuthorities(), droitDtoPrints,  user.getMatricule(), user.getFiliere(), user.getClasse(), user.getName());
        System.out.println(response);
        return new ResponseEntity<JwtResponse>(response, HttpStatus.CREATED);

    }

    @GetMapping("/test")
    public String test() {
        return "test is successful";
    }

}