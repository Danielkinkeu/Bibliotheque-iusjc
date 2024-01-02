package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Models.Role;
import com.king.Bibliotheque.Models.User;
import com.king.Bibliotheque.Models.Validation;
import com.king.Bibliotheque.Repositories.UserRepository;
import com.king.Bibliotheque.RoleType;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;
    private ValidationService validationService;
    public void inscription(User user){
        if(!user.getEmail().contains("@")) {
            throw new RuntimeException(" mail  incorrect ");
        }
        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new RuntimeException("votre email est deja utilise ");
        }
        String mdpCrypte = this.passwordEncoder.encode(user.getMdp());
        user.setMdp(mdpCrypte);

        Role userRole = new Role();
        userRole.setTitle(RoleType.STUDENT);
        user.setRole(userRole);
        user = this.userRepository.save(user);
        this.validationService.enregistre(user);
    }

    public void validation(Map<String,String> validation) {
        Validation validation1 = this.validationService.lireFctDuCode(validation.get("code"));
        if(Instant.now().isAfter(validation1.getExpiration())){
            throw new RuntimeException(" your activation code expired ");
        }
        User activateUser = this.userRepository.findById(validation1.getUser().getId()).orElseThrow(() -> new RuntimeException("unknown user "));
        activateUser.setActive(true);
        this.userRepository.save(activateUser);
    }

}
