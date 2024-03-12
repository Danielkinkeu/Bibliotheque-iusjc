package org.isj.ing.gestionuser.repository;

import org.isj.ing.gestionuser.model.RoleDroit;
import org.isj.ing.gestionuser.model.RoleDroitId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDroitRepository extends JpaRepository<RoleDroit, RoleDroitId>, JpaSpecificationExecutor<RoleDroit> {
}