package com.ey.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    // Each payment is associated with a booking
    @ManyToOne
    @JoinColumn(name = "booking_id")
    private Booking booking;

    private double amount;
    private String method; // e.g., PayPal, Card
    private String status; // e.g., pending, paid, failed

    private LocalDateTime createdAt = LocalDateTime.now();

    public Payment() {}

    public Payment(Long paymentId, Booking booking, double amount, String method, String status) {
        this.paymentId = paymentId;
        this.booking = booking;
        this.amount = amount;
        this.method = method;
        this.status = status;
    }

    // Getters and setters
    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", booking=" + booking +
                ", amount=" + amount +
                ", method='" + method + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
