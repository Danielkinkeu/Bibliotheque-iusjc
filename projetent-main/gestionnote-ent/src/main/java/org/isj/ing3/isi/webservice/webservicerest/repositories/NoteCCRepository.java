package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Candidat;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.NoteCC;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.TypeNoteCC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteCCRepository extends JpaRepository<NoteCC, Long> {
    @Query(value = "SELECT * FROM NoteCC n WHERE n.candidat=:candidats AND n.typeNoteCC=:typeNoteCCs",nativeQuery = true)
    public Optional <NoteCC> retrouverNoteCC(@Param("candidats")Candidat candidat,@Param("typeNoteCCs") TypeNoteCC typeNoteCC);


}