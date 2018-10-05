package com.example.instagramapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InstagramApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(InstagramApiApplication.class, args);
    }
}
