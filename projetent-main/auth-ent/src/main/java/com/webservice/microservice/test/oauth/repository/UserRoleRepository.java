package com.webservice.microservice.test.oauth.repository;

import com.webservice.microservice.test.oauth.model.User;
import com.webservice.microservice.test.oauth.model.UserRole;
import com.webservice.microservice.test.oauth.model.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId>, JpaSpecificationExecutor<UserRole> {
    public List<UserRole> findByCodeuserid(User user);
}