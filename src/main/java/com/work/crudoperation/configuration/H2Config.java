package com.work.crudoperation.configuration;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2Config {
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2Server() throws Exception {
        return Server.createWebServer(
                "-web",
                "-webAllowOthers",
                "-webPort",
                "8082"
        );
    }
}
