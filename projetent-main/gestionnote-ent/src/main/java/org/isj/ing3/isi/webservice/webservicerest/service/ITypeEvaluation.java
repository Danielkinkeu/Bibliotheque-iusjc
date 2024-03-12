package org.isj.ing3.isi.webservice.webservicerest.service;

import org.isj.ing3.isi.webservice.webservicerest.exception.IsjException;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeEvaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;

import java.util.List;

public interface ITypeEvaluation {

   int saveTypeEvaluation(TypeEvaluation typeEvaluation ) throws IsjException;
    List<TypeEvaluation> listTypeEvaluation();
    int deleteTypeEvaluation(Long code) throws IsjException;
    TypeEvaluation getTypeEvaluationByCode(Long code) throws IsjException;
    int updateTypeEvaluation(TypeEvaluation typeEvaluation ) throws IsjException;


}

