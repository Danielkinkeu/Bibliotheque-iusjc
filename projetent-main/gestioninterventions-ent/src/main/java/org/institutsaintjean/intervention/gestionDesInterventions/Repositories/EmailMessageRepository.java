package org.institutsaintjean.intervention.gestionDesInterventions.Repositories;

import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Departement;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.EmailMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailMessageRepository extends JpaRepository<EmailMessage, Long> {
}
