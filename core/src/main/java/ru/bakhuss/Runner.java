package ru.bakhuss;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class Runner {
    private static ApplicationContext context;

    public Runner(ApplicationContext context) {
        Runner.context = context;
    }

    public static void main(String[] args) {
        SpringApplication.run(Runner.class, args);
        log.info("context is loaded");
    }
}
