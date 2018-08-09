package com.yazabara.demoaugust2018.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
}
