package com.ey.service;

import com.ey.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OTPService {

    @Autowired
    private EmailService emailService;

    private ConcurrentHashMap<String, String> otpStore = new ConcurrentHashMap<>();

    public String generateOTP(String email) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));
        otpStore.put(email, otp);
        return otp;
    }

    public void sendOTPEmail(String email) {
        String otp = generateOTP(email);
        String subject = "Dummy OTP for Verification";
        String body = "Your dummy OTP is: " + otp + ". This is just for testing.";
        emailService.sendSimpleEmail(email, subject, body);
    }

    public boolean verifyOTP(String email, String otp) {
        String storedOtp = otpStore.get(email);
        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStore.remove(email); // Clear OTP after verification
            return true;
        }
        return false;
    }
}
