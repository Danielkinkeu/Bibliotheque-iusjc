package com.king.Bibliotheque.Repositories;

import com.king.Bibliotheque.Models.Adherent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdherentRepository extends JpaRepository<Adherent, Integer> {
    Adherent findByPseudo(String pseudo);
}
