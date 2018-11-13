package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.exceptions.UserNotFoundException;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class UserAccountServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserAccountService userAccountService;

    @Test(expected = UserNotFoundException.class)
    public void getUserDataByIdShouldThrowException() {
        when(userRepository.findById(1)).thenThrow(new UserNotFoundException(1));
        userAccountService.getUserDataById(1);
    }

    @Test
    public void getUserDataByIdShouldWorksCorrect() {
        DbUser dbUser = new DbUser().withId(1);
        when(userRepository.findById(dbUser.getUserId()))
                .then((Answer<Optional<DbUser>>) invocationOnMock -> Optional.of(dbUser));

        assertThat(userAccountService.getUserDataById(dbUser.getUserId()), is(dbUser));
    }

    @Test
    public void addUserShouldAddCorrect() {
        DbUser dbUser = new DbUser()
                .withUsername("test1")
                .withPassword("password")
                .withId(1)
                .withRole(SecurityRoles.USER);

        when(userRepository.save(dbUser))
                .then((Answer<DbUser>) invocationOnMock -> dbUser);

        when(userRepository.findById(dbUser.getUserId()))
                .then((Answer<Optional<DbUser>>) invocationOnMock -> Optional.of(dbUser));

        userAccountService.save(dbUser);

        assertThat(userAccountService.getUserDataById(dbUser.getUserId()), is(dbUser));
    }
}