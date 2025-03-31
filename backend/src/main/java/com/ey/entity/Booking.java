package com.ey.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    // Many bookings by one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Many bookings for one room
    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    private LocalDate checkIn;
    private LocalDate checkOut;

    // Example statuses: requested, approved, cancelled, completed
    private String status;

    public Booking() {}

    public Booking(Long bookingId, User user, Room room, LocalDate checkIn, LocalDate checkOut, String status) {
        this.bookingId = bookingId;
        this.user = user;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.status = status;
    }

    // Getters and setters
    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId=" + bookingId +
                ", user=" + user +
                ", room=" + room +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", status='" + status + '\'' +
                '}';
    }
}
