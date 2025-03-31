package com.ey.util;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        // Dummy OTP print instead of sending real email
        System.out.println("📧 Dummy Email to: " + toEmail);
        System.out.println("📌 Subject: " + subject);
        System.out.println("📨 Message: " + body);
    }
}
