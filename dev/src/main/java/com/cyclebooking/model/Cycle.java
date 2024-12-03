package com.cyclebooking.model;

import lombok.Data;

@Data
public class Cycle {
    private Long id;
    private String model;
    private String type;
    private boolean isAvailable;
    private double hourlyRate;
} 