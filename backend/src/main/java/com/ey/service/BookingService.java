package com.ey.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.Booking;
import com.ey.repository.BookingRepository;
import com.ey.repository.RoomRepository;
import com.ey.repository.UserRepository;
import com.ey.request.BookingRequest;
import com.ey.request.RescheduleRequest;
import com.ey.response.BookingResponse;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    public BookingResponse createBooking(BookingRequest request) {
        Booking booking = new Booking();
        booking.setUser(userRepository.findById(request.getUserId()).orElse(null));
        booking.setRoom(roomRepository.findById(request.getRoomId()).orElse(null));
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        booking.setStatus("requested");

        bookingRepository.save(booking);
        return convertToResponse(booking);
    }

    public List<BookingResponse> getBookingsByUserId(Long userId) {
        return bookingRepository.findByUserUserId(userId)
                .stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    public BookingResponse getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.map(this::convertToResponse).orElse(null);
    }

    public String cancelBooking(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isEmpty()) return "Booking not found";

        Booking booking = bookingOpt.get();
        booking.setStatus("Cancelled");
        bookingRepository.save(booking);
        return "Booking cancelled successfully";
    }

    public String rescheduleBooking(Long bookingId, RescheduleRequest request) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isEmpty()) return "Booking not found";

        Booking booking = bookingOpt.get();
        booking.setCheckIn(request.getNewCheckIn());
        booking.setCheckOut(request.getNewCheckOut());
        booking.setStatus("Rescheduled");
        bookingRepository.save(booking);
        return "Booking rescheduled successfully";
    }

    public String getBookingStatus(Long bookingId) {
        return bookingRepository.findById(bookingId)
                .map(Booking::getStatus)
                .orElse("Booking not found");
    }

    public String approveBooking(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isEmpty()) return "Booking not found";

        Booking booking = bookingOpt.get();
        booking.setStatus("Approved");
        bookingRepository.save(booking);
        return "Booking approved successfully";
    }

    public String declineBooking(Long bookingId) {
        Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
        if (bookingOpt.isEmpty()) return "Booking not found";

        Booking booking = bookingOpt.get();
        booking.setStatus("Declined");
        bookingRepository.save(booking);
        return "Booking declined successfully";
    }

    private BookingResponse convertToResponse(Booking booking) {
        BookingResponse response = new BookingResponse();
        response.setBookingId(booking.getBookingId());
        response.setUserId(booking.getUser().getUserId());
        response.setRoomId(booking.getRoom().getRoomId());
        response.setCheckIn(booking.getCheckIn().toString());
        response.setCheckOut(booking.getCheckOut().toString());
        response.setStatus(booking.getStatus());
        return response;
    }
}
