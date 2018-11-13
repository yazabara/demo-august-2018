package com.yazabara.demoaugust2018.controller.api.v1;

import com.yazabara.demoaugust2018.service.WorkoutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.doThrow;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class TrainingRestApiV1Test {

    @Mock
    private WorkoutService workoutService;

    @InjectMocks
    private TrainingRestApiV1 restApiV1;

    @Test(expected = IllegalArgumentException.class)
    public void test() {
        doThrow(IllegalArgumentException.class)
                .when(workoutService)
                .addTrainings(null, null);

        restApiV1.addTraining(null, null);
    }
}