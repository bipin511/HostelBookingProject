package com.ey.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.ey.entity.Booking;
import com.ey.request.BookingRequest;
import com.ey.response.BookingResponse;
import com.ey.service.BookingService;

@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = "http://localhost:5173")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingResponse> createBooking(@RequestBody BookingRequest request) {
        Booking booking = bookingService.createBooking(request);

        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getId());
        response.setCheckIn(booking.getCheckIn());
        response.setCheckOut(booking.getCheckOut());
        response.setStatus(booking.getStatus());
        response.setUserId(booking.getUser().getId());
        response.setRoomId(booking.getRoom().getId());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResponse>> getBookingsByUser(@PathVariable Long userId) {
        List<Booking> bookings = bookingService.getBookingsByUser(userId);

        List<BookingResponse> responses = bookings.stream().map(booking -> {
            BookingResponse response = new BookingResponse();
            response.setBookingId(booking.getId());
            response.setCheckIn(booking.getCheckIn());
            response.setCheckOut(booking.getCheckOut());
            response.setStatus(booking.getStatus());
            response.setUserId(booking.getUser().getId());
            response.setRoomId(booking.getRoom().getId());
            return response;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/reschedule")
    public ResponseEntity<BookingResponse> rescheduleBooking(@PathVariable Long id, @RequestBody BookingRequest request) {
        Booking updatedBooking = bookingService.rescheduleBooking(id, request);

        BookingResponse response = new BookingResponse();
        response.setBookingId(updatedBooking.getId());
        response.setCheckIn(updatedBooking.getCheckIn());
        response.setCheckOut(updatedBooking.getCheckOut());
        response.setStatus(updatedBooking.getStatus());
        response.setUserId(updatedBooking.getUser().getId());
        response.setRoomId(updatedBooking.getRoom().getId());

        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/{id}/approve")
    public ResponseEntity<Void> approveBooking(@PathVariable Long id) {
        bookingService.approveBooking(id);
        return ResponseEntity.ok().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.ok("Booking deleted successfully");
    }

}
