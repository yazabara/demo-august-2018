package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class WorkoutService {

    private final UserAccountService userAccountService;

    public WorkoutService(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public DbUser addTrainings(Integer userId, DbTraining... trainings) {
        DbUser user = userAccountService.getUserDataById(userId);
        List<DbTraining> dbTrainings = Arrays.asList(trainings);
        if (CollectionUtils.isEmpty(dbTrainings)) {
            log.warn("Unable to add trainings added for user {}, because new training list is empty", user);
            return user;
        }
        user.withTrainings(dbTrainings);
        DbUser save = userAccountService.save(user);
        log.info("Trainings added for user {}", save);
        return save;
    }
}
