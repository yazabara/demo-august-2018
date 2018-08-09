package com.yazabara.demoaugust2018.model.db;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yazabara.demoaugust2018.config.security.SecurityRoles;
import com.yazabara.demoaugust2018.service.json.PasswordDeserializer;
import com.yazabara.demoaugust2018.service.json.PasswordSerializer;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DbUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(nullable = false, unique = true)
    @EqualsAndHashCode.Exclude
    private String username;

    @Column(nullable = false)
    @JsonSerialize(using = PasswordSerializer.class)
    @JsonDeserialize(using = PasswordDeserializer.class)
    @EqualsAndHashCode.Exclude
    private String password;

    @Column(nullable = false)
    @EqualsAndHashCode.Exclude
    private String role = SecurityRoles.USER;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "owner", orphanRemoval = true)
    @EqualsAndHashCode.Exclude
    private List<DbTraining> trainings = new ArrayList<>();

    public DbUser withUsername(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.username = name;
        }
        return this;
    }

    public DbUser withId(Integer id) {
        if (id != null) {
            this.userId = id;
        }
        return this;
    }

    public DbUser withPassword(String password) {
        if (StringUtils.isNotBlank(password)) {
            this.password = password;
        }
        return this;
    }

    public DbUser withRole(String role) {
        if (SecurityRoles.roles().contains(role)) {
            this.role = role;
        }
        return this;
    }

    public DbUser withTrainings(Collection<DbTraining> trainings) {
        if (!CollectionUtils.isEmpty(trainings)) {
            this.trainings.addAll(trainings);
        }
        return this;
    }
}
