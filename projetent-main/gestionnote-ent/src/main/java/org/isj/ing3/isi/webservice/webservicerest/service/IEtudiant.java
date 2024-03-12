package org.isj.ing3.isi.webservice.webservicerest.service;


import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Etudiant;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;

import java.util.List;

public interface IEtudiant {
    int saveEtudiant(Etudiant etudiantDto) throws IsjException;
    int deleteEtudiant(Long code) throws  IsjException;
    List<Etudiant> listEtudiants();
    int updateEtudiant(Etudiant etudiant ) throws IsjException;
    Etudiant searchEtudiantByCodeEtudiant(Long codeEtudiant ) throws IsjException;
    Etudiant getEtudiantByCode(Long code) throws IsjException;
    Etudiant getStudentByMatricule(String matricule) throws IsjException;
    void generateAttestationReusite() throws Exception;
    int nbEtudiant();


}
