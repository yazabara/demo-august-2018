package com.yazabara.demoaugust2018.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "training_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private DbTraining training;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "exercise", orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private List<DbWorkoutSet> workoutSets = new ArrayList<>();

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

    public DbExercise withWorkoutSets(Collection<DbWorkoutSet> workoutSets) {
        if (!CollectionUtils.isEmpty(workoutSets)) {
            this.workoutSets.addAll(workoutSets);
        }
        return this;
    }
}
