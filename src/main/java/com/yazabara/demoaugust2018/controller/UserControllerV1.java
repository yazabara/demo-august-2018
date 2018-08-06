package com.yazabara.demoaugust2018.controller;

import com.yazabara.demoaugust2018.config.SecurityRoles;
import com.yazabara.demoaugust2018.model.web.WebUser;
import com.yazabara.demoaugust2018.service.UserAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.Collection;

@RestController
@RequestMapping(value = "/v1/user")
public class UserControllerV1 {

    private final UserAccessService userAccessService;

    @Autowired
    public UserControllerV1(UserAccessService userAccessService) {
        this.userAccessService = userAccessService;
    }

    @PostMapping("/add")
    @RolesAllowed(SecurityRoles.ADMIN)
    public WebUser start(@RequestBody WebUser user) {
        return userAccessService.addUser(user);
    }

    @GetMapping("/list")
    @RolesAllowed(SecurityRoles.USER)
    public Collection<WebUser> list() {
        return userAccessService.list();
    }
}
