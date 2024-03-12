package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;

import java.util.Date;
import java.util.List;

public interface IAnneeAcademique {

 int saveAnneeAcademique (AnneeAcademique anneeAcademique) throws IsjException;
 int  updateAnneeAcademique(AnneeAcademique anneeAcademique ) throws IsjException;
    List<AnneeAcademique> listAnneeAcademique();
    int deleteAnneAcademique(Long code) throws IsjException;
    AnneeAcademique getAnneAcademiqueByCode(Long code) throws IsjException;

    AnneeAcademique getAnneeAcademiqueByDate(Date date) throws IsjException;
}
