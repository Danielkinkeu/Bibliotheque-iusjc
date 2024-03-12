package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.SessionRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.ISession;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.StatutVie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zalando.problem.Status;

import javax.persistence.Table;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class SessionServiceImpl implements ISession {
    @Autowired
    SessionRepository sessionRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public Session saveSession(Session session) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(session.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;

        session.setCreateur(createur);
        session.setModificateur(modificateur);
        session.setDateCreation(new Date());
        session.setDateModification(new Date());
        session.setStatutVie(StatutVie.ACTIVE);
        Session sessionSave = sessionRepository.save(session);
        if (sessionSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement'', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return sessionSave;
    }

    @Override
    public List<Session> listSession() {
        return sessionRepository.findAll();
    }

    @Override
    public int deleteSessionByCode(Long code) throws IsjException {
        sessionRepository.deleteById(sessionRepository.findById(code).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND)).getCode());
        return 1;
    }

    @Override
    public Long updateSession(Session session) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(session.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(session.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));

        session.setCreateur(createur);
        session.setModificateur(modificateur);
        Session sessionUpdate = sessionRepository.save(session);
        if (sessionUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la modification'', veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return sessionUpdate.getCode();
    }

    @Override
    public Session findLastSessionUser(Long codeuUtilisateur) throws IsjException {
        Utilisateur utilisateur = utilisateurRepository.findById(codeuUtilisateur).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        return sessionRepository.getLastSessionUser(utilisateur).orElseThrow(() -> new IsjException("Aucune session retrouvée", Status.NOT_FOUND));
    }


}
