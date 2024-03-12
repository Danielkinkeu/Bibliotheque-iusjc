package org.isj.ing3.isi.webservice.webservicerest.serviceImpl;

import org.isj.ing3.isi.webservice.webservicerest.exception.ErrorInfo;
import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Message;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.repositories.MessageRepository;
import org.isj.ing3.isi.webservice.webservicerest.repositories.UtilisateurRepository;
import org.isj.ing3.isi.webservice.webservicerest.service.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zalando.problem.Status;

import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class MessageServiceImpl implements IMessage {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    UtilisateurRepository utilisateurRepository;

    @Override
    public int saveMessage(Message message) throws IsjException{

        Utilisateur createur = utilisateurRepository.findById(message.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = createur;
        message.setCreateur(createur);
        message.setModificateur(modificateur);
        Message messageSave = messageRepository.save(message);
        if (messageSave == null) {
            throw new IsjException("Un problème est survenu lors de l'enregistrement, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return messageSave.getCode().intValue();
    }

    @Override
    public int updateMessage (Message message) throws IsjException {
        Utilisateur createur = utilisateurRepository.findById(message.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        Utilisateur modificateur = utilisateurRepository.findById(message.getCreateur().getCode()).orElseThrow(() -> new IsjException(ErrorInfo.RESSOURCE_NOT_FOUND));
        message.setCreateur(createur);
        message.setModificateur(modificateur);
        Message messageUpdate = messageRepository.save(message);
        if (messageUpdate == null) {
            throw new IsjException("Un problème est survenu lors de la mise à jour, veuillez réessayer plus tard", Status.INTERNAL_SERVER_ERROR);
        }
        return messageUpdate.getCode().intValue();
    }

    @Override
    public List<Message> listMessages() {
        return messageRepository.findAll();
    }

    @Override
    public int deleteMessage(Long code) throws IsjException {
        messageRepository.deleteById(getMessageByCode(code).getCode());
        return 1;
    }

    @Override
    public Message getMessageByCode(Long code) throws IsjException {
        return messageRepository.findById(code).orElseThrow(() -> new IsjException("Message introuvable", Status.NOT_FOUND));
    }
}
