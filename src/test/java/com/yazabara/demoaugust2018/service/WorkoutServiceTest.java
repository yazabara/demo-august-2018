package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.model.db.DbExercise;
import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.model.db.DbWorkoutSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Random;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class WorkoutServiceTest {

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private WorkoutService workoutService;


    @Test
    public void addTrainingShouldWorkCorrect() {
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
                        .withWorkoutSets(Arrays.asList(
                                new DbWorkoutSet()
                                        .withDuration(10)
                                        .withWeight(100)
                                        .withRepetitions(6),
                                new DbWorkoutSet()
                                        .withDuration(10)
                                        .withWeight(120)
                                        .withRepetitions(7)
                        ))
        ));
        DbUser user = new DbUser()
                .withId(new Random().nextInt())
                .withUsername("user")
                .withPassword("user")
                .withTrainings(Collections.singletonList(training));

        when(userAccountService.getUserDataById(user.getUserId()))
                .then((Answer<DbUser>) invocationOnMock -> user);

        workoutService.addTrainings(user.getUserId(), training);
    }
}