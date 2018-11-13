package com.yazabara.demoaugust2018.controller.api.v1;

import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.service.UserAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.Collection;
import java.util.Collections;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.StrictStubs.class)
public class UserRestApiV1Test {

    @Mock
    private UserAccountService userAccountService;

    @InjectMocks
    private UserRestApiV1 userRestApiV1;

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = SecurityRoles.ADMIN)
    public void listShouldReturnEmptyList() {
        when(userAccountService.list())
                .then((Answer<Collection<DbUser>>) invocationOnMock -> Collections.emptyList());

        assertThat(userRestApiV1.list(), is(Collections.emptyList()));
    }
}