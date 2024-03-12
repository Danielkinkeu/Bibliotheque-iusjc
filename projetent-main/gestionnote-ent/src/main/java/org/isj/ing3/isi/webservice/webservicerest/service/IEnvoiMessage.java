package org.isj.ing3.isi.webservice.webservicerest.service;


import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EnvoiMessage;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;

import java.util.List;

public interface IEnvoiMessage {
    int saveEnvoiMessage(EnvoiMessage envoiMessageDto) throws IsjException;
    int deleteEnvoiMessage(Long code) throws IsjException;
    int updateEnvoiMessage(EnvoiMessage envoiMessage ) throws IsjException;
    List<EnvoiMessage> listEnvoiMessage();

    EnvoiMessage getEnvoiMessageByCode(Long code) throws IsjException;
}
