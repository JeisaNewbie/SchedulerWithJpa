package com.example.schedulerwithjpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SchedulerWithJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerWithJpaApplication.class, args);
    }

}
