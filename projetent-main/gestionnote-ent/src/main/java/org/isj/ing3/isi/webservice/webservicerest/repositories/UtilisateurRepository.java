package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Utilisateur;
import org.isj.ing3.isi.webservice.webservicerest.utils.enumaration.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    @Query(value = "SELECT u FROM Utilisateur u WHERE u.login=:login AND u.motDePasse=:pw AND u.statut=:statut")
    Optional<Utilisateur> getUserByLoginAndPw(@Param("login") String login, @Param("pw") String pw,  @Param("statut") Statut statut);

    Utilisateur getUtilisateurByEmail(@Param("email") String email);

}