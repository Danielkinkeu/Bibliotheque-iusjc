package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;

import java.util.List;

public interface ISession {

    Session saveSession(Session sessionDto)throws IsjException;
    List<Session> listSession();
    int deleteSessionByCode(Long code) throws IsjException;
    Long updateSession(Session session) throws IsjException;
    Session findLastSessionUser(Long utilisateur) throws IsjException;
}
