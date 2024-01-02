package com.king.Bibliotheque.Services;

import com.king.Bibliotheque.Models.Validation;
import lombok.AllArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public class NotificationService {
    JavaMailSender javaMailSender;
    public void send(Validation validation){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("franck.kinkeu@institutsaintjean.org");
        mailMessage.setTo(validation.getUser().getEmail());
        mailMessage.setSubject("your activation account ");
        String text = String.format(
                "Hello %s, your activation account is: %s; Bye",
                validation.getUser().getName(),
                validation.getCode()
        );
        mailMessage.setText(text);
        javaMailSender.send(mailMessage);
    }
}
