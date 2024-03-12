package org.institutsaintjean.intervention.gestionDesInterventions.Repositories;


import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Departement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartementRepository extends JpaRepository<Departement, Long> {

    Departement findDepartementByIdDepartement(Long idDepartement);

}
