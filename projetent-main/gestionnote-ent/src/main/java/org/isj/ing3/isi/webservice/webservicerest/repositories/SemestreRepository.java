package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.AnneeAcademique;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {
    @Query(value = "SELECT * FROM Semestre s WHERE s.libelle=:libelle AND s.annee_academique=:anneeacademique.code",nativeQuery = true)
    public Optional <Semestre> retrouverSemestre(@Param("libelle")String libelle, @Param("anneeacademique") Long annee_academique);

}