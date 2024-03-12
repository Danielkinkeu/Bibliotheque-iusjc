package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Filiere;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Niveau;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NiveauRepository extends JpaRepository<Niveau, Long> {

    @Query(value = "SELECT * FROM Niveau ni WHERE ni.numero=:niveau", nativeQuery = true)
    public Optional <Niveau> retrouverNiveau(@Param("niveau") long niveau);
}