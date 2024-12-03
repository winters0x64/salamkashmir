package com.cyclebooking.dto;

import lombok.Data;

@Data
public class BookingFormDTO {
    private Long cycleId;
    private String customerName;
    private Integer hours;
} 