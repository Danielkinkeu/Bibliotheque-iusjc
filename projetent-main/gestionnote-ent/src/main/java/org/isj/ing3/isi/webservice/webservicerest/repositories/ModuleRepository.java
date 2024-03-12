package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Evaluation;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Module;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module, Long> {

    @Query(value = "SELECT * FROM Module mo WHERE mo.code_module=:codeModule", nativeQuery = true)
    public Optional <Module> retrouverModule(@Param("codeModule") String codeModule);
}