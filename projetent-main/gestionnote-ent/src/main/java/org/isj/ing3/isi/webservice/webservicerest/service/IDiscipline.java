package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Discipline;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Etudiant;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface IDiscipline {

    int saveDiscipline (Discipline discipline) throws IsjException;
    List<Discipline> listDisciplines();
    int deleteDiscipline(Long code) throws IsjException;
    Discipline getDisciplineByCode(Long code) throws IsjException;
    int updateDiscipline (Discipline discipline) throws IsjException;
    Discipline searchDisciplineByEtudiantOrSemestreOrDate( Etudiant etudiant,  Semestre semestre,  Date dateRetard) throws IsjException;
}
