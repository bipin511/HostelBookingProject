package com.ey.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ey.entity.Booking;
import com.ey.entity.Payment;
import com.ey.repository.BookingRepository;
import com.ey.repository.PaymentRepository;
import com.ey.request.PaymentRequest;
import com.ey.response.PaymentResponse;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // ✅ Make a new payment
    public PaymentResponse makePayment(PaymentRequest request) {
        PaymentResponse response = new PaymentResponse();

        // Validate that the booking exists
        Optional<Booking> bookingOptional = bookingRepository.findById(request.getBookingId());
        if (!bookingOptional.isPresent()) {
            response.setStatus("Booking not found");
            return response;
        }

        Booking booking = bookingOptional.get();

        // Create and save the payment
        Payment payment = new Payment();
        payment.setBooking(booking);
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus("Completed"); // You can make this dynamic
        Payment savedPayment = paymentRepository.save(payment);

        // Prepare response
        response.setPaymentId(savedPayment.getPaymentId());
        response.setBookingId(booking.getBookingId());
        response.setAmount(savedPayment.getAmount());
        response.setMethod(savedPayment.getMethod());
        response.setStatus(savedPayment.getStatus());
        response.setCreatedAt(savedPayment.getCreatedAt().toString());

        return response;
    }

    // ✅ Get payment by ID
    public PaymentResponse getPaymentById(Long paymentId) {
        PaymentResponse response = new PaymentResponse();

        Optional<Payment> paymentOptional = paymentRepository.findById(paymentId);
        if (!paymentOptional.isPresent()) {
            response.setStatus("Payment not found");
            return response;
        }

        Payment payment = paymentOptional.get();
        response.setPaymentId(payment.getPaymentId());
        response.setBookingId(payment.getBooking().getBookingId());
        response.setAmount(payment.getAmount());
        response.setMethod(payment.getMethod());
        response.setStatus(payment.getStatus());
        response.setCreatedAt(payment.getCreatedAt().toString());

        return response;
    }
}
