package org.institutsaintjean.intervention.gestionDesInterventions.Services;

import javax.mail.MessagingException;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.EmailMessage;



public interface EmailService {
    public void EnvoieEmailEtudiantFin(EmailMessage emailMessage) throws MessagingException;
    public void EnvoieEmail(EmailMessage emailMessage) throws MessagingException;

}
