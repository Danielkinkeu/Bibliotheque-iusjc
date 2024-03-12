package com.webservice.microservice.test.oauth.repository;

import com.webservice.microservice.test.oauth.model.Droit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DroitRepository extends JpaRepository<Droit, Long>, JpaSpecificationExecutor<Droit> {
}