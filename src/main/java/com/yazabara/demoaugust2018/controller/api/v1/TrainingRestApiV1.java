package com.yazabara.demoaugust2018.controller.api.v1;

import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.service.WorkoutService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(value = "/v1/api/training")
public class TrainingRestApiV1 {

    private final WorkoutService workoutService;

    public TrainingRestApiV1(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/add/{userId}")
    @RolesAllowed({SecurityRoles.USER, SecurityRoles.ADMIN})
    @PreAuthorize("@userAccountService.getUserDataById(#userId).getUsername()==principal.username")
    public DbUser addTraining(@PathVariable("userId") Integer userId, @RequestBody DbTraining training) {
        return workoutService.addTrainings(userId, training);
    }
}
