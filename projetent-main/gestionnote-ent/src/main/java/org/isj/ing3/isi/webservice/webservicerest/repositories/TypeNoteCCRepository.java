package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Enseignement;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Specialite;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.Optional;

@Repository
public interface TypeNoteCCRepository extends JpaRepository<TypeNoteCC, Long> {
   @Query(value = "SELECT eva from TypeNoteCC eva where eva.enseignement=:enseignement and eva.numeroCC=:numero_cc",nativeQuery = true)
    public Optional <TypeNoteCC> retrouverTypeNoteCC(@Param("enseignement") Enseignement enseignement, @Param("numero_cc")int numero_cc);

}