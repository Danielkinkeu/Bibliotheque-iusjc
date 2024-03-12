package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Long> {
    @Query(value = "SELECT * FROM Specialite sp WHERE sp.libelle=:specialite and sp.filiere.libelle=:filiere", nativeQuery = true)
    public Optional <Specialite> retrouverSpecialite(@Param("specialite")String specialite, @Param("filiere")String filiere);

}