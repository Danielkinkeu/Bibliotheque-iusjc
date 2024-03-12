package org.isj.ing.gestionuser.repository;

import org.isj.ing.gestionuser.model.UserRole;
import org.isj.ing.gestionuser.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId>, JpaSpecificationExecutor<UserRole> {
}