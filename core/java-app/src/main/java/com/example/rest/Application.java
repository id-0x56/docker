package com.example.rest;

import org.flywaydb.core.Flyway;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =  SpringApplication.run(Application.class, args);

        String url = configurableApplicationContext.getEnvironment().getProperty("spring.datasource.url");
        String username = configurableApplicationContext.getEnvironment().getProperty("spring.datasource.username");
        String password = configurableApplicationContext.getEnvironment().getProperty("spring.datasource.password");

        // Flyway flyway = Flyway.configure().dataSource(url, username, password).locations("database/migrations").load();
        // flyway.migrate();
    }
}
