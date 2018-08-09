package com.yazabara.demoaugust2018.controller.api;

import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

@RestController
@RequestMapping(value = "/v1/api/user")
public class UserApiControllerV1 {

    private final WorkoutService workoutService;

    @Autowired
    public UserApiControllerV1(WorkoutService workoutService) {
        this.workoutService = workoutService;
    }

    @PostMapping("/add")
    @RolesAllowed(SecurityRoles.ADMIN)
    public DbUser add(@RequestBody DbUser user) {
        return workoutService.addUser(user);
    }

    @PostMapping("/add-training/{userId}")
    @RolesAllowed(SecurityRoles.USER)
    public DbUser addTraining(@PathVariable("userId") Integer userId, @RequestBody DbTraining training) {
        return workoutService.addTrainings(userId, training);
    }

    @GetMapping("/list")
    @RolesAllowed(SecurityRoles.USER)
    public Collection<DbUser> list() {
        return workoutService.list();
    }
}
