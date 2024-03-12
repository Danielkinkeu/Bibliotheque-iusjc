package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.PasswordResetToken;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.PasswordResetTokenPojo;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.PasswordResetTokenRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IUtilisateur;
import org.isj.ing3.isi.webservice.webservicerest.utils.CryptageSHA;
import org.isj.ing3.isi.webservice.webservicerest.utils.PasswordResetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/api/resetpassword")
public class PasswordResetController {

    @Autowired private IUtilisateur userService;
    @Autowired private PasswordResetTokenRepository tokenRepository;
    //@Autowired private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/handlePasswordReset")
    @Transactional
    public ResponseEntity<Utilisateur> handlePasswordReset(@RequestBody PasswordResetDto form) throws IsjException {

        PasswordResetToken token = tokenRepository.findByToken(form.getToken());
        Utilisateur user = token.getUser();
        //String updatedPassword = passwordEncoder.encode(form.getPassword());
        //String updatedPassword = passwordEncoder.encode(form.getPassword());
        user.setMotDePasse(CryptageSHA.hachage(form.getPassword()));
        userService.updateUtilisateur(user);
        tokenRepository.delete(token);

        //return ResponseEntity.ok("redirect:/login?resetSuccess");
        return ResponseEntity.ok(user);
    }

    @PostMapping("/findToken")
    public ResponseEntity<PasswordResetTokenPojo> rolesUser(@RequestBody PasswordResetDto passwordResetDto)  {

        PasswordResetToken passwordResetToken=tokenRepository.findByToken(passwordResetDto.getToken());
        System.out.println("passwordResetToken="+passwordResetToken);
        PasswordResetTokenPojo passwordResetTokenPojo=new PasswordResetTokenPojo();
        if(passwordResetToken!=null) {
            passwordResetTokenPojo.setToken(passwordResetToken.getToken());
            passwordResetTokenPojo.setUser(passwordResetToken.getUser());
            passwordResetTokenPojo.setExpiryDate(passwordResetToken.getExpiryDate().getTime());
            passwordResetTokenPojo.setId(passwordResetToken.getId());
        }
        return ResponseEntity.ok(passwordResetTokenPojo);

    }

}
