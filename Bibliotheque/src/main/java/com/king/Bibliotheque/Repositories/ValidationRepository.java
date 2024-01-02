package com.king.Bibliotheque.Repositories;

import com.king.Bibliotheque.Models.Validation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidationRepository extends CrudRepository<Validation, Integer> {
    Optional<Validation> findByCode(String code);
}
