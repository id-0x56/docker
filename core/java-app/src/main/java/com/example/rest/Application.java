package com.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.flywaydb.core.Flyway;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        Flyway flyway = Flyway.configure().dataSource("jdbc:mysql://mysql:3306/database", "username", "password").locations("database/migrations").load();
        flyway.migrate();
    }
}
