package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.model.web.WebUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserAccessService {

    private final UserRepository userRepository;

    @PostConstruct
    public void baseUser() {
        DbUser user = new DbUser().withName("user").withPassword("user");
        userRepository.save(user);
    }

    @Autowired
    public UserAccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public WebUser addUser(WebUser webUser) {
        DbUser save = userRepository.save(new DbUser()
                .withName(webUser.getName())
                .withPassword(webUser.getPassword())
        );

        webUser.setId(save.getId());
        return webUser;
    }

    public Collection<WebUser> list() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(dbUser -> new WebUser(dbUser.getId(), dbUser.getName(), dbUser.getPassword()))
                .collect(Collectors.toCollection(HashSet::new));
    }
}
