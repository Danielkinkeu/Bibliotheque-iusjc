package com.king.Bibliotheque.Repositories;

import com.king.Bibliotheque.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByTitle(String title);
}

