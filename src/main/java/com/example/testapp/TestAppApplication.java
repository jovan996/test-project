package com.example.testapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class TestAppApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(TestAppApplication.class, args);
    }
}
