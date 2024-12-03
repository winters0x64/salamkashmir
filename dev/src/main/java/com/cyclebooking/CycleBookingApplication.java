package com.cyclebooking;

import com.cyclebooking.repository.InMemoryStorage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CycleBookingApplication {
    public static void main(String[] args) {
        SpringApplication.run(CycleBookingApplication.class, args);
    }
    
    @Bean
    CommandLineRunner init(InMemoryStorage storage) {
        return args -> {
            storage.initializeData();
        };
    }
} 