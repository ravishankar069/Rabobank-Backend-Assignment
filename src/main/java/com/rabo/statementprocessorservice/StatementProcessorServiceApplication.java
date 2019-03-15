package com.rabo.statementprocessorservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class StatementProcessorServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StatementProcessorServiceApplication.class, args);
    }
}
