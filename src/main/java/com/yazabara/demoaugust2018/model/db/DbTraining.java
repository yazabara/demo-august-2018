package com.yazabara.demoaugust2018.model.db;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "training")
@Data
@ToString(exclude = "owner")
public class DbTraining {

    @Id
//    @Column(insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer trainingId;

    @Column(nullable = false)
    private String name;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", nullable = false)
    private DbUser owner;
}
