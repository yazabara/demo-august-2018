package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.exceptions.UserNotFoundException;
import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.model.web.WebUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import com.yazabara.demoaugust2018.service.converter.UserConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
public class WorkoutService {

    private final UserRepository userRepository;
    private final UserConverterService converterService;

    @Autowired
    public WorkoutService(UserRepository userRepository, UserConverterService converterService) {
        this.userRepository = userRepository;
        this.converterService = converterService;
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

    public WebUser getUserDataById(Integer id) {
        Optional<DbUser> byId = userRepository.findById(id);
        if (!byId.isPresent()) {
            log.error("No user found for id = {}");
            throw new UserNotFoundException(id);
        }
        return converterService.convertToWeb(byId.get());
    }

    public Collection<WebUser> list() {
        return StreamSupport
                .stream(userRepository.findAll().spliterator(), false)
                .map(converterService::convertToWeb)
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

    @Transactional
    public void addTraining(DbTraining trainings, Integer userId) {
        Optional<DbUser> byId = userRepository.findById(userId);
        if (!byId.isPresent()) {
            log.info("No user found for id = {}");
            return;
        }
        DbUser save = userRepository.save(byId.get().withTrainings(Collections.singleton(trainings)));
        log.info("Trainings added for user {}", save);
    }
}
