package com.yazabara.demoaugust2018.controller;

import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.model.web.WebUser;
import com.yazabara.demoaugust2018.service.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/v1/user")
public class UserControllerV1 {

    private final UserAccessService userAccessService;

    @Autowired
    public UserControllerV1(UserAccessService userAccessService) {
        this.userAccessService = userAccessService;
    }

    @PostMapping("/add")
    public DbUser start(@RequestBody WebUser user) {
        return userAccessService
                .addUser(new DbUser()
                        .withName(user.getName())
                        .withPassword(user.getPassword())
                );
    }

    @GetMapping("/list")
    public Iterable<DbUser> list() {
        return userAccessService.list();
    }
}
