package org.institutsaintjean.intervention.gestionDesInterventions.Components;

import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Email;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.EmailMessage;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.PiecejointeEmail;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.EmailMessageRepository;
import org.institutsaintjean.intervention.gestionDesInterventions.Repositories.EmailRepository;
import org.institutsaintjean.intervention.gestionDesInterventions.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class EmailManeger {
    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailMessageRepository emailMessageRepository;

    @Autowired
    private EmailService emailService;
    public void addToEmailMessage(EmailMessage emailMessage) {
        emailMessageRepository.save(emailMessage);

    }

    public void addToEmailMessagePermanent(Email email) {
        emailRepository.save(email);

    }

    public boolean isConnectedToNetwork() {
        try {
            InetAddress address = InetAddress.getByName("www.google.com");
            return address.isReachable(5000); // 5000 ms timeout
        } catch (UnknownHostException e) {
            return false; // Unable to resolve host, so not connected
        } catch (Exception e) {
            return false; // Other exceptions, assume not connected
        }
    }

    @Scheduled(fixedRate = 60000)
    public void checkNetworkAndSendEmails() throws MessagingException {
        if (isConnectedToNetwork()) {
            System.out.println("belom dans shedulate apres le test de connection df");
            List<EmailMessage> emails = emailMessageRepository.findAll();
            if (emails != null && !emails.isEmpty()){
                for (EmailMessage emailMessage : emails) {
                    Set<PiecejointeEmail> pieceJointeList = emailMessage.getPiecejointeEmails();
                    if(pieceJointeList != null && !pieceJointeList.isEmpty()){
                        emailService.EnvoieEmailEtudiantFin(emailMessage);
                        emailMessageRepository.delete(emailMessage);
                    }else {
                        emailService.EnvoieEmail(emailMessage);
                        emailMessageRepository.delete(emailMessage);
                    }

                }
            }

        }
    }


}
