package com.cyclebooking.controller;

import com.cyclebooking.model.Booking;
import com.cyclebooking.model.Cycle;
import com.cyclebooking.dto.BookingFormDTO;
import com.cyclebooking.repository.InMemoryStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cycles")
public class CycleController {
    private final InMemoryStorage storage;

    @GetMapping
    public String listCycles(Model model) {
        model.addAttribute("cycles", storage.findAvailableCycles());
        return "cycles";
    }

    @PostMapping("/{id}/book")
    public String bookCycle(@PathVariable Long id, 
                          @ModelAttribute BookingFormDTO bookingForm,
                          @AuthenticationPrincipal UserDetails userDetails) {
        Cycle cycle = storage.findCycleById(id);
        if (cycle != null && cycle.isAvailable()) {
            Booking booking = new Booking();
            booking.setCycle(cycle);
            booking.setUser(storage.findUserByUsername(userDetails.getUsername()));
            booking.setStartTime(LocalDateTime.now());
            booking.setEndTime(LocalDateTime.now().plusHours(bookingForm.getHours()));
            booking.setStatus("ACTIVE");
            booking.setTotalCost(cycle.getHourlyRate() * bookingForm.getHours());
            storage.saveBooking(booking);
            
            cycle.setAvailable(false);
            storage.saveCycle(cycle);
        }
        return "redirect:/bookings";
    }
} 