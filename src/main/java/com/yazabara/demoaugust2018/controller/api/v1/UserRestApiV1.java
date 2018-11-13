package com.yazabara.demoaugust2018.controller.api.v1;

import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.service.UserAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.Collection;

@RestController
@RequestMapping(value = "/v1/api/user")
public class UserRestApiV1 {

    private final UserAccountService userAccountService;

    public UserRestApiV1(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @PostMapping("/add")
    @RolesAllowed(SecurityRoles.ADMIN)
    public DbUser add(@RequestBody DbUser user) {
        return userAccountService.save(user);
    }

    @GetMapping("/list")
    @RolesAllowed({SecurityRoles.USER, SecurityRoles.ADMIN})
    public Collection<DbUser> list() {
        return userAccountService.list();
    }

    //TODO remove
    @GetMapping("/principal")
    public String principal(Principal principal) {
        return principal.getName();
    }
}
