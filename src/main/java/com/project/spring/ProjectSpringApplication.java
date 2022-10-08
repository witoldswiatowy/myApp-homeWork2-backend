package com.project.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class ProjectSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectSpringApplication.class, args);
    }

}
