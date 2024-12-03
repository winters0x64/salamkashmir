package com.cyclebooking.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class Booking {
    private Long id;
    private User user;
    private Cycle cycle;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double totalCost;
    private String status;
} 