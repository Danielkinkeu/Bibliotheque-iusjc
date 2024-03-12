package org.institutsaintjean.intervention.gestionDesInterventions.Repositories;


import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Categorie;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.SousIntervention;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SousInterventionRepository extends JpaRepository<SousIntervention, Long> {

    List<SousIntervention> findByDepartementIdDepartement(Long idDepartement);
    List<SousIntervention> findByCategorieIdCategorie(Long idcategorie);

}
