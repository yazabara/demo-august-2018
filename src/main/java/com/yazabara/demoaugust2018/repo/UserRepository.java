package com.yazabara.demoaugust2018.repo;

import com.yazabara.demoaugust2018.model.db.DbUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<DbUser, Integer> {
}
