package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;

import java.util.List;

public interface IFiliere {

    int saveFiliere (Filiere filiere) throws IsjException;
    List<Filiere> listFilieres();
    int deleteFiliere(Long code);
    Filiere getFiliereByCode(Long code) throws IsjException;
    int updateFiliere (Filiere filiere) throws IsjException;


    Filiere getFiliereByLibelleFiliere(String libelleFiliere) throws IsjException;
}
