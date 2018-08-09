package com.yazabara.demoaugust2018.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

/**
 *
 */
@Entity
@Table(name = "workout_set")
@Data
@ToString(exclude = "exercise")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DbWorkoutSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long workoutId;

    /**
     * Wight of current workout set
     */
    private long weight;

    /**
     * Workout repetitions
     */
    private long repetitions;

    /**
     * Workout set duration in seconds
     */
    private long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    private DbExercise exercise;

    public DbWorkoutSet withId(Integer id) {
        if (id != null) {
            setWorkoutId(id);
        }
        return this;
    }

    public DbWorkoutSet withWeight(Integer weight) {
        if (weight != null) {
            setWeight(weight);
        }
        return this;
    }

    public DbWorkoutSet withRepetitions(Integer repetitions) {
        if (repetitions != null) {
            setRepetitions(repetitions);
        }
        return this;
    }

    public DbWorkoutSet withDuration(Integer duration) {
        if (duration != null) {
            setWeight(duration);
        }
        return this;
    }
}
