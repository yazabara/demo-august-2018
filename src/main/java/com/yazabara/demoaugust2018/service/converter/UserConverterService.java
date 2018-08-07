package com.yazabara.demoaugust2018.service.converter;

import com.yazabara.demoaugust2018.model.db.DbTraining;
import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.model.web.WebExercise;
import com.yazabara.demoaugust2018.model.web.WebTraining;
import com.yazabara.demoaugust2018.model.web.WebUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserConverterService {

    public WebUser convertToWeb(DbUser user) {
        List<WebTraining> trainings = user
                .getTrainings()
                .stream()
                .map(dbTraining -> new WebTraining(dbTraining.getTrainingId(), dbTraining.getName(), dbTraining.getDate(), convertExercisesToWeb(dbTraining)))
                .collect(Collectors.toList());
        return new WebUser(user.getUserId(), user.getUsername(), user.getPassword(), user.getRole(), trainings);
    }

    private List<WebExercise> convertExercisesToWeb(DbTraining training) {
        return training
                .getWorkoutSets()
                .stream()
                .map(dbExercise -> new WebExercise(dbExercise.getExerciseId(), dbExercise.getName(), dbExercise.getDescription()))
                .collect(Collectors.toList());
    }
}
