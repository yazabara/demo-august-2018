package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.exceptions.UserNotFoundException;
import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class WorkoutService {

    private final UserRepository userRepository;

    @Autowired
    public WorkoutService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public DbUser addUser(DbUser user) {
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
            log.error("No user found for id = {}");
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

    public DbUser addTrainings(Integer userId, DbTraining... trainings) {
        DbUser user = getUserDataById(userId);
        List<DbTraining> dbTrainings = Arrays.asList(trainings);
        if (CollectionUtils.isEmpty(dbTrainings)) {
            log.warn("Unable to add trainings added for user {}, because new training list is empty", user);
            return user;
        }
        user.withTrainings(dbTrainings.stream().map(training -> training.withOwner(user)).collect(Collectors.toList()));
        DbUser save = userRepository.save(user);
        log.info("Trainings added for user {}", save);
        return save;
    }
}
