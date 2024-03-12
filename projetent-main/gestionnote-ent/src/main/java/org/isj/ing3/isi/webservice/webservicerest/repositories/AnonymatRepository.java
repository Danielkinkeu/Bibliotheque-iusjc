package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Anonymat;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnonymatRepository extends JpaRepository<Anonymat, Long> {

    /******fonction qui retourne l'anonymat possédant le numero d'anonymat passée en parametre**************/

    @Query(value = "SELECT a FROM Anonymat a WHERE a.numeroAnonymat=:num_anonymat",nativeQuery = true)
    public Optional< Anonymat >retrouverAnonymatNote(@Param("num_anonymat") String num_anonymat);

    /*** fonction qui retourne la liste des Anonymat pour une evaluation precise*********/

    @Query(value = "SELECT ano FROM Anonymat ano WHERE ano.evaluation=:evaluation",nativeQuery = true)
    public Optional< List<Anonymat> >retrouverListeAnonymat(@Param("evaluation") Evaluation evaluation);

    /*****fonction qui retourne la liste des anonymat à partir du codeEstInscrit et le codeEvaluation******/
/*
    @Query(value = "SELECT ano FROM Anonymat ano WHERE ano.evaluation=:evaluation and ano.estInscrit=:est_inscrit",nativeQuery = true)
    public Anonymat retrouverAnonymat(@Param("evaluation") Evaluation evaluation,@Param("estInscrit") EstInscrit estInscrit);
*/
}