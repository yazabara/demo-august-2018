package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.DemoAugust2018Application;
import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.exceptions.UserNotFoundException;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ActiveProfiles("workout-service")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoAugust2018Application.class)
public class WorkoutServiceTest {

    @Autowired
    private WorkoutService workoutService;

    @Autowired
    @Qualifier("userRepositoryMock")
    private UserRepository userRepository;

    @Test(expected = UserNotFoundException.class)
    public void getUserDataByIdShouldThrowException() {
        workoutService.getUserDataById(1);
    }

    @Test
    public void getUserDataByIdShouldWorksCorrect() {
        DbUser dbUser = new DbUser().withId(1);
        Mockito.when(userRepository.findById(dbUser.getUserId())).then((Answer<Optional<DbUser>>) invocationOnMock -> Optional.of(dbUser));

        assertThat(workoutService.getUserDataById(dbUser.getUserId()), is(dbUser));
    }

    @Test
    public void addUserShouldAddCorrect() {
        DbUser dbUser = new DbUser()
                .withUsername("test1")
                .withPassword("password")
                .withId(1)
                .withRole(SecurityRoles.USER);

        Mockito.when(userRepository.save(dbUser)).then((Answer<DbUser>) invocationOnMock -> dbUser);
        Mockito.when(userRepository.findById(dbUser.getUserId())).then((Answer<Optional<DbUser>>) invocationOnMock -> Optional.of(dbUser));

        workoutService.addUser(dbUser);

        assertThat(workoutService.getUserDataById(dbUser.getUserId()), is(dbUser));
    }
}