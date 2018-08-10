package com.yazabara.demoaugust2018.controller.api;

import com.yazabara.demoaugust2018.DemoAugust2018Application;
import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.service.WorkoutService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@ActiveProfiles("user-api-controller1")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoAugust2018Application.class)
public class UserApiControllerV1Test {

    @Autowired
    private UserApiControllerV1 userApiControllerV1;

    @Autowired
    @Qualifier("workoutServiceMock")
    private WorkoutService workoutService;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = SecurityRoles.ADMIN)
    public void listShouldReturnEmptyList() {
        Mockito.when(workoutService.list()).then((Answer<Collection<DbUser>>) invocationOnMock -> Collections.emptyList());

        assertThat(userApiControllerV1.list(), is(Collections.emptyList()));
    }
}