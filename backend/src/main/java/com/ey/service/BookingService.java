package com.ey.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.Booking;
import com.ey.entity.Room;
import com.ey.entity.User;
import com.ey.repository.BookingRepository;
import com.ey.repository.RoomRepository;
import com.ey.repository.UserRepository;
import com.ey.request.BookingRequest;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Booking createBooking(BookingRequest request) {
        Room room = roomRepository.findById(request.getRoomId())
            .orElseThrow(() -> new RuntimeException("Room not found"));

        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setRoom(room);
        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        booking.setStatus("requested");

        // ✅ Mark the room as unavailable
        room.setAvailable(false);
        roomRepository.save(room);

        return bookingRepository.save(booking);
    }


    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public Booking updateBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public void cancelBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            booking.setStatus("cancelled");
            bookingRepository.save(booking);

            // ✅ Mark room as available again
            Room room = booking.getRoom();
            room.setAvailable(true);
            roomRepository.save(room);
        }
    }


    public List<Booking> getBookingsForOwner(Long ownerId) {
        return bookingRepository.findByRoom_Hostel_Owner_Id(ownerId);
    }
    
    public Booking rescheduleBooking(Long bookingId, BookingRequest request) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setCheckIn(request.getCheckIn());
        booking.setCheckOut(request.getCheckOut());
        booking.setStatus("rescheduled");

        return bookingRepository.save(booking);
    }
    
    public void approveBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus("approved");
        bookingRepository.save(booking);
    }
    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    }



}
