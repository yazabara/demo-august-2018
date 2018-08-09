package com.yazabara.demoaugust2018.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "training")
@Data
@ToString(exclude = "owner")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
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
    @JsonIgnore
    private DbUser owner;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "training", orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private List<DbExercise> exercises = new ArrayList<>();

    public DbTraining withOwner(DbUser user) {
        setOwner(user);
        return this;
    }
}
