package com.yazabara.demoaugust2018.model.web;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yazabara.demoaugust2018.service.json.PasswordDeserializer;
import com.yazabara.demoaugust2018.service.json.PasswordSerializer;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WebUser implements Serializable {

    private Integer id;

    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    @JsonSerialize(using = PasswordSerializer.class)
    @JsonDeserialize(using = PasswordDeserializer.class)
    private String password;

    @EqualsAndHashCode.Exclude
    private String role;

    @EqualsAndHashCode.Exclude
    private List<WebTraining> trainings = new ArrayList<>();
}
