package com.webservice.microservice.test.oauth.service;

import com.webservice.microservice.test.oauth.model.UserInfo;
import com.webservice.microservice.test.oauth.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Inject
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String userName) {

        com.webservice.microservice.test.oauth.model.User user = userRepository.findByEmail(userName).orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with -> username or email : " + userName));

        if (user == null) {
            throw new UsernameNotFoundException(
                    String.format("Username not found for domain, "
                            + "username=%s", userName));
        }
        User.UserBuilder builder;

        builder = User.withUsername(user.getName());
        builder.password(user.getPassword());
        //builder.roles(user.getRole());
        return builder.build();
    }


    
    
}
