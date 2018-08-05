package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccessService {

    private final UserRepository userRepository;

    @Autowired
    public UserAccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DbUser addUser(DbUser dbUser) {
        return userRepository.save(dbUser);
    }

    public Iterable<DbUser> list() {
        return userRepository.findAll();
    }
}
