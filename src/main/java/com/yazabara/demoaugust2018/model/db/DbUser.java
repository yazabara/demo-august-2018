package com.yazabara.demoaugust2018.model.db;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Data
@ToString
public class DbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String password;

    public DbUser withName(String name) {
        this.name = name;
        return this;
    }

    public DbUser withPassword(String password) {
        this.password = password;
        return this;
    }
}
