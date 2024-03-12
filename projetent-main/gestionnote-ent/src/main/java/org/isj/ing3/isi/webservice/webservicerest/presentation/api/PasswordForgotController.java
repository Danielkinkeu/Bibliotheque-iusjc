package org.isj.ing3.isi.webservice.webservicerest.presentation.api;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.PasswordResetToken;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.PasswordResetTokenRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.EmailService;
import org.isj.ing3.isi.webservice.webservicerest.service.IUtilisateur;
import org.isj.ing3.isi.webservice.webservicerest.utils.Mail;
import org.isj.ing3.isi.webservice.webservicerest.utils.PasswordForgotDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/api/forgot-password")
public class PasswordForgotController {

    @Autowired private IUtilisateur userService;
    @Autowired private PasswordResetTokenRepository tokenRepository;
    @Autowired private EmailService emailService;

    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDto() {
        return new PasswordForgotDto();
    }

    @GetMapping
    public String displayForgotPasswordPage() {
        return "forgot-password";
    }

    @PostMapping("/generateToken")
    public ResponseEntity<String> processForgotPasswordForm(@RequestBody PasswordForgotDto form) {

        Utilisateur user = userService.findByEmail(form.getEmail());
        if (user == null){
            /*result.rejectValue("email", null, "We could not find an account for that e-mail address.");
            return "forgot-password";*/
            return ResponseEntity.ok("redirect:/forgot-password?error=We could not find an account for that e-mail address.");
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(60);
        tokenRepository.save(token);

        Mail mail = new Mail();
        mail.setFrom("gestionnotesisj@institutsaintjean.org");
        mail.setTo(user.getEmail());
        mail.setSubject("Réinitialisation de mot de passe");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("signature", "Support Team - Institut Universitaire Saint Jean");
        //String url = request.getScheme() + "://" + request.getServerName() + ":" + 9100;
        String url = "https://5.196.67.45:8443/gestionnotes";
        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());
        mail.setText("Veuillez cliquer ici pour réinitialiser votre mot de passe: "+url + "/reset-password?token=" + token.getToken()+"\n" +
                "\n" +
                "Support Team - Institut Universitaire Saint Jean");
        mail.setModel(model);
        emailService.sendEmail(mail);

        return ResponseEntity.ok("redirect:/forgot-password?success");

    }

}
