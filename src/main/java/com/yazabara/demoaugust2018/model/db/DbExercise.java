package com.yazabara.demoaugust2018.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.*;

@Entity
@Table(name = "exercise")
@Data
@ToString(exclude = "training")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DbExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer exerciseId;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "training_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private DbTraining training;

    public DbExercise withId(Integer id) {
        if (id != null) {
            setExerciseId(id);
        }
        return this;
    }

    public DbExercise withName(String name) {
        if (StringUtils.isNotBlank(name)) {
            setName(name);
        }
        return this;
    }

    public DbExercise withDescription(String description) {
        if (StringUtils.isNotBlank(name)) {
            setDescription(description);
        }
        return this;
    }

    public DbExercise withTraining(DbTraining training) {
        if (training != null) {
            setTraining(training);
        }
        return this;
    }
}
