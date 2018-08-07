package com.yazabara.demoaugust2018.model.web;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

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
}
