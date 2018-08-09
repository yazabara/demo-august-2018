package com.yazabara.demoaugust2018.config;

import com.yazabara.demoaugust2018.repo.UserRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Profile("test")
@Configuration
public class UserRepositoryTestConfig {

    @Bean
    @Primary
    public UserRepository nameService() {
        return Mockito.mock(UserRepository.class);
    }
}
