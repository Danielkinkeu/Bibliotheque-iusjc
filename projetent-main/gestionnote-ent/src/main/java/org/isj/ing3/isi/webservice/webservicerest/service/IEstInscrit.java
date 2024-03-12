package org.isj.ing3.isi.webservice.webservicerest.service;


import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;

import java.util.List;
import java.util.Optional;


public interface IEstInscrit {
    int saveInscrit(EstInscrit estInscrit) throws IsjException;
    int deleteInscrit(Long code) throws IsjException;
    int updateEstInscrit(EstInscrit estInscrit ) throws IsjException;
    List<EstInscrit> listInscrit();

    EstInscrit ListeDesInscrit(Long code) throws  IsjException;

    EstInscrit getInscritByCode(Long code) throws IsjException;
    EstInscrit getInscritByCandidatInscritAndEnseignement (Long CodeCandidat,Long CodeEnseignement) throws  IsjException;
}
