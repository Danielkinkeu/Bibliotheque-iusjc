package org.institutsaintjean.intervention.gestionDesInterventions.Repositories;

import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository <Email, Long> {

}
