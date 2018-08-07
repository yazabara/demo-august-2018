package com.yazabara.demoaugust2018.model.web;

import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WebTraining implements Serializable {

    private Integer trainingId;

    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    private Date date;

    @EqualsAndHashCode.Exclude
    private List<WebExercise> exercises = new ArrayList<>();
}
