package org.institutsaintjean.intervention.gestionDesInterventions.Repositories;

import org.institutsaintjean.intervention.gestionDesInterventions.Entities.Role;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findRoleByIdRole(Long idRole);
}
