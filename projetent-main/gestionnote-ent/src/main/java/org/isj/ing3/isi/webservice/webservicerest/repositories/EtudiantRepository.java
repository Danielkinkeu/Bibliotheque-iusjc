package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Optional<Etudiant> findByMatricule(String matricule);

    /*retrouver un etudiant à partir de son code*/
    @Query(value = "SELECT e.matricule FROM etudiant e WHERE e.code=:codeEtudiant",nativeQuery = true)
    public Optional<Etudiant> retrouverEtudiant(@Param("codeEtudiant") Long codeEtudiant);

    @Query(value = "SELECT COUNT(DISTINCT matricule) FROM etudiant",nativeQuery = true)
    public int getNbStudent();
}