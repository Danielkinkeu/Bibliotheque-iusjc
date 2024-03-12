package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FiliereRepository extends JpaRepository<Filiere, Long> {

    @Query(value = "SELECT * FROM filiere fi WHERE fi.libelle=:libelleFiliere", nativeQuery = true)
    public Optional <Filiere> retrouverFiliere(@Param("libelleFiliere") String libelleFiliere);

}