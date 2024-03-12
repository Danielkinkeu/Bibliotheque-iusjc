package org.isj.ing3.isi.webservice.webservicerest.service;



import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;

import java.util.List;

public interface IEnseignement {
    int saveEnseignement(Enseignement enseignement) throws IsjException;
    int deleteEnseignement(Long code) throws IsjException;
  int updateEnseignement(Enseignement enseignement ) throws IsjException;
    List<Enseignement> listEnseignements();
    Enseignement getEnseignementByCode(Long code) throws IsjException;


}
