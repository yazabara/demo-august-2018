package com.yazabara.demoaugust2018.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super("User with id: " + id + " not found in system.");
    }
}
