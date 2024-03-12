package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;

import java.util.List;
import java.util.Optional;

public interface ISpecialite {
    Long saveSpecialite(Specialite specialiteDto) throws IsjException;
    List<Specialite> listSpecialite();
    int deleteSpecialiteByCode(Long code) throws IsjException;
    Specialite searchSpecialiteBySpecialiteOrfiliere(String specialite, String filiere) throws IsjException;
    Long updateSpecialite(Specialite specialiteDto) throws IsjException;

}
