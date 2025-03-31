package com.ey.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ey.request.BookingRequest;
import com.ey.request.RescheduleRequest;
import com.ey.response.BookingResponse;
import com.ey.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    // === Create Booking ===
    @PostMapping
    public ResponseEntity<BookingResponse> createBooking( @Valid @RequestBody BookingRequest request) {
        return ResponseEntity.ok(bookingService.createBooking(request));
    }

    // === Get Bookings by User ID ===
    @GetMapping("/user/{userId}")
    public List<BookingResponse> getBookingsByUser(@PathVariable Long userId) {
        return bookingService.getBookingsByUserId(userId);
    }

    // === Get Booking by ID ===
    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> getBookingById(@PathVariable Long id) {
        BookingResponse booking = bookingService.getBookingById(id);
        return booking != null ? ResponseEntity.ok(booking) : ResponseEntity.notFound().build();
    }

    // === Cancel Booking (User-Initiated) ===
    @PutMapping("/{id}/cancel")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok("Booking cancelled successfully");
    }

    // === Reschedule Booking ===
    @PutMapping("/{bookingId}/reschedule")
    public ResponseEntity<String> rescheduleBooking(
            @PathVariable Long bookingId,
            @RequestBody RescheduleRequest request
    ) {
        String message = bookingService.rescheduleBooking(bookingId, request);
        return ResponseEntity.ok(message);
    }

    // === Cancel Booking (Alternate Endpoint) ===
    @DeleteMapping("/{bookingId}/cancel")
    public ResponseEntity<String> cancelBookingAgain(@PathVariable Long bookingId) {
        String message = bookingService.cancelBooking(bookingId);
        return ResponseEntity.ok(message);
    }

    // === Get Booking Status ===
    @GetMapping("/{bookingId}/status")
    public ResponseEntity<String> getBookingStatus(@PathVariable Long bookingId) {
        String status = bookingService.getBookingStatus(bookingId);
        return ResponseEntity.ok(status);
    }

    // === Approve Booking (Owner/Hostel) ===
    @PutMapping("/{bookingId}/approve")
    public ResponseEntity<String> approveBooking(@PathVariable Long bookingId) {
        String message = bookingService.approveBooking(bookingId);
        return ResponseEntity.ok(message);
    }

    // === Decline Booking (Owner/Hostel) ===
    @PutMapping("/{bookingId}/decline")
    public ResponseEntity<String> declineBooking(@PathVariable Long bookingId) {
        String message = bookingService.declineBooking(bookingId);
        return ResponseEntity.ok(message);
    }
}
