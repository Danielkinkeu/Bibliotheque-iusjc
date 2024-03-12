package org.institutsaintjean.intervention.gestionDesInterventions.ServicesImplementation;



import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.ByteArrayDataSource;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.EmailMessage;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.PiecejointeEmail;
import org.institutsaintjean.intervention.gestionDesInterventions.Services.EmailService;
import javax.activation.DataSource;


import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import javax.mail.internet.MimeMultipart;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImplementation  implements EmailService {
    private final JavaMailSender mailSender;

    @Override
    public void EnvoieEmailEtudiantFin(EmailMessage emailMessage) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("gestion.intervention@institutsaintjean.org");
        helper.setTo(emailMessage.getEmail());
        helper.setSubject(emailMessage.getSubject());
        System.out.println("belom jordan dans l'implementation des Emails" + emailMessage.getContent());
        helper.setText(emailMessage.getContent(), true);

        Set<PiecejointeEmail> pieceJointeList = emailMessage.getPiecejointeEmails();
        if (pieceJointeList != null && !pieceJointeList.isEmpty()) {
            Multipart multipart = new MimeMultipart();
            for (PiecejointeEmail pieceJointe : pieceJointeList) {
                // Créez une DataSource à partir du contenu de la pièce jointe
                DataSource dataSource = new ByteArrayDataSource(pieceJointe.getContenu(), pieceJointe.getType());

//                // Créez une MimeBodyPart pour la pièce jointe
//                MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//                attachmentBodyPart.setDataHandler(new DataHandler(dataSource));
//                attachmentBodyPart.setFileName(pieceJointe.getNom());
//
//                // Ajoutez la pièce jointe à la partie multipart du message
//                multipart.addBodyPart(attachmentBodyPart);

                helper.addAttachment(pieceJointe.getNom(), dataSource);
            }
            // Ajoutez le contenu du message et les pièces jointes au message final
//            message.setContent(multipart);
        }


        // Envoyez l'e-mail
        mailSender.send(message);
    }

    @Override
    public void EnvoieEmail(EmailMessage emailMessage) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("gestion.intervention@institutsaintjean.org");
        helper.setTo(emailMessage.getEmail());
        helper.setSubject(emailMessage.getSubject());
        helper.setText(emailMessage.getContent());


        // Envoyez l'e-mail
        mailSender.send(message);

    }
}
