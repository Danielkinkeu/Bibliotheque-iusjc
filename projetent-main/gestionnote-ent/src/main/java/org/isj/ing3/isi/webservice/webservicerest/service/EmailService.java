package org.isj.ing3.isi.webservice.webservicerest.service;

import lombok.NoArgsConstructor;
import org.isj.ing3.isi.webservice.webservicerest.utils.Mail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
@NoArgsConstructor
public class EmailService {
    private final static String EMAIL_CONFIRMATION_SUBJECT = "Confirm your udeesa account";

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendConfirmationEmail(String token, String email) {
        // build email
        // send message
        String message = "Welcome to Udeesa, test token" + token;
        String from = "no-reply@udeesa.org";
        send(email, from,EMAIL_CONFIRMATION_SUBJECT, message);
    }

    @Async
    void send(String to, String from,String subject,  String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(email);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            throw new IllegalStateException("failed to send email");
        }
    }

    public void sendEmail(Mail mail) {
        send(mail.getTo(), mail.getFrom(), mail.getSubject(),mail.getText());
    }
}