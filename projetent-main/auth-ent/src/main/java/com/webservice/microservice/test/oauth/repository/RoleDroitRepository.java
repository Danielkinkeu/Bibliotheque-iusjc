package com.webservice.microservice.test.oauth.repository;

import com.webservice.microservice.test.oauth.model.Droit;
import com.webservice.microservice.test.oauth.model.Role;
import com.webservice.microservice.test.oauth.model.RoleDroit;
import com.webservice.microservice.test.oauth.model.RoleDroitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleDroitRepository extends JpaRepository<RoleDroit, RoleDroitId>, JpaSpecificationExecutor<RoleDroit> {
    List<RoleDroit> findByCoderoleid(Role role);


}