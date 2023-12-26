package com.king.Bibliotheque.Repositories;

import com.king.Bibliotheque.Models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
