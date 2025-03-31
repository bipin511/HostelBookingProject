package com.ey.repository;

import com.ey.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // Additional custom queries (if needed) can be defined here.
}
