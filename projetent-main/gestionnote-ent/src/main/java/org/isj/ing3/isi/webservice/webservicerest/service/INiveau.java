package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Niveau;

import java.util.List;

public interface INiveau {

    int saveNiveau (Niveau niveau) throws IsjException;
    List<Niveau> listNiveaus();
    int deleteNiveau(Long code) throws IsjException;
    Niveau getNiveauByCode(Long code) throws IsjException;
    int updateNiveau (Niveau niveau) throws IsjException;
    Niveau getNiveauByNumero(long niveau) throws IsjException;
}
