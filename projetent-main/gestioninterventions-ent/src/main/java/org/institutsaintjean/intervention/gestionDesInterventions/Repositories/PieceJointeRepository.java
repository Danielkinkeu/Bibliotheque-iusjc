package org.institutsaintjean.intervention.gestionDesInterventions.Repositories;

import org.institutsaintjean.intervention.gestionDesInterventions.Entities.PieceJointe;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PieceJointeRepository extends JpaRepository<PieceJointe,Long> {
    PieceJointe findByIdPieceJointe(Long idPieceJointe);
}
