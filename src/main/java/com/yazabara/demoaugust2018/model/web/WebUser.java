package com.yazabara.demoaugust2018.model.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class WebUser {

    private Integer id;

    @EqualsAndHashCode.Exclude
    private String name;

    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private String password;
}
