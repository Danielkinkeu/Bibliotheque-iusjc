package org.institutsaintjean.intervention.gestionDesInterventions.Repositories;


import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Personnel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonnelRepository extends JpaRepository<Personnel, Long> {


    Personnel findByIdPersonnel(Long idPersonnel);

    Personnel findByEmailPersonnel( String email) ;


    List<Personnel> findByDepartementIdDepartement(Long departementId);

    Optional<Personnel> findByLogin(String login);

}
