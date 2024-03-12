package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;


import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EnvoiMessage;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.EnvoiMessageRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IEnvoiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EnvoimessageServiceImpl implements IEnvoiMessage {
    @Autowired
    EnvoiMessageRepository envoiMessageRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveEnvoiMessage(EnvoiMessage envoiMessage) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(envoiMessage.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        envoiMessage.setCreateur(createur);
        envoiMessage.setModificateur(modificateur);
        EnvoiMessage envoiMessageSave = envoiMessageRepository.save(envoiMessage);
        if (envoiMessageSave ==null){
            throw  new IsjException("un problème est survenu lors de l'enregistrement, veuillez reéssayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return envoiMessageSave.getCode().intValue();
    }

    @Override
    public int deleteEnvoiMessage(Long code) throws IsjException {
        envoiMessageRepository.deleteById(envoiMessageRepository.findById(code).get().getCode());
        return 1;
    }

    @Override
    public int updateEnvoiMessage(EnvoiMessage envoiMessage) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(envoiMessage.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;
        Utilisateur modificateur = utilisateurRepository.findById(envoiMessage.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));;

        envoiMessage.setCreateur(createur);
        envoiMessage.setModificateur(modificateur);
        EnvoiMessage envoiMessageUpdate = envoiMessageRepository.save(envoiMessage);
        if (envoiMessageUpdate == null){
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return envoiMessageUpdate.getCode().intValue();
    }

    @Override
    public List<EnvoiMessage> listEnvoiMessage() {
        return envoiMessageRepository.findAll();
    }

    @Override
    public EnvoiMessage getEnvoiMessageByCode(Long code) throws IsjException {
        return envoiMessageRepository.findById(code).orElseThrow(() -> new IsjException("Message introuvable",Status.NOT_FOUND));
    }
}
