package org.institutsaintjean.intervention.gestionDesInterventions.Repositories;


import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    Etudiant findByEmailEtudiant(String email);
    Etudiant findByCode(long code);

    Optional<Etudiant> findByLogin(String login);


}
