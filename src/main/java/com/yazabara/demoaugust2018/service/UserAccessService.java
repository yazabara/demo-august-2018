package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.config.SecurityRoles;
import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.model.web.WebTraining;
import com.yazabara.demoaugust2018.model.web.WebUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class UserAccessService {

    private final UserRepository userRepository;

    @PostConstruct
    public void testUsers() {
        //TODO TEST users
        DbUser user = new DbUser().withUsername("user").withPassword("user");
        DbUser admin = new DbUser().withUsername("admin").withPassword("admin").withRole(SecurityRoles.ADMIN);
        DbUser save = userRepository.save(user);
        DbUser save1 = userRepository.save(admin);
        // test trainings
        DbTraining training = new DbTraining();
        training.setDate(new Date());
        training.setName("First training");
        training.setOwner(save);
        addTrainings(Collections.singletonList(training), save.getUserId());
        training.setOwner(save1);
        addTrainings(Collections.singletonList(training), save1.getUserId());
    }

    @Autowired
    public UserAccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public WebUser addUser(WebUser webUser) {
        DbUser save = userRepository.save(new DbUser()
                .withUsername(webUser.getName())
                .withPassword(webUser.getPassword())
        );

        webUser.setId(save.getUserId());
        webUser.setRole(save.getRole());

        log.info("New user {} added into application storage", webUser);
        return webUser;
    }

    public Collection<WebUser> list() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(dbUser -> {
                    List<WebTraining> trainings = dbUser
                            .getTrainings()
                            .stream()
                            .map(dbTraining -> new WebTraining(dbTraining.getTrainingId(), dbTraining.getName(), dbTraining.getDate()))
                            .collect(Collectors.toList());
                    return new WebUser(dbUser.getUserId(), dbUser.getUsername(), dbUser.getPassword(), dbUser.getRole(), trainings);
                })
                .collect(Collectors.toCollection(HashSet::new));
    }

    @Transactional
    public void addTrainings(List<DbTraining> trainings, Integer userId) {
        Optional<DbUser> byId = userRepository.findById(userId);
        if (!byId.isPresent()) {
            log.info("No user found for id = {}");
            return;
        }
        DbUser save = userRepository.save(byId.get().withTrainings(trainings));
        log.info("Trainings added for user {}", save);
    }
}
