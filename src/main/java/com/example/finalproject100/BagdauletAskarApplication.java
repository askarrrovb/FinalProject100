package com.example.finalproject100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class BagdauletAskarApplication {

    public static void main(String[] args) {
        SpringApplication.run(BagdauletAskarApplication.class, args);
    }
}
