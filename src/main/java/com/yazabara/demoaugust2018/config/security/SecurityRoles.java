package com.yazabara.demoaugust2018.config.security;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecurityRoles {

    public final static String USER = "USER";
    public final static String ADMIN = "ADMIN";

    public static List<String> roles() {
        return Stream.of(USER, ADMIN)
                .distinct()
                .collect(Collectors.toList());
    }
}
