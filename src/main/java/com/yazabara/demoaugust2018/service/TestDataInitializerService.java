package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.model.db.DbExercise;
import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Date;

//TODO remove after implementation. Class adds test data into embedded db
@Service
public class TestDataInitializerService {

    private final UserRepository userRepository;
    private final WorkoutService workoutService;

    public TestDataInitializerService(UserRepository userRepository, WorkoutService workoutService) {
        this.userRepository = userRepository;
        this.workoutService = workoutService;
    }

    @PostConstruct
    public void testUsers() {
        DbUser user = new DbUser().withUsername("user").withPassword("user");
        DbUser admin = new DbUser().withUsername("admin").withPassword("admin").withRole(SecurityRoles.ADMIN);
        DbUser save = userRepository.save(user);
        DbUser save1 = userRepository.save(admin);
        // test trainings
        DbTraining training = new DbTraining();
        training.setDate(new Date());
        training.setName("First training");
        DbExercise dbExercise1 = new DbExercise();
        dbExercise1.setDescription("first exercise");
        dbExercise1.setName("Jiiiim bleat");
        dbExercise1.setTraining(training);
        training.getExercises().add(dbExercise1);
        workoutService.addTrainings(save.getUserId(), training);
        workoutService.addTrainings(save1.getUserId(), training);

        DbTraining webTraining = new DbTraining();
        webTraining.setDate(new Date());
        webTraining.setName("Stanovaya bleat");

        DbExercise webExercise = new DbExercise();
        webExercise.setName("podhod1");
        webExercise.setTraining(webTraining);
        webTraining.setExercises(Collections.singletonList(webExercise));

        workoutService.addTrainings(1, webTraining);
    }
}
