package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Session;
import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {


    @Query(value = "SELECT * session WHERE utilisateur=:utilisateur ODER BY date_connection DESC LIMIT 1", nativeQuery = true)
    Optional<Session> getLastSessionUser(@Param("utilisateur") Utilisateur utilisateur);

}