package com.example.rest;

import org.flywaydb.core.Flyway;
// import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
// import org.springframework.context.annotation.Bean;

// import java.sql.SQLException;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =  SpringApplication.run(Application.class, args);

        String url = configurableApplicationContext.getEnvironment().getProperty("spring.datasource.url");
        String username = configurableApplicationContext.getEnvironment().getProperty("spring.datasource.username");
        String password = configurableApplicationContext.getEnvironment().getProperty("spring.datasource.password");

        Flyway flyway = Flyway.configure().dataSource(url, username, password).locations("database/migrations").load();
        flyway.migrate();
    }

    // @Bean(initMethod = "start", destroyMethod = "stop")
    // public Server h2Server() throws SQLException {
    //     return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092");
    // }
}
