package com.cyclebooking.repository;

import com.cyclebooking.model.User;
import com.cyclebooking.model.Cycle;
import com.cyclebooking.model.Booking;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Component
public class InMemoryStorage {
    private final Map<Long, User> users = new ConcurrentHashMap<>();
    private final Map<Long, Cycle> cycles = new ConcurrentHashMap<>();
    private final Map<Long, Booking> bookings = new ConcurrentHashMap<>();
    
    private final AtomicLong userIdSequence = new AtomicLong(0);
    private final AtomicLong cycleIdSequence = new AtomicLong(0);
    private final AtomicLong bookingIdSequence = new AtomicLong(0);
    
    public User saveUser(User user) {
        if (user.getId() == null) {
            user.setId(userIdSequence.incrementAndGet());
        }
        users.put(user.getId(), user);
        return user;
    }
    
    public Cycle saveCycle(Cycle cycle) {
        if (cycle.getId() == null) {
            cycle.setId(cycleIdSequence.incrementAndGet());
        }
        cycles.put(cycle.getId(), cycle);
        return cycle;
    }
    
    public Booking saveBooking(Booking booking) {
        if (booking.getId() == null) {
            booking.setId(bookingIdSequence.incrementAndGet());
        }
        bookings.put(booking.getId(), booking);
        return booking;
    }
    
    public User findUserByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
    
    public List<Cycle> findAvailableCycles() {
        return cycles.values().stream()
                .filter(Cycle::isAvailable)
                .toList();
    }
    
    public List<Booking> getAllBookings() {
        return new ArrayList<>(bookings.values());
    }
    
    // Add some initial data
    public void initializeData() {
        // Mountain Bikes
        Cycle mountainBike = new Cycle();
        mountainBike.setModel("Trek X-Caliber");
        mountainBike.setType("Mountain Bike");
        mountainBike.setAvailable(true);
        mountainBike.setHourlyRate(15.0);
        saveCycle(mountainBike);
        
        Cycle advancedMTB = new Cycle();
        advancedMTB.setModel("Specialized Stumpjumper");
        advancedMTB.setType("Mountain Bike");
        advancedMTB.setAvailable(true);
        advancedMTB.setHourlyRate(20.0);
        saveCycle(advancedMTB);

        // Road Bikes
        Cycle roadBike = new Cycle();
        roadBike.setModel("Giant Contend");
        roadBike.setType("Road Bike");
        roadBike.setAvailable(true);
        roadBike.setHourlyRate(18.0);
        saveCycle(roadBike);
        
        Cycle racingBike = new Cycle();
        racingBike.setModel("Cannondale CAAD13");
        racingBike.setType("Road Bike");
        racingBike.setAvailable(true);
        racingBike.setHourlyRate(25.0);
        saveCycle(racingBike);

        // Hybrid Bikes
        Cycle hybridBike = new Cycle();
        hybridBike.setModel("Schwinn Discover");
        hybridBike.setType("Hybrid");
        hybridBike.setAvailable(true);
        hybridBike.setHourlyRate(12.0);
        saveCycle(hybridBike);
        
        Cycle cityBike = new Cycle();
        cityBike.setModel("Trek FX");
        cityBike.setType("Hybrid");
        cityBike.setAvailable(true);
        cityBike.setHourlyRate(10.0);
        saveCycle(cityBike);

        // Kids Bikes
        Cycle kidsBike = new Cycle();
        kidsBike.setModel("Mongoose Legion");
        kidsBike.setType("Kids");
        kidsBike.setAvailable(true);
        kidsBike.setHourlyRate(8.0);
        saveCycle(kidsBike);
        
        // Electric Bikes
        Cycle eBike = new Cycle();
        eBike.setModel("RadCity Electric");
        eBike.setType("Electric");
        eBike.setAvailable(true);
        eBike.setHourlyRate(30.0);
        saveCycle(eBike);
        
        // Test user remains the same
        User user = new User();
        user.setUsername("testuser");
        user.setPassword("$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqV90IheNlYhWNgqX.LTmxWu0s.O"); // password
        user.setEmail("test@example.com");
        user.setFullName("Test User");
        saveUser(user);
    }
    
    public Cycle findCycleById(Long id) {
        return cycles.get(id);
    }
    
    public List<Booking> getBookingsByUsername(String username) {
        return bookings.values().stream()
                .filter(booking -> booking.getUser().getUsername().equals(username))
                .toList();
    }
} 