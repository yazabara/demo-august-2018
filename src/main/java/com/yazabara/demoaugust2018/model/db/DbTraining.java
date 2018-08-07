package com.yazabara.demoaugust2018.model.db;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "training")
@Data
@ToString(exclude = "owner")
@EqualsAndHashCode
public class DbTraining {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainingId;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private String name;

    @EqualsAndHashCode.Exclude
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @EqualsAndHashCode.Exclude
    private DbUser owner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "training", orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private List<DbExercise> workoutSets = new ArrayList<>();
}
