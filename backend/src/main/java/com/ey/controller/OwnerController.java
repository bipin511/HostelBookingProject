package com.ey.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ey.entity.Booking;
import com.ey.entity.Room;
import com.ey.service.BookingService;
import com.ey.service.RoomService;

@RestController
@RequestMapping("/api/owner")
@CrossOrigin(origins = "http://localhost:5173")
public class OwnerController {

    @Autowired
    private BookingService bookingService;
    
    @Autowired
    private RoomService roomService;
    
    // Owner approves booking requests (for bookings in their hostel)
    @PutMapping("/bookings/{id}/approve")
    public ResponseEntity<Booking> approveBooking(@PathVariable Long id) {
        Booking booking = bookingService.updateBooking(
            bookingService.getBookingsByUser(id).stream().findFirst().orElse(null)
        );
        if (booking != null) {
            booking.setStatus("approved");
            booking = bookingService.updateBooking(booking);
            return ResponseEntity.ok(booking);
        }
        return ResponseEntity.notFound().build();
    }
    
    // Owner declines booking requests
    @PutMapping("/bookings/{id}/decline")
    public ResponseEntity<Booking> declineBooking(@PathVariable Long id) {
        Booking booking = bookingService.getBookingsByUser(id).stream().findFirst().orElse(null);
        if (booking != null) {
            booking.setStatus("declined");
            booking = bookingService.updateBooking(booking);
            return ResponseEntity.ok(booking);
        }
        return ResponseEntity.notFound().build();
    }
    
    // Owner adds a new room to a hostel
    @PostMapping("/rooms")
    public ResponseEntity<Room> addRoom(@RequestBody Room room) {
        Room created = roomService.addRoom(room);
        return ResponseEntity.ok(created);
    }
    
    // Owner updates room details (availability, rent, occupancy)
    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Long id, @RequestBody Room room) {
        room.setId(id);
        Room updated = roomService.updateRoom(room);
        return ResponseEntity.ok(updated);
    }
    
    // Owner views all bookings for their hostel
    @GetMapping("/bookings/{ownerId}")
    public ResponseEntity<List<Booking>> getBookingsForOwner(@PathVariable Long ownerId) {
        List<Booking> bookings = bookingService.getBookingsForOwner(ownerId);
        return ResponseEntity.ok(bookings);
    }
}
