package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.EstInscrit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstInscritRepository extends JpaRepository<EstInscrit, Long> {
    /***** fonction qui retourne la liste des des est_inscrit par rapport a un enseigment specifique passé en parametre***/

    @Query(value = "SELECT est FROM EstInscrit est WHERE est.enseignement=:enseignement GROUP BY est.candidatInscrit",nativeQuery = true)
    public Optional<EstInscrit> retrouverListeEstInscrit(@Param("enseignement") Enseignement enseignement);


/************-******fonction qui retrouve les candidats inscrit *************/

@Query(value = "SELECT est FROM EstInscrit est WHERE est.candidatInscrit=:candidatInscrit AND est.enseignement=:enseignement",nativeQuery = true)
    public Optional<EstInscrit> retrouverEstInscrit(@Param("candidatInscrit") Candidat candidatInscrit, @Param("enseignement") Enseignement enseignement);
}