package com.yazabara.demoaugust2018.config;

import com.yazabara.demoaugust2018.service.WorkoutService;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("user-api-controller1")
@Configuration
public class UserApiControllerV1TestConfig {

    @Bean("workoutServiceMock")
    @Primary
    public WorkoutService workoutService() {
        return Mockito.mock(WorkoutService.class);
    }
}
