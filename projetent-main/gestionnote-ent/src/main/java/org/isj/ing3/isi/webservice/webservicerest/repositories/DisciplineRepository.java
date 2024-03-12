package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    @Query(value="SELECT * FROM Discipline d WHERE d.etudiant=:etudiant AND d.semestre=:semestre and d.dateRetard=:dateRetard", nativeQuery = true)
    public Optional <Discipline> retrouverDiscipline(@Param("etudiant")Etudiant etudiant, @Param("semestre")Semestre semestre, @Param("dateRetard") Date dateRetard);

}