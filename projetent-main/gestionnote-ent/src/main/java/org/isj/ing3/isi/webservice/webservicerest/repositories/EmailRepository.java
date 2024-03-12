package org.isj.ing3.isi.webservice.webservicerest.repositories;

import org.isj.ing3.isi.webservice.webservicerest.model.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email, Long> {
}