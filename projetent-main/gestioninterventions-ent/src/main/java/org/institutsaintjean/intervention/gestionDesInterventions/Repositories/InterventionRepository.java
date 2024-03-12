package org.institutsaintjean.intervention.gestionDesInterventions.Repositories;


import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Departement;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Etudiant;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Intervention;
import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Personnel;
import org.institutsaintjean.intervention.gestionDesInterventions.Enumerations.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterventionRepository extends JpaRepository<Intervention, Long> {


    List<Intervention> findByDepartementAndStatut(Departement departement, Statut statut);

    List<Intervention> findByEtudiant(Etudiant etudiant);
    List<Intervention> findByDepartement(Departement departement);
    List<Intervention> findByPersonnelAndStatut(Personnel personnel, Statut statut);
    List<Intervention> findByEtudiantAndStatutOrderByDateCreationInterAsc(Etudiant etudiant, Statut statut);







}
