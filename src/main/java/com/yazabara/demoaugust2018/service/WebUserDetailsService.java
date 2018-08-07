package com.yazabara.demoaugust2018.service;

import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class WebUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public WebUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("User  with {} try login", s);
        Optional<DbUser> byName = userRepository.findByUsername(s);
        if (!byName.isPresent()) {
            throw new UsernameNotFoundException(s);
        }
        final User.UserBuilder userBuilder = User.builder().passwordEncoder(encoder::encode);
        DbUser user = byName.get();
        return userBuilder
                .username(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();
    }
}
