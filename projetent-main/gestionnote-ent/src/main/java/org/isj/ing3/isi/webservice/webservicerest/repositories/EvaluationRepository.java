package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {


    /*************retrouver typeEvaluation***********/

    @Query(value = "SELECT type_evaluation\n" +
            "FROM type_evaluation,enseignement,ue,semestre,annee_academique\n" +
            "WHERE type_evaluation.enseignement=enseignement.code\n" +
            "and enseignement.ue=ue.code\n" +
            "and enseignement.semestre=semestre.code\n" +
            "and semestre.annee_academique=annee_academique.code\n" +
            "and extract(year from annee_academique.date_debut)= an "+
            " and type_evaluation.libelle =TypeEv \n" +
            " and ue.code_ue =codeUe",nativeQuery = true)
    public Optional<Evaluation> retrouverTypeEvaluation(@Param("TypeEv") String TypeEv, @Param("codeUe")String codeUe, @Param("an")long an);

    /*****************retrouver evaluation********************************/

    @Query(value = "SELECT eva from Evaluation eva where eva.typeEvaluation=:typeEvaluation",nativeQuery = true)
    public Optional<Evaluation>retrouverEvaluation(@Param("typeEvaluation") TypeEvaluation typeEvaluation);
}