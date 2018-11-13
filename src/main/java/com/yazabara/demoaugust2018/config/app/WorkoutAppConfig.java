package com.yazabara.demoaugust2018.config.app;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class WorkoutAppConfig {

    private String version;
}
