package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Email;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.*;
import org.isj.ing3.isi.webservice.webservicerest.service.IDroit;
import org.isj.ing3.isi.webservice.webservicerest.service.IEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmailServiceImpl implements IEmail {
    @Autowired
    EmailRepository emailRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveEmail(Email email) throws  IsjException{
        Utilisateur createur = utilisateurRepository.findById(email.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        email.setCreateur(createur);
        email.setModificateur(modificateur);
        Email emailSave = emailRepository.save(email);
        if (emailSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return emailSave.getCode().intValue();
    }

    @Override
    public int updateEmail(Email email) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(email.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(email.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        email.setCreateur(createur);
        email.setModificateur(modificateur);
        Email emailUpdate  = emailRepository.save(email);
        if (emailUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return emailUpdate.getCode().intValue();
    }


    @Override
    public List<Email> listEmails() {
        return emailRepository.findAll();
    }

    @Override
    public int deleteEmail(Long code) throws IsjException {
        emailRepository.deleteById(emailRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public Email getByEmailCode(Long code) throws IsjException {
        return emailRepository.findById(code).orElseThrow(() -> new IsjException("email introuvable", Status.NOT_FOUND));
    }
}
