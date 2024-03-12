package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignant;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;

import java.util.List;

public interface IEnseignant {
    int saveEnseignant(Enseignant enseignant) throws IsjException;
    int deleteEnseignant(Long code) throws  IsjException;
   int updateEnseignant(Enseignant enseignant ) throws IsjException;
    List<Enseignant> listEnseignants();

    Enseignant getEnseignantByCode(Long code) throws IsjException;
    public int nbEnseignant();
}
