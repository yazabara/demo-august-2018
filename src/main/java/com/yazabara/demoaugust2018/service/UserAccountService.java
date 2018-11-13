package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.exceptions.UserNotFoundException;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class UserAccountService {

    private final UserRepository userRepository;

    public UserAccountService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    private void initAdmin() {
        userRepository.save(new DbUser().withUsername("admin").withPassword("admin").withRole(SecurityRoles.ADMIN));
    }

    public DbUser save(DbUser user) {
        //double check (db integrity)
        user.getTrainings().forEach(training -> {
            training.setOwner(user);
            training.getExercises().forEach(dbExercise -> {
                dbExercise.setTraining(training);
                dbExercise.getWorkoutSets().forEach(dbWorkoutSet -> dbWorkoutSet.setExercise(dbExercise));
            });
        });
        DbUser save = userRepository.save(user);
        log.info("New user {} added into application storage", save);
        return save;
    }

    public DbUser getUserDataById(Integer id) {
        Optional<DbUser> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            log.error("No user found for id = {}", id);
            throw new UserNotFoundException(id);
        }
        return byId.get();
    }

    public Collection<DbUser> list() {
        HashSet<DbUser> collect = StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .collect(Collectors.toCollection(HashSet::new));
        log.info("User list retrieved = {}", collect.size());
        return collect;
    }
}
