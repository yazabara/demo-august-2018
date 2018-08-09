package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.config.app.WorkoutAppConfig;
import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.model.db.DbExercise;
import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

//TODO remove after implementation. Class adds test data into embedded db
@Service
public class TestDataInitializerService {

    private final WorkoutAppConfig appConfig;
    private final WorkoutService workoutService;

    public TestDataInitializerService(WorkoutAppConfig appConfig, WorkoutService workoutService) {
        this.appConfig = appConfig;
        this.workoutService = workoutService;
    }

    @PostConstruct
    public void testUsers() {
        if (!appConfig.getUseTestData()) {
            return;
        }
        addAdmin();
        addUserWithTrainings();
    }

    private void addAdmin() {
        workoutService.addUser(new DbUser().withUsername("admin").withPassword("admin").withRole(SecurityRoles.ADMIN));
    }

    private void addUserWithTrainings() {
        DbTraining training = new DbTraining()
                .withName("First training")
                .withDate(new Date());

        training.setExercises(Arrays.asList(
                new DbExercise()
                        .withName("Jim bleat")
                        .withDescription("Base exercise")
                        .withTraining(training),
                new DbExercise()
                        .withName("Stanovay bleat")
                        .withDescription("Base exercise")
                        .withTraining(training)
        ));
        workoutService.addUser(new DbUser().withUsername("user").withPassword("user").withTrainings(Collections.singletonList(training)));
    }
}
