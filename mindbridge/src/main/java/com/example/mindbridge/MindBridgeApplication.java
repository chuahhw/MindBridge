package com.example.mindbridge;

import com.example.mindbridge.model.Counselor;
import com.example.mindbridge.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MindBridgeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MindBridgeApplication.class, args);
    }
}
