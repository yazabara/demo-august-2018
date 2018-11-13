package com.yazabara.demoaugust2018.controller;

import com.yazabara.demoaugust2018.config.app.WorkoutAppConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final WorkoutAppConfig config;

    public IndexController(WorkoutAppConfig config) {
        this.config = config;
    }

    @GetMapping("/version")
    public String version() {
        return config.getVersion();
    }
}
