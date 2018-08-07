package com.yazabara.demoaugust2018.repo;

import com.yazabara.demoaugust2018.model.db.DbUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<DbUser, Integer> {

    Optional<DbUser> findByUsername(String name);
}
