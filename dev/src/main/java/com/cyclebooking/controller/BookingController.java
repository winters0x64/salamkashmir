package com.cyclebooking.controller;

import com.cyclebooking.repository.InMemoryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/bookings")
public class BookingController {
    private final InMemoryStorage storage;

    @GetMapping
    public String listBookings(@AuthenticationPrincipal UserDetails userDetails,
                             Model model) {
        model.addAttribute("bookings", 
            storage.getBookingsByUsername(userDetails.getUsername()));
        return "bookings";
    }
} 