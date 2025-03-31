package com.ey.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.Booking;
import com.ey.entity.Hostel;
import com.ey.exception.ResourceNotFoundException;
import com.ey.repository.BookingRepository;
import com.ey.repository.HostelRepository;
import com.ey.response.BookingResponse;
import com.ey.response.HostelResponse;

@Service
public class AdminService {

    @Autowired
    private HostelRepository hostelRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Get hostels pending verification (where isVerified == false)
    public List<HostelResponse> getPendingHostels() {
        List<Hostel> pending = hostelRepository.findAll().stream()
                .filter(h -> !h.isVerified())
                .collect(Collectors.toList());
        return pending.stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    // Verify a hostel by setting its isVerified field to true
    public String verifyHostel(Long hostelId) {
        Hostel hostel = hostelRepository.findById(hostelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hostel not found for ID: " + hostelId));
        hostel.setVerified(true);
        hostelRepository.save(hostel);
        return "Hostel verified successfully";
    }

    // Get all bookings (for admin monitoring)
    public List<BookingResponse> getAllBookings() {
        List<Booking> bookings = bookingRepository.findAll();
        return bookings.stream()
                .map(this::convertToBookingResponse)
                .collect(Collectors.toList());
    }

    // Helper method to convert a Hostel entity to a HostelResponse DTO
    private HostelResponse convertToResponse(Hostel hostel) {
        HostelResponse response = new HostelResponse();
        response.setHostelId(hostel.getHostelId());
        response.setName(hostel.getName());
        response.setLocation(hostel.getLocation());
        response.setDescription(hostel.getDescription());
        response.setVerified(hostel.isVerified());
        return response;
    }

    // Helper method to convert a Booking entity to a BookingResponse DTO
    private BookingResponse convertToBookingResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setCheckIn(booking.getCheckIn().toString());
        response.setCheckOut(booking.getCheckOut().toString());
        response.setStatus(booking.getStatus());
        // Add other fields as needed
        return response;
    }
}
