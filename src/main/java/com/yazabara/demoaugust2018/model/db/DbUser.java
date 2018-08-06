package com.yazabara.demoaugust2018.model.db;

import com.yazabara.demoaugust2018.config.SecurityRoles;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

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

    @Column(nullable = false)
    private String role = SecurityRoles.USER;

    public DbUser withName(String name) {
        if (StringUtils.isNotBlank(name)) {
            this.name = name;
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
}
