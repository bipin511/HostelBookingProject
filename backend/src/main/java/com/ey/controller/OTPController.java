package com.ey.controller;

import com.ey.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
@CrossOrigin(origins = "http://localhost:5173")
public class OTPController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/send")
    public String sendOTP(@RequestParam String email) {
        otpService.sendOTPEmail(email);
        return "✅ Dummy OTP generated and printed in the console for: " + email;
    }

    @PostMapping("/verify")
    public String verifyOTP(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.verifyOTP(email, otp);
        return isValid ? "✅ OTP verified successfully" : "❌ Invalid OTP";
    }
}
