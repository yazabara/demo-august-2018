package com.yazabara.demoaugust2018.config.security;

import com.yazabara.demoaugust2018.model.db.DbUser;
import com.yazabara.demoaugust2018.repo.UserRepository;
import com.yazabara.demoaugust2018.service.security.WebUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
@Slf4j
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final WebUserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    @Autowired
    public WebSecurityConfig(WebUserDetailsService userDetailsService, PasswordEncoder encoder) {
        this.userDetailsService = userDetailsService;
        this.encoder = encoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/static/**", "/login**", "/error**").permitAll()
                .antMatchers("/v1/api/**").authenticated()
                .and()
                .httpBasic();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return userDetailsService;
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder);
        return authProvider;
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
        return map -> {
            String login = (String) map.get("login");
            Optional<DbUser> byUsername = userRepository.findByUsername(login);
            DbUser user;
            if (!byUsername.isPresent()) {
                log.info("No user found, generating profile for {}", login);
                //TODO generate temp password
                user = userRepository.save(new DbUser().withUsername(login).withPassword(login));
            } else {
                log.info("User found: {}", byUsername.get());
                user = byUsername.get();
            }
            return userDetailsService.createUserDetails(user);
        };
    }
}
